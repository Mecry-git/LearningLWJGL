package Main;

import Engine.Graphics.Vertex;
import Engine.Maths.Vector3F;
import Engine.io.Window.Init;
import Engine.io.Window.Window;

public class Main implements Runnable {
    public static final Vertex[] vertices = new Vertex().toFloatArray(new float[] {
            -0.5f,  0.5f,  0.0f,   0.5f,  0.5f, 0.0f,
             0.5f, -0.5f,  0.0f,  -0.5f, -0.5f, 0.0f
    }, new float[] {
            0, 0, 0,   0, 0, 0,   0, 0, 0,   0, 0, 0
    }, new float[] {
             0, 0,   0, 1,   1, 1,   1, 0
    });
    public static final int[] indices = new int[] {
            0, 1, 2,   0, 2, 3
    };
    public static final String bgt1P = "/Textures/bg1.png";
    public static final String bgt2P = "/Textures/bg2.jpg";
    public static final String vertexFilePath = "/Shaders/MainVertex.glsl";
    public static final String fragmentFilePath = "/Shaders/MainFragment.glsl";

    public static Vector3F pos = new Vector3F(0, 0, 1);
    public static Vector3F rot = new Vector3F(0, 0, 0);
    public static Vector3F scale = new Vector3F(1, 1, 1);
    public static float camMoveSpeed = 0.01f;
    public static float camRotSpeed = 1;
    public static float fov = 70;
    public static float near = 0.1f;
    public static float far = 1000;

    public static Window window = new Window("LearningLWJGL Window");

    private void start() {
        Thread mainThread = new Thread(this, "Learning LWJGL Main Thread");
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
