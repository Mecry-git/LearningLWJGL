package Engine.io;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static Engine.io.Input.Actions.*;
import static org.lwjgl.glfw.GLFW.*;
import static Engine.io.Window.Window.*;
import static Engine.io.Input.Input.*;

public class Callbacks {
    private static GLFWWindowSizeCallback sizeCallback;
    private static GLFWWindowPosCallback posCallback;

    private static volatile GLFWKeyCallback keyboard;
    private static volatile GLFWCursorPosCallback mouseMove;
    private static volatile GLFWMouseButtonCallback mouseButtons;
    private static GLFWScrollCallback mouseScroll;

    public static void updateCallBacks() {
        setSizeCallback(new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                setSize(new Dimension(width, height));
                GL11.glViewport(0, 0, getSize().width, getSize().height);
            }
        });

        setPosCallback(new GLFWWindowPosCallback() {
            @Override
            public void invoke(long window, int xPos, int yPos) {
                if (!getIsFullScreen()) setPos(new Point(xPos, yPos));
            }
        });

        setKeyboard(new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                setKeys(key, action != GLFW_RELEASE);
                keysChanged(key, action);
            }
        });

        setMouseButtons(new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                setButtons(button, action != GLFW_RELEASE);
                buttonsChanged(button, action);
            }
        });

        setMouseMove(new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xPos, double yPos) {
                setMousePos(new Point((int)xPos, (int)yPos));
                cursorPosChanged(getMousePos());
            }
        });

        setMouseScroll(new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double xOffset, double yOffset) {
                mouseScrollChanged(new Point((int)xOffset, (int)yOffset));
            }
        });
    }

    public static GLFWWindowSizeCallback getSizeCallback() {
        return sizeCallback;
    }
    public static GLFWWindowPosCallback getPosCallback() {
        return posCallback;
    }
    public static void setSizeCallback(GLFWWindowSizeCallback sizeCallback) {
        Callbacks.sizeCallback = sizeCallback;
    }
    public static void setPosCallback(GLFWWindowPosCallback posCallback) {
        Callbacks.posCallback = posCallback;
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
    public static GLFWScrollCallback getMouseScroll() {
        return mouseScroll;
    }
    public static void setKeyboard(GLFWKeyCallback keyboard) {
        Callbacks.keyboard = keyboard;
    }
    public static void setMouseMove(GLFWCursorPosCallback mouseMove) {
        Callbacks.mouseMove = mouseMove;
    }
    public static void setMouseButtons(GLFWMouseButtonCallback mouseButtons) {
        Callbacks.mouseButtons = mouseButtons;
    }
    public static void setMouseScroll(GLFWScrollCallback mouseScroll) {
        Callbacks.mouseScroll = mouseScroll;
    }

    public static void destroy() {
        sizeCallback.free();
        posCallback.free();

        keyboard.free();
        mouseMove.free();
        mouseButtons.free();
        mouseScroll.free();
    }
}
