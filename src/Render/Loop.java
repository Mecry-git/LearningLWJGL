package Render;

import Engine.io.Input.Input;
import Engine.io.Window;

import static Engine.io.Input.InputActions.*;

public class Loop {
    public void loop() {
        Window window = new Window();
        Render render = new Render();
        Update update = new Update();
        while (!window.ifShouldClose()) {
            update.update();
            render.render();
            Input.input();
            if (actionKeys()) return;
            actionButtons();
            actionCursorPos();
        }
    }
}
