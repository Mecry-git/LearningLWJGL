package Engine.io.Window;

import Engine.Graphics.Material;
import Engine.Graphics.Mesh;
import Engine.Graphics.Renderer;
import Engine.Graphics.Shader;
import Engine.Maths.Vector3F;
import Engine.io.Input.Callbacks;
import Main.Main;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static Engine.io.Input.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;

public class Window {
    public static int frames;
    public static long time;
    private static Point pos = new Point(100, 100);
    private static Point lastPos;
    private static final Dimension SCREEN_SIZE =
            Toolkit.getDefaultToolkit().getScreenSize();
    private static Dimension size = new Dimension(SCREEN_SIZE.width,
            SCREEN_SIZE.height);
    private static String title;
    private static long window;
    private static Vector3F bgc = new Vector3F();
    private static boolean isFS;
    private static boolean shouldClose;

    private static final Shader shader = new Shader(Main.vertexFilePath, Main.fragmentFilePath);
    private static final Renderer renderer = new Renderer(shader);
    private static final Mesh mesh = new Mesh(Main.vertices, Main.indices,
            new Material(Main.textureFilePath));

    public Window(String title) {
        setWindowTitle(title);
    }

    public void create() {
        //Initialize GLFW
        if (!glfwInit())
            throw new IllegalStateException("\nERROR: Unable to initialize GLFW!");

        //Create a window
        window = glfwCreateWindow(size.width, size.height,
                title + " | FPS: Loading...",
                isFS ? glfwGetPrimaryMonitor() : 0, 0);
        if (window == 0)
            throw new IllegalStateException("\nERROR: Failed to create a window!");

        //Let window go mid
        GLFWVidMode videoMode = glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        assert videoMode != null;

        pos = new Point((videoMode.width() - size.width) / 2,
                (videoMode.height() - size.height) / 2);
        lastPos = pos;
        glfwSetWindowPos(window, pos.x, pos.y);

        //Make the OpenGL context
        glfwMakeContextCurrent(window);
        GL.createCapabilities();

        //Window settings
        setCallBacks();
        glfwShowWindow(window);
        glfwSwapInterval(1);

        //FrameTime setup
        time = System.currentTimeMillis();

        //Create mesh
        mesh.create();
        shader.create();
    }

    public void update() {
        //Render
        renderer.renderMesh(mesh);
        glfwSwapBuffers(window);

        //Window update
        GL11.glClearColor(bgc.x, bgc.y, bgc.z, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        glfwPollEvents();

        updateFrames();

        //Update callbacks
        Callbacks.updateCallbacks();
    }

    public void setWindowTitle(String title) {
        Window.title = title;
    }

    public static boolean isBgcBlack() {
        return bgc.x == 0f  &&  bgc.y == 0f  &&  bgc.z == 0f;
    }
    public void setBgc(Vector3F bgc) {
        Window.bgc = bgc;
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
    public void setShouldClose(boolean isShouldClose) {
        shouldClose = isShouldClose;
    }

    public long getWindow() {
        return window;
    }

    public Dimension getSize() {
        return size;
    }
    public void setSize(Dimension size) {
        Window.size = size;
    }

    private static boolean isSgFS;
    public void setPos(Point pos) {
        if (pos.equals(new Point()))
            if (isSgFS) lastPos = Window.pos;
            else pos = lastPos;
        Window.pos = pos;
        isSgFS = false;
    }
    public void chFS() {
        if (isFS) glfwSetWindowMonitor(window, 0, pos.x, pos.y,
                size.width, size.height, 0);
        else {
            isSgFS = true;
            setPos(new Point());
            glfwSetWindowMonitor(window, glfwGetPrimaryMonitor(), pos.x, pos.y,
                    SCREEN_SIZE.width, SCREEN_SIZE.height, 0);
        }

        isFS = !isFS;
        GL11.glViewport(0, 0, size.width, size.height);
    }

    public void destroy() {
        System.out.println("Close game!");

        mesh.destroy();
        shader.destroy();
        Callbacks.destroy();

        glfwWindowShouldClose(window);
        glfwDestroyWindow(window);
        glfwTerminate();
    }
}
