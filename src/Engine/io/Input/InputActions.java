package Engine.io.Input;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;

public class InputActions {
    public static boolean actionKeys() {
        if (Input.isKeyPressed(GLFW_KEY_ESCAPE)) {
            System.out.println("Pressed ESCape! \nClose game!");
            return true;
        }
        return false;
    }
    public static void actionButtons() {
    }
    public static void actionCursorPos() {
    }
}
