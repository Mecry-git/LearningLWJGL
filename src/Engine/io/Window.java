package Engine.io;

import Engine.io.Input.Input;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.glfw.GLFW.*;

public class Window {
    public static int frames;
    public static long time;
    private static Point pos = new Point(100, 100);
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static Dimension size = new Dimension(screenSize.width,
            screenSize.height);
    private static String title;
    private static long window;
    private static boolean shouldClose;
    private static boolean isResized;
    private static boolean isFullScreen;
    private static float bgcR, bgcG, bgcB;

    public void window(String title) {
        Window.title = title;
    }

    public void create() {
        if (!glfwInit()) {
            throw new IllegalStateException("ERROR: Unable to initialize GLFW!");
        }

        window = glfwCreateWindow(size.width, size.height,
                title + " | FPS: Loading...",
                isFullScreen ? glfwGetPrimaryMonitor() : 0, 0);
        if (window == 0) {
            throw new IllegalStateException("ERROR: Failed to create a window!");
        }

        GLFWVidMode videoMode = glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        assert videoMode != null;
        System.out.println(videoMode.width() + " | " + videoMode.height());
        pos = new Point((videoMode.width() - size.width) / 2,
                (videoMode.height() - size.height) / 2);
        System.out.println(pos);
        glfwSetWindowPos(window, pos.x, pos.y);
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        setCallBacks();
        glfwShowWindow(window);
        glfwSwapInterval(1);
        time = System.currentTimeMillis();
    }

    public void update() {
        if (isResized) {
            GL11.glViewport(0, 0, size.width, size.height);
            isResized = false;
        }
        glfwSetWindowMonitor(window, isFullScreen ? glfwGetPrimaryMonitor() : 0,
                pos.x, pos.y, size.width, size.height, 0);
        GL11.glClearColor(bgcR, bgcG, bgcB, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        glfwPollEvents();
        frames ++;
        if (System.currentTimeMillis() > time + 1000) {
            glfwSetWindowTitle(window, title + " | FPS: " + frames);
            time = System.currentTimeMillis();
            frames = 0;
        }
    }

    public void swapBuffers() {
        glfwSwapBuffers(window);
    }

    public void setBgc(float r, float g, float b) {
        bgcR = r;
        bgcG = g;
        bgcB = b;
    }

    public boolean ifShouldClose() {
        if (!shouldClose)
            return glfwWindowShouldClose(window);
        else return true;
    }
    public static void setShouldClose(boolean isShouldClose) {
        shouldClose = isShouldClose;
    }

    public void destroy() {
        new Input().destroy();
        glfwWindowShouldClose(window);
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    private void setCallBacks() {
        GLFWWindowSizeCallback sizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                size = new Dimension(width, height);
                isResized = true;
            }
        };

        glfwSetKeyCallback(window, Input.getKeyboardKeyCallback());
        glfwSetMouseButtonCallback(window, Input.getMouseButtons());
        glfwSetCursorPosCallback(window, Input.getCursorPosCallback());
        glfwSetWindowSizeCallback(window, sizeCallback);
    }

    public Dimension getSize() {
        return size;
    }
    public static long getWindow() {
        return window;
    }
    public static boolean isResized() {
        return isResized;
    }
    public static void changeFullScreen() {
        isFullScreen = !isFullScreen;
        isResized = true;
    }
}
