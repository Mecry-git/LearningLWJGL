package Engine.io.Window;

import Engine.io.Input.Callbacks;

import static Main.Main.window;

public class Init {
    public Init() {
        Callbacks.initCallbacks();
        window.create();
    }
}
