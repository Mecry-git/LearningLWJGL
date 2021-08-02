package Render;

import Engine.io.Input;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;

public class ActionKeys {
    public boolean actionKeys() {
        if (Input.isKeyPressed(GLFW_KEY_ESCAPE)) {
            System.out.println("Pressed ESCape!\n" +
                    "Success to setShouldClose!");
            return true;
        }
        return false;
    }
}
