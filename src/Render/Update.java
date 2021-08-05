package Render;

import Engine.io.Callbacks;
import Engine.io.Window;

public class Update {
    Window window = new Window();
    public void update() {
        window.update();
        Callbacks.updateCallBacks();
    }
}
