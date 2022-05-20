package Engine.Scenes.Objects.Cameras;

import Engine.io.Input.Keys.KeySets;

import static org.lwjgl.glfw.GLFW.*;

public class Camera {
    public static final KeySets DefCamMovement = new KeySets(new int[] {
            GLFW_KEY_W, GLFW_KEY_S, GLFW_KEY_A, GLFW_KEY_D,
            GLFW_KEY_LEFT_SHIFT, GLFW_KEY_SPACE,
            GLFW_KEY_U, GLFW_KEY_J, GLFW_KEY_H, GLFW_KEY_K
    });

    private final KeySets camMovement;

    public Camera(KeySets camMovement) {
        this.camMovement = camMovement;
    }

    public int getCamMovementKey(int index) {
        return camMovement.getKey(index);
    }
    public void setCamMovementKey(int index, int key) {
        camMovement.setKey(index, key);
    }
}
