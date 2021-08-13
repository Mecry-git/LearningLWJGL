package Main;

import Engine.Graphics.Vertex;
import Engine.io.Window.Init;
import Engine.io.Window.Window;

public class Main implements Runnable{
    public Thread mainThread;

    public static Vertex[] vertices = new Vertex().toFloatArray(new float[] {
            -1f,  1f,  0f,   1f,  1f, 0f,
             1f, -1f,  0f,  -1f, -1f, 0f
    }, new float[] {
            0f, 0f, 0f,  0f, 0f, 0f,
            0f, 0f, 0f,  0f, 0f, 0f
    }, new float[] {
             0f, 0f,  0f, 1f,  1f, 1f,  1f, 0f
    });
    public static int[] indices = new int[] {
            0, 1, 2,  0, 2, 3
    };
    public static String textureFilePath = "/Textures/YouDidIt.png";
    public static String vertexFilePath = "/Shaders/MainVertex.glsl";
    public static String fragmentFilePath = "/Shaders/MainFragment.glsl";

    public static Window window = new Window("LearningLWJGL Window");

    public void start() {
        mainThread = new Thread(this, "Learning LWJGL Main Thread");
        mainThread.start();
    }

    public void run() {
        new Init();
        while (!window.ifShouldClose()) window.update();
        window.destroy();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
