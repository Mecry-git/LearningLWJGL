package Engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static Engine.io.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;

public class Window {
    public static int frames;
    public static long time;
    private static Point pos = new Point(100, 100);
    private static final Dimension screenSize =
            Toolkit.getDefaultToolkit().getScreenSize();
    private static Dimension size = new Dimension(screenSize.width,
            screenSize.height);
    private static String title;
    private static long window;
    private static boolean shouldClose;
    private static boolean isFullScreen;
    private static float bgcR, bgcG, bgcB;

    public void create() {
        //Initialize GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("ERROR: Unable to initialize GLFW!");
        }

        //Create a window
        window = glfwCreateWindow(size.width, size.height,
                title + " | FPS: Loading...",
                isFullScreen ? glfwGetPrimaryMonitor() : 0, 0);
        if (window == 0) {
            throw new IllegalStateException("ERROR: Failed to create a window!");
        }

        //Let window go mid
        GLFWVidMode videoMode = glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        assert videoMode != null;
        pos = new Point((videoMode.width() - size.width) / 2,
                (videoMode.height() - size.height) / 2);
        glfwSetWindowPos(window, pos.x, pos.y);

        //Window settings
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        setCallBacks();
        glfwShowWindow(window);
        glfwSwapInterval(1);

        //FrameTime setup
        time = System.currentTimeMillis();
    }
    public void update() {
        GL11.glClearColor(bgcR, bgcG, bgcB, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        glfwPollEvents();

        updateFrames();
    }

    public void setWindow(String title) {
        Window.title = title;
    }

    public void swapBuffers() {
        glfwSwapBuffers(window);
    }

    public void setBgc(float r, float g, float b) {
        bgcR = r;
        bgcG = g;
        bgcB = b;
    }

    public void updateFrames() {
        frames ++;
        if (System.currentTimeMillis() > time + 1000) {
            glfwSetWindowTitle(window, title + " | FPS: " + frames);
            time = System.currentTimeMillis();
            frames = 0;
        }
    }

    private void setCallBacks() {
        glfwSetKeyCallback(getWindow(), getKeyboardKeyCallback());
        glfwSetCursorPosCallback(getWindow(), getCursorPosCallback());
        glfwSetMouseButtonCallback(getWindow(), getMouseButtons());
        glfwSetScrollCallback(getWindow(), getMouseScroll());

        glfwSetWindowSizeCallback(getWindow(), getSizeCallback());
        glfwSetWindowPosCallback(getWindow(), getPosCallback());
    }

    public boolean ifShouldClose() {
        if (!shouldClose)
            return glfwWindowShouldClose(window);
        else return true;
    }
    public static void setShouldClose(boolean isShouldClose) {
        shouldClose = isShouldClose;
    }

    public static long getWindow() {
        return window;
    }

    public static Dimension getSize() {
        return size;
    }
    public static void setSize(Dimension size) {
        Window.size = size;
    }

    public static boolean getIsFullScreen() {
        return isFullScreen;
    }

    public static void setPos(Point pos) {
        Window.pos = pos;
    }

    public void destroy() {
        System.out.println("Close game!");

        Callbacks.destroy();

        glfwWindowShouldClose(window);
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public static void changeFullScreen() {
        glfwSetWindowMonitor(window, isFullScreen ? glfwGetPrimaryMonitor() : 0,
                pos.x, pos.y, size.width, size.height, 0);

        isFullScreen = !isFullScreen;
        GL11.glViewport(0, 0, size.width, size.height);
    }
}
