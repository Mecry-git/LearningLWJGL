package Engine.Scenes.Objects.Cameras;

import Engine.io.Input.Keys.KeySets;
import org.jetbrains.annotations.NotNull;

import static org.lwjgl.glfw.GLFW.*;

public class Camera {
    public static final KeySets DefCamMovement = new KeySets(new int[] {
            GLFW_KEY_W, GLFW_KEY_S, GLFW_KEY_A, GLFW_KEY_D,
            GLFW_KEY_LEFT_SHIFT, GLFW_KEY_SPACE,
            GLFW_KEY_U, GLFW_KEY_J, GLFW_KEY_H, GLFW_KEY_K
    });

    private final KeySets camMovement = DefCamMovement;

    public Camera(@NotNull KeySets camMovement) {
        for (int i = 0; i < camMovement.getKeyNLength(); i ++)
            setCamMovementKey(i, camMovement.getKey(i));
    }

    public int getCamMovementKey(int index) {
        return camMovement.getKey(index);
    }
    public void setCamMovementKey(int index, int key) {
        camMovement.setKey(index, key);
    }
}
