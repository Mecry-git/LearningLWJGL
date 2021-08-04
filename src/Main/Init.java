package Main;

import Engine.io.Input.Input;
import Engine.io.Window;

public class Init {
    public void init(String windowTitle, float bgcR, float bgcG, float bgcB) {
        Input.input();
        Window window = new Window();
        window.window(windowTitle);
        window.setBgc(bgcR, bgcG, bgcB);
        window.create();
    }
}
