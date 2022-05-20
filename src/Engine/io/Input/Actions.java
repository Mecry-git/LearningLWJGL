package Engine.io.Input;

import Engine.Maths.Vector3F;
import Engine.Scenes.Objects.Cameras.Camera;
import Engine.io.OutputPrint.Terminal;
import Main.Main;

import java.awt.*;

import static Engine.io.Input.Input.*;
import static Main.Main.window;
import static org.lwjgl.glfw.GLFW.*;

public class Actions {

    public static void checkCamMoveKeys(Camera camera) {
        //Move camera
        if (!window.isFS()) {
            if (isKeyPressed(camera.getCamMovementKey(0))) {
                Main.camera.pos.z -= Main.camera.camMoveSpeed;
            }
            if (isKeyPressed(camera.getCamMovementKey(1)))
                Main.camera.pos.z += Main.camera.camMoveSpeed;
            if (isKeyPressed(camera.getCamMovementKey(2)))
                Main.camera.pos.y -= Main.camera.camMoveSpeed;
            if (isKeyPressed(camera.getCamMovementKey(3)))
                Main.camera.pos.y += Main.camera.camMoveSpeed;
            if (isKeyPressed(camera.getCamMovementKey(5)))
                Main.camera.pos.x += Main.camera.camMoveSpeed;
            if (isKeyPressed(camera.getCamMovementKey(4)))
                Main.camera.pos.x -= Main.camera.camMoveSpeed;

            if (window.isRotKey()) {
                if (isKeyPressed(camera.getCamMovementKey(6)))
                    Main.camera.rot.x += Main.camera.camRotSpeed * 2;
                if (isKeyPressed(camera.getCamMovementKey(7)))
                    Main.camera.rot.x -= Main.camera.camRotSpeed * 2;
                if (isKeyPressed(camera.getCamMovementKey(8)))
                    Main.camera.rot.z -= Main.camera.camRotSpeed * 2;
                if (isKeyPressed(camera.getCamMovementKey(9)))
                    Main.camera.rot.z += Main.camera.camRotSpeed * 2;
            }
        } else {
            if (isKeyPressed(camera.getCamMovementKey(0)))
                Main.camera.pos.z -= Main.camera.camMoveSpeed / 10;
            if (isKeyPressed(camera.getCamMovementKey(1)))
                Main.camera.pos.z += Main.camera.camMoveSpeed / 10;
            if (isKeyPressed(camera.getCamMovementKey(2)))
                Main.camera.pos.y -= Main.camera.camMoveSpeed / 10;
            if (isKeyPressed(camera.getCamMovementKey(3)))
                Main.camera.pos.y += Main.camera.camMoveSpeed / 10;
            if (isKeyPressed(camera.getCamMovementKey(5)))
                Main.camera.pos.x += Main.camera.camMoveSpeed / 10;
            if (isKeyPressed(camera.getCamMovementKey(4)))
                Main.camera.pos.x -= Main.camera.camMoveSpeed / 10;

            if (window.isRotKey()) {
                if (isKeyPressed(camera.getCamMovementKey(6)))
                    Main.camera.rot.x += Main.camera.camRotSpeed / 5;
                if (isKeyPressed(camera.getCamMovementKey(7)))
                    Main.camera.rot.x -= Main.camera.camRotSpeed / 5;
                if (isKeyPressed(camera.getCamMovementKey(8)))
                    Main.camera.rot.z -= Main.camera.camRotSpeed / 5;
                if (isKeyPressed(camera.getCamMovementKey(9)))
                    Main.camera.rot.z += Main.camera.camRotSpeed / 5;
            }
        }
    }

    public static void keysChanged(int key, int action) {
        //Set ESC to close
        if (key == GLFW_KEY_ESCAPE ) {
            if (action == GLFW_PRESS) {
                Terminal.printKeyEscPressed();
            }
            if (action == GLFW_RELEASE) {
                window.setShouldClose(true);
            }
        }
        //CMD print device part
        if (key == GLFW_KEY_F3) {
            if (action == GLFW_PRESS) {
                Terminal.printKeyF3State(true);
            }
            if (action == GLFW_RELEASE) {
                Terminal.printKeyF3State(false);
            }
        }
        if (isKeyPressed(GLFW_KEY_F3)  &&  key != GLFW_KEY_F3) {
            if (action == GLFW_PRESS) {
                Terminal.printKeysState(key, true);
            }
            if (action == GLFW_RELEASE) {
                Terminal.printKeysState(key, false);
            }

            if ((isKeyPressed(GLFW_KEY_LEFT_CONTROL) ||
                    isKeyPressed(GLFW_KEY_RIGHT_CONTROL)) &&
                    (isKeyPressed(GLFW_KEY_LEFT_ALT) ||
                            isKeyPressed(GLFW_KEY_RIGHT_ALT))) {
                    if (key == GLFW_KEY_H  &&  action == GLFW_PRESS)
                        //Press "F3 + Control + Alt + H"
                        Terminal.printHelperPanel();
            }
        }
        //set F11 to setFullScreen
        if (key == GLFW_KEY_F11  &&  action == GLFW_PRESS) window.chFS();

        //set "F8 + R or B" to change bgc
        if (key == GLFW_KEY_F8) {
            if (action != GLFW_RELEASE) {
                if (action == GLFW_PRESS)
                    Terminal.printKeyF8State(true);
            }
            if (action == GLFW_RELEASE)
                Terminal.printKeyF8State(false);
        }
        if (isKeyPressed(GLFW_KEY_F8)) {
            if (key == GLFW_KEY_C  &&  action == GLFW_PRESS)
                if (window.isBgcBlack()) {
                    Terminal.printBackgroundChanged("red");
                    window.setBgc(new Vector3F(1.0f, 0.0f, 0.0f));
                } else {
                    Terminal.printBackgroundChanged("black");
                    window.setBgc(new Vector3F());
                }
        }

        //Change the way to change that camera rotation
        if ((isKeyPressed(GLFW_KEY_LEFT_CONTROL) || isKeyPressed(GLFW_KEY_RIGHT_CONTROL))
        && (isKeyPressed(GLFW_KEY_LEFT_ALT) || isKeyPressed(GLFW_KEY_RIGHT_ALT) &&
                isKeyPressed(GLFW_KEY_P))) window.chRotFrom();
    }
    public static void buttonsChanged(int button, int action) {
        if (isKeyPressed(GLFW_KEY_F3)) {
            if (action != GLFW_RELEASE) {
                if (button == GLFW_MOUSE_BUTTON_LEFT)
                    Terminal.printMouseButtonsState(0, true);
                if (button == GLFW_MOUSE_BUTTON_RIGHT)
                    Terminal.printMouseButtonsState(2, true);
                if (button == GLFW_MOUSE_BUTTON_MIDDLE)
                    Terminal.printMouseButtonsState(1, true);
            } else {
                if (button == GLFW_MOUSE_BUTTON_LEFT)
                    Terminal.printMouseButtonsState(0, false);
                if (button == GLFW_MOUSE_BUTTON_RIGHT)
                    Terminal.printMouseButtonsState(2, false);
                if (button == GLFW_MOUSE_BUTTON_MIDDLE)
                    Terminal.printMouseButtonsState(1, false);
            }
        }
    }
    public static void cursorPosChanged(Point mousePos) {
        if (isKeyPressed(GLFW_KEY_F3))
            Terminal.printMousePositionState(mousePos);

        if (!window.isRotKey()) {
            Main.camera.rot.z -= ((double) window.getSize().width / 2 - (double) mousePos.x) / 10 * Main.camera.camRotSpeed;
            double cursorXMoved = ((double) window.getSize().height / 2 - (double) mousePos.y) / 10 * Main.camera.camRotSpeed;

            if (!((Main.camera.rot.x - cursorXMoved >= 90) ||
                    (Main.camera.rot.x - cursorXMoved <= -90)))
                Main.camera.rot.x += cursorXMoved;


            glfwSetCursorPos(window.getWindowID(), (double) window.getSize().width / 2, (double) window.getSize().height / 2);
        }
    }
    public static void mouseScrollChanged(Point mouseScrollPos) {
        if (isKeyPressed(GLFW_KEY_F3)) {
            Terminal.printMouseScrollState(mouseScrollPos);
        }
    }
}
