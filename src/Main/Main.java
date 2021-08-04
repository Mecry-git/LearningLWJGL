package Main;

import Engine.io.Window;
import Render.Loop;

public class Main implements Runnable {
    public Thread game;

    public void start() {
        game = new Thread(this, "game");
        game.start();
    }

    public void run() {
        new Init().init("Game", 1.0f, 0.0f, 0.0f);
        new Loop().loop();
        new Window().destroy();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
