package Engine.io.Input;

import Engine.io.Window;

import java.awt.*;

import static Engine.io.Input.Input.*;
import static org.lwjgl.glfw.GLFW.*;

public class InputActions {
    public static void updateKeys() {
    }
    public static void updateButtons() {
    }
    public static void updateCursorPos() {
    }

    public static void keysChanged(int key, int action) {
        if (key == GLFW_KEY_ESCAPE ) {
            if (action == GLFW_PRESS) {
                System.out.println("Pressed ESC! Release it to close game!");
            }
            if (action == GLFW_RELEASE) {
                System.out.println("Close game!");
                Window.shouldClose = true;
            }
        }

        if (key == GLFW_KEY_F3) {
            if (action == GLFW_PRESS) {
                System.out.println("F3 was pressed! Open the CMD print service. " +
                        "Help to press \"F3 + Control + Alt + H\" " +
                        "to open F3 help menu!");
            }
            if (action == GLFW_RELEASE) {
                System.out.println("F3 was released! Close the CMD print service!");
            }
        }
        if (isKeyPressed(GLFW_KEY_F3)  &&  key != GLFW_KEY_F3) {
            if (action == GLFW_PRESS) {
                System.out.println("Scancode " + key + " was pressed!");
            }
            if (action == GLFW_RELEASE) {
                System.out.println("Scancode " + key + " was released!");
            }

            if ((isKeyPressed(GLFW_KEY_LEFT_CONTROL) ||
                    isKeyPressed(GLFW_KEY_RIGHT_CONTROL)) &&
                    (isKeyPressed(GLFW_KEY_LEFT_ALT) ||
                            isKeyPressed(GLFW_KEY_RIGHT_ALT))) {
                if (key == GLFW_KEY_H  &&  action == GLFW_PRESS)
                    //If Press "F3 + Control + Alt + H"
                    System.out.println("""
                            You pressed "F3 + Control + Alt + H"! Open F3 help menu:\s
                            Press F3 to open CMD print service.\s
                            Press F3 and press other key to print their scancode and states.\s
                            Press F3 and press mouse buttonsto print their states.\s
                            Press F3 and move mouse to printmouse position.\s
                            Release F3 to close CMD print service.\s""");
            }
        }
    }
    public static void buttonsChanged(int button, int action) {
        if (isKeyPressed(GLFW_KEY_F3)) {
            if (action != GLFW_RELEASE) {
                if (button == GLFW_MOUSE_BUTTON_LEFT)
                    System.out.println("Mouse left button was pressed!");
                if (button == GLFW_MOUSE_BUTTON_RIGHT)
                    System.out.println("Mouse right button was pressed!");
                if (button == GLFW_MOUSE_BUTTON_MIDDLE)
                    System.out.println("Mouse middle button was pressed!");
            } else {
                if (button == GLFW_MOUSE_BUTTON_LEFT)
                    System.out.println("Mouse left button was released!");
                if (button == GLFW_MOUSE_BUTTON_RIGHT)
                    System.out.println("Mouse right button was released!");
                if (button == GLFW_MOUSE_BUTTON_MIDDLE)
                    System.out.println("Mouse middle button was released!");
            }
        }
    }
    public static void cursorPosChanged(Point mousePos) {
        if (isKeyPressed(GLFW_KEY_F3))
            System.out.println("MousePos: " + mousePos.x + " | " + mousePos.y);
    }
}
