package Render;

import Engine.io.Window;

public class Loop {
    public void loop() {
        Window window = new Window();
        Render render = new Render();
        Update update = new Update();
        ActionKeys actionKeys = new ActionKeys();
        ActionButtons actionButtons = new ActionButtons();
        while (!window.ifShouldClose()) {
            update.update();
            render.render();
            if (actionKeys.actionKeys()) return;
            if (actionButtons.actionButtons()) return;
        }
    }
}
