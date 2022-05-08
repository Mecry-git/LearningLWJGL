package Engine.Objects;

import Engine.Maths.Vector3F;
import Engine.io.Input.Actions;

public class CamObj {
    public Vector3F pos, rot;
    public float fov, near, far, camMoveSpeed, camRotSpeed;

    public CamObj(Vector3F pos, Vector3F rot, float fov, float near, float far,
                  float camMoveSpeed, float camRotSpeed) {
        this.pos = pos;
        this.rot = rot;

        this.fov = fov;
        this.near = near;
        this.far = far;

        this.camMoveSpeed = camMoveSpeed;
        this.camRotSpeed = camRotSpeed;
    }

    public void update() {
        if (rot.y > 360) rot.y -= 360;
        if (rot.y < -360) rot.y += 360;
        if (rot.z > 360) rot.z -= 360;
        if (rot.z < -360) rot.z += 360;

        Actions.checkCamMoveKeys();
    }
}
