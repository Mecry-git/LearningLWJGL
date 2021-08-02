package Render;

import Engine.io.Input;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_RIGHT;

public class ActionButtons {
    public boolean actionButtons() {
        if (Input.isButtonPressed(GLFW_MOUSE_BUTTON_RIGHT)) {
            System.out.println("Mouse Right button clicked!\n" +
                    "Success to setShouldClose!");
            return true;
        }
        return false;
    }
}
