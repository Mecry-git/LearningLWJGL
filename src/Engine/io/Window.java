package Engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

import java.awt.*;

import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetWindowTitle;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwTerminate;

public class Window {
    public static int frames;
    public static long time;

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final Dimension size = new Dimension(screenSize.width,
            screenSize.height);
    private static String title;
    private static long window;
    public void window(String title) {
        Window.title = title;
    }

    public void create() {
        if (!glfwInit()) {
            throw new IllegalStateException("ERROR: Unable to initialize GLFW!");
        }

        window = glfwCreateWindow(size.width, size.height,
                title + " | FPS: Loading...", 0, 0);
        if (window == 0) {
            throw new IllegalStateException("ERROR: Failed to create a window!");
        }

        GLFWVidMode videoMode = glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        assert videoMode != null;
        glfwSetWindowPos(window, (videoMode.width() - size.width) / 2,
                (videoMode.height() - size.height) / 2);
        glfwMakeContextCurrent(window);

        glfwSetKeyCallback(window, Input.getKeyboardKeyCallback());
        glfwSetMouseButtonCallback(window, Input.getMouseButtons());
        glfwSetCursorPosCallback(window, Input.getCursorPosCallback());

        glfwShowWindow(window);

        glfwSwapInterval(1);

        time = System.currentTimeMillis();
    }

    public void update() {
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

    public boolean ifShouldClose() {
        return glfwWindowShouldClose(window);
    }

    public void destroy() {
        new Input().destroy();
        glfwWindowShouldClose(window);
        glfwDestroyWindow(window);
        glfwTerminate();
    }
}
