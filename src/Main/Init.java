package Main;

import Engine.io.Window;

public class Init {
    public void init(String windowTitle) {
        Window window = new Window();
        window.window(windowTitle);
        window.create();
    }
}
