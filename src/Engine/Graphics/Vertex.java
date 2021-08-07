package Engine.Graphics;

import Engine.Maths.Vector3F;

public class Vertex {
    public Vector3F pos;

    public Vertex(Vector3F pos) {
        this.pos = pos;
    }

    public Vector3F getPos() {
        return pos;
    }
}
