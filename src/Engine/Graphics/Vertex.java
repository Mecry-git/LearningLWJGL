package Engine.Graphics;

import Engine.Maths.Vector3F;

public class Vertex {
    public Vector3F pos;

    public Vertex() {
        this(new Vector3F());
    }
    public Vertex(Vector3F pos) {
        this.pos = pos;
    }

    public Vertex[] floatArray(float[] fPoses) {
        Vertex[] vertices = new Vertex[fPoses.length / 3];
        for (int i = 0; i < vertices.length; i ++) {
            vertices[i] = new Vertex();
            vertices[i].pos.x = fPoses[i * 3];
            vertices[i].pos.y = fPoses[i * 3 + 1];
            vertices[i].pos.z = fPoses[i * 3 + 2];
        }
        return vertices;
    }
}
