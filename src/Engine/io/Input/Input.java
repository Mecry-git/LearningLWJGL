package Engine.io.Input;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import java.awt.*;

import static Engine.io.Input.InputActions.keysChanged;
import static Engine.io.Input.InputActions.buttonsChanged;
import static Engine.io.Input.InputActions.cursorPosChanged;
import static org.lwjgl.glfw.GLFW.*;

public class Input {
    private static final boolean[] keys = new boolean[GLFW_KEY_LAST];
    private static final boolean[] buttons = new boolean[GLFW_MOUSE_BUTTON_LAST];
    private static Point mousePos;

    private static volatile GLFWKeyCallback keyboard;
    private static volatile GLFWCursorPosCallback mouseMove;
    private static volatile GLFWMouseButtonCallback mouseButtons;

    public static void input() {
        keyboard = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keys[key] = action != GLFW_RELEASE;
                keysChanged(key, action);
            }
        };

        mouseButtons = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                buttons[button] = action != GLFW_RELEASE;
                buttonsChanged(button, action);
            }
        };

        mouseMove = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xPos, double yPos) {
                mousePos = new Point((int)xPos, (int)yPos);
                cursorPosChanged(mousePos);
            }
        };
    }

    public static GLFWKeyCallback getKeyboardKeyCallback() {
        return keyboard;
    }
    public static GLFWCursorPosCallback getCursorPosCallback() {
        return mouseMove;
    }
    public static GLFWMouseButtonCallback getMouseButtons() {
        return mouseButtons;
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

    public void destroy() {
        keyboard.free();
        mouseMove.free();
        mouseButtons.free();
    }
}
