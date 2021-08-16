package Main;

import Engine.Blocks.Block;
import Engine.Blocks.Image;
import Engine.Maths.Vector3F;
import Engine.Objects.CamObj;
import Engine.io.Window.Init;
import Engine.io.Window.Window;

public class Main implements Runnable {
    public static final String vertexFilePath = "/Shaders/MainVertex.glsl";
    public static final String fragmentFilePath = "/Shaders/MainFragment.glsl";

    public static Block[] blocks = new Block[] {
            new Block(new Vector3F(), "Grass_Block")
    };

    public static CamObj camera = new CamObj(new Vector3F(0, 0, 5),
            new Vector3F(0, 0, 0), 70, 0.1f, 1000,
            0.05f, 1);

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
