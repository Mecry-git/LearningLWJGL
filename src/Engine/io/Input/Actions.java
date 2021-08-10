package Engine.io.Input;

import Engine.Maths.Vector3F;
import Engine.io.Window.Window;

import java.awt.*;

import static Engine.io.Input.Input.*;
import static Main.Main.window;
import static org.lwjgl.glfw.GLFW.*;

public class Actions {
    public static void keysChanged(int key, int action) {
        //Set ESC to close
        if (key == GLFW_KEY_ESCAPE ) {
            if (action == GLFW_PRESS) {
                System.out.println("Pressed ESC! Release it to close game!");
            }
            if (action == GLFW_RELEASE) {
                window.setShouldClose(true);
            }
        }
        //CMD print device part
        if (key == GLFW_KEY_F3) {
            if (action == GLFW_PRESS) {
                System.out.println("F3 was pressed! Help to press " +
                        "\"F3 + Control + Alt + H\" " +
                        "to open F3 help menu!");
            }
            if (action == GLFW_RELEASE) {
                System.out.println("F3 was released!");
            }
        }
        if (isKeyPressed(GLFW_KEY_F3)  &&  key != GLFW_KEY_F3) {
            if (action == GLFW_PRESS) {
                System.out.println("The key which scancode " + key + " was pressed!");
            }
            if (action == GLFW_RELEASE) {
                System.out.println("The key which scancode " + key + " was released!");
            }

            if ((isKeyPressed(GLFW_KEY_LEFT_CONTROL) ||
                    isKeyPressed(GLFW_KEY_RIGHT_CONTROL)) &&
                    (isKeyPressed(GLFW_KEY_LEFT_ALT) ||
                            isKeyPressed(GLFW_KEY_RIGHT_ALT))) {
                    if (key == GLFW_KEY_H  &&  action == GLFW_PRESS)
                        //Press "F3 + Control + Alt + H"
                        System.out.println("""
                            You pressed "F3 + Control + Alt + H"! Help menu:\s
                            Press F3 to open CMD print service.\s
                            Press F3 and press other key to print their scancode and states.\s
                            Press F3 and press mouse buttons to print their states.\s
                            Press F3 and move mouse to print mouse position.\s
                            Press F3 and turn mouseScroll to print mouseScroll state.\s
                            Release F3 to close CMD print service.\s""");
            }
        }
        //set F11 to setFullScreen
        if (key == GLFW_KEY_F11  &&  action == GLFW_PRESS) window.changeFullScreen();

        //set "F8 + R or B" to change bgc
        if (key == GLFW_KEY_F8) {
            if (action != GLFW_RELEASE) {
                if (action == GLFW_PRESS)
                    System.out.println("F8 was Pressed! Press \"F8 + C\" to " +
                            "change background color!(Black or red)");
            }
            if (action == GLFW_RELEASE)
                System.out.println("F8 was Released!");
        }
        if (isKeyPressed(GLFW_KEY_F8)) {
            if (key == GLFW_KEY_C  &&  action == GLFW_PRESS)
                if (Window.isBgcBlack()) {
                    System.out.println("Change background to red!");
                    window.setBgc(new Vector3F(1.0f, 0.0f, 0.0f));
                } else {
                    System.out.println("Change background to black!");
                    window.setBgc(new Vector3F());
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
            System.out.println("MousePosition: " + mousePos.x + " | " + mousePos.y);
    }
    public static void mouseScrollChanged(Point mouseScrollPos) {
        if (isKeyPressed(GLFW_KEY_F3)) {
            if (mouseScrollPos.y > 0)
                System.out.println("MouseScroll turned up " +
                        mouseScrollPos.y + " point(s)!");
            if (mouseScrollPos.y < 0)
                System.out.println("MouseScroll turned down " +
                        -mouseScrollPos.y + " point(s)!");
        }
    }
}
