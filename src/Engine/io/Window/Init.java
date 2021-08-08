package Engine.io.Window;

import Engine.io.Callbacks;

public class Init {
    public Init(String windowTitle) {
        Callbacks.updateCallBacks();
        Window.setWindowTitle(windowTitle);
        Window window = new Window();
        window.create();
    }
}
