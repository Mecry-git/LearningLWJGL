package Main;

import Engine.io.Window;
import Render.*;

public class Main implements Runnable {
    public Thread game;

    public void start() {
        game = new Thread(this, "game");
        game.start();
    }

    public void run() {
        new Init().init("Game");
        new Loop().loop();
        new Window().destroy();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
