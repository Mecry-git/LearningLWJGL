package Engine.io.Input;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import static org.lwjgl.glfw.GLFW.*;

public class Input {
    private static final boolean[] keys = new boolean[GLFW_KEY_LAST];
    private static final boolean[] buttons = new boolean[GLFW_MOUSE_BUTTON_LAST];
    private static double mouseX, mouseY;

    private static volatile GLFWKeyCallback keyboard;
    private static volatile GLFWCursorPosCallback mouseMove;
    private static volatile GLFWMouseButtonCallback mouseButtons;

    public static void input() {
        keyboard = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keys[key] = action == GLFW_PRESS;
            }
        };

        mouseButtons = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                buttons[button] = action == GLFW_PRESS;
            }
        };

        mouseMove = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mouseX = xpos;
                mouseY = ypos;
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
    public static double getMouseX() {
        return mouseX;
    }
    public static double getMouseY() {
        return mouseY;
    }

    public void destroy() {
        keyboard.free();
        mouseMove.free();
        mouseButtons.free();
    }
}
