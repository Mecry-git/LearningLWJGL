package Engine.Objects;

import Engine.Graphics.Mesh;
import Engine.Maths.Vector3F;

public class ProgOb {
    public Vector3F pos, rot, scale;
    public Mesh mesh;

    public ProgOb(Vector3F pos, Vector3F rot, Vector3F scale, Mesh mesh) {
        this.pos = pos;
        this.rot = rot;
        this.scale = scale;
        this.mesh = mesh;
    }
}
