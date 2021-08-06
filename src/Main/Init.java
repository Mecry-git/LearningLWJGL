package Main;

import Engine.io.Callbacks;
import Engine.io.Window;

public class Init {
    public void init(String windowTitle, float bgcR, float bgcG, float bgcB) {
        Callbacks.updateCallBacks();
        Window window = new Window();
        window.setWindow(windowTitle);
        window.setBgc(bgcR, bgcG, bgcB);
        window.create();
    }
}
