package Engine.Graphics;

import Engine.Maths.Vector2F;
import Engine.Maths.Vector3F;
import org.jetbrains.annotations.NotNull;

public class Vertex {
    public Vector3F pos, col;
    public Vector2F tex;

    public Vertex() {
        this (new Vector3F(), new Vector3F(), new Vector2F());
    }
    public Vertex(Vector3F pos, Vector3F cal, Vector2F tex) {
        this.pos = pos;
        this.col = cal;
        this.tex = tex;
    }

    public float[] getPosFloatArray(@NotNull Vertex vertex) {
        float[] fTargets = new float[3];

        fTargets[0] = vertex.pos.x;
        fTargets[1] = vertex.pos.y;
        fTargets[2] = vertex.pos.z;

        return fTargets;
    }
    public float[] getColFloatArray(@NotNull Vertex vertex) {
        float[] fTargets = new float[3];

        fTargets[0] = vertex.col.x;
        fTargets[1] = vertex.col.y;
        fTargets[2] = vertex.col.z;

        return fTargets;
    }
    public float[] getTexFloatArray(@NotNull Vertex vertex) {
        float[] fTargets = new float[2];

        fTargets[0] = vertex.tex.x;
        fTargets[1] = vertex.tex.y;

        return fTargets;
    }
    public Vertex[] toFloatArray(float @NotNull [] poses, float[] cols, float[] tex) {
        Vertex[] vTargets = new Vertex[poses.length / 3];
        for (int i = 0; i < vTargets.length; i ++) {
            vTargets[i] = new Vertex();

            vTargets[i].pos.x = poses[i * 3];
            vTargets[i].pos.y = poses[i * 3 + 1];
            vTargets[i].pos.z = poses[i * 3 + 2];

            vTargets[i].col.x = cols[i * 3];
            vTargets[i].col.y = cols[i * 3 + 1];
            vTargets[i].col.z = cols[i * 3 + 2];

            vTargets[i].tex.x = tex[i * 2];
            vTargets[i].tex.y = tex[i * 2 + 1];

        }
        return vTargets;
    }
}
