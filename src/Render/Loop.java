package Render;

import Engine.io.Window;

public class Loop {
    public void loop() {
        Window window = new Window();
        Render render = new Render();
        Update update = new Update();
        while (!window.ifShouldClose()) {
            if (update.update()) return;
            render.render();
        }
    }
}
