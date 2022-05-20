package Engine.io.Window;

import Engine.Graphics.Material;
import Engine.Graphics.Mesh;
import Engine.Graphics.Renderer;
import Engine.Graphics.Shader;
import Engine.Maths.Matrix4F;
import Engine.Maths.Vector3F;
import Engine.Objects.ProgObj;
import Engine.io.Input.Callbacks;
import Engine.io.OutputPrint.Terminal;
import Main.Main;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.Objects;

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
    private static boolean rotIsKey;

    private static final Shader shader = new Shader(Main.vertexFilePath, Main.fragmentFilePath);
    private final Renderer renderer = new Renderer(this, shader);
    private static final Mesh[] meshes = new Mesh[Main.blocks.length * 6];
    public static ProgObj[] progObjs = new ProgObj[Main.blocks.length * 6];
    public Matrix4F prjtnMat = Matrix4F.prjtn(Main.camera.fov, (float) size.width / size.height, Main.camera.near, Main.camera.far);

    public Window(String title) {
        setWindowTitle(title);

        for (int i = 0; i < Main.blocks.length; i ++)
            for (int j = 0; j < 6; j ++) {
                meshes[j] = new Mesh(Main.blocks[0].images[j].vertices,
                        Main.blocks[0].images[j].indices, new Material(Main.blocks[0].images[j].pic));
                progObjs[j] = new ProgObj(Main.camera.pos, Main.camera.rot, Main.scale, meshes[j]);
            }
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
        for (int i = 0; i < 6; i ++)
            progObjs[i].mesh.create();
        shader.create();

        glfwSetCursorPos(window, (double) size.width / 2, (double) size.height / 2);
    }
    public void update() {
        //Program and Camera update
        for (int i = 0; i < 6; i ++) {
            progObjs[i] = new ProgObj(Main.camera.pos, Main.camera.rot, Main.scale, meshes[i]);
            //Renderer update
            renderer.renderMesh(progObjs[i]);
        }
        Main.camera.update();
        //Render
        glfwSwapBuffers(window);

        //Window update
        GL11.glClearColor(bgc.x, bgc.y, bgc.z, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        glfwPollEvents();

        updateFrames();

        //Update callbacks
        Callbacks.updateCallbacks();

        if (!rotIsKey) glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
        else glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
    }

    public void setWindowTitle(String title) {
        Window.title = title;
    }

    public boolean isBgcBlack() {
        return Objects.equals(bgc, new Vector3F());
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
        glfwSetKeyCallback(getWindowID(), getKeyboardKeyCallback());
        glfwSetCursorPosCallback(getWindowID(), getCursorPosCallback());
        glfwSetMouseButtonCallback(getWindowID(), getMouseButtons());
        glfwSetScrollCallback(getWindowID(), getMouseScroll());

        glfwSetWindowSizeCallback(getWindowID(), getSizeCallback());
        glfwSetWindowPosCallback(getWindowID(), getPosCallback());
    }

    public boolean ifShouldClose() {
        if (!shouldClose)
            return glfwWindowShouldClose(window);
        else return true;
    }
    public void setShouldClose(boolean isShouldClose) {
        shouldClose = isShouldClose;
    }

    public long getWindowID() {
        return window;
    }

    public Dimension getSize() {
        return size;
    }
    public void setSize(Dimension size) {
        Window.size = size;
    }

    private static boolean isSgFS;
    public void setPos(@NotNull Point pos) {
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
    public boolean isFS() {
        return isFS;
    }

    public boolean isRotKey() {
        return rotIsKey;
    }
    public void chRotFrom() {
        rotIsKey = !rotIsKey;
        if (!rotIsKey) glfwSetCursorPos(window, (double) size.width / 2, (double) size.height / 2);
    }

    public void destroy() {
        Terminal.printCloseWords();

        for (int i = 0; i < 6; i ++)
            progObjs[i].mesh.destroy();
        shader.destroy();
        Callbacks.destroy();

        glfwWindowShouldClose(window);
        glfwDestroyWindow(window);
        glfwTerminate();
    }
}
