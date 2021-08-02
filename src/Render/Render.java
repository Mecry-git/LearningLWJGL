package Render;

import Engine.io.Window;

public class Render {
    Window window = new Window();
    public void render() {
        window.swapBuffers();
    }
}
