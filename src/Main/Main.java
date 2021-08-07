package Main;

import Engine.Graphics.Vertex;
import Engine.io.Window.Init;
import Engine.io.Window.Window;
import Engine.Maths.Vector3F;

public class Main implements Runnable{
    public Thread mainThread;

    public static Vertex[] vertices = new Vertex[] {
            new Vertex(new Vector3F(-0.5f,  0.5f, 0.0f)),
            new Vertex(new Vector3F( 0.5f,  0.5f, 0.0f)),
            new Vertex(new Vector3F( 0.5f, -0.5f, 0.0f)),
            new Vertex(new Vector3F(-0.5f, -0.5f, 0.0f))
    };
    public static int[] indices = new int[] {
            0, 1, 2,  0, 2, 3
    };

    public void start() {
        mainThread = new Thread(this, "Learning LWJGL Main Thread");
        mainThread.start();
    }

    public void run() {
        new Init("Learning LWJGL Window");
        Window window = new Window();
        while (!Window.ifShouldClose()) window.update();
        window.destroy();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
