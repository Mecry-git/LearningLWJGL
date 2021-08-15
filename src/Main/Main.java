package Main;

import Engine.Graphics.Vertex;
import Engine.Maths.Vector3F;
import Engine.Objects.CamObj;
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

    public static CamObj camera = new CamObj(new Vector3F(0, 0, 1),
            new Vector3F(0, 0, 0), 70, 0.1f, 1000,
            0.01f, 1);

    public static Vector3F scale = new Vector3F(1, 1, 1);

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
