package Render;

import Engine.io.Input.Input;
import Engine.io.Window;

import static Engine.io.Input.InputActions.*;

public class Update {
    Window window = new Window();
    public void update() {
        window.update();
        Input.input();
    }
}
