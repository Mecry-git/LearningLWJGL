package Engine.io.Input;

import java.awt.*;

import static org.lwjgl.glfw.GLFW.*;

public class Input {
    private static final boolean[] keys = new boolean[GLFW_KEY_LAST];
    private static final boolean[] buttons = new boolean[GLFW_MOUSE_BUTTON_LAST];
    private static Point mousePos;
    private static Point mouseScrollPos;

    public static void setKeys(int num, boolean key) {
        keys[num] = key;
    }
    public static void setButtons(int num, boolean button) {
        buttons[num] = button;
    }

    public static boolean isKeyPressed(int key) {
        return keys[key];
    }
    public static boolean isButtonPressed(int button) {
        return buttons[button];
    }

    public static Point getMousePos() {
        return mousePos;
    }
    public static void setMousePos(Point mousePos) {
        Input.mousePos = mousePos;
    }

    public static Point getMouseScrollPos() {
        return mouseScrollPos;
    }
    public static void setMouseScrollPos(Point mouseScrollPos) {
        Input.mouseScrollPos = mouseScrollPos;
    }
}
