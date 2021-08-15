package Engine.Objects;

import Engine.Graphics.Mesh;
import Engine.Maths.Vector3F;

public class ProgObj {
    public Vector3F pos, rot, scale;
    public Mesh mesh;

    public ProgObj(Vector3F pos, Vector3F rot, Vector3F scale, Mesh mesh) {
        this.pos = pos;
        this.rot = rot;
        this.scale = scale;
        this.mesh = mesh;
    }
}
