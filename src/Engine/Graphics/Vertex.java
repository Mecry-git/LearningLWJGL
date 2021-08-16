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

    public float[] getPosFloatArray(Vertex @NotNull [] vertexes) {
        float[] fTargets = new float[vertexes.length * 3];
        for (int i = 0; i < vertexes.length; i ++) {
            fTargets[i * 3] = vertexes[i].pos.x;
            fTargets[i * 3 + 1] = vertexes[i].pos.y;
            fTargets[i * 3 + 2] = vertexes[i].pos.z;
        }
        return fTargets;
    }
    public float[] getColFloatArray(Vertex @NotNull [] vertexes) {
        float[] fTargets = new float[vertexes.length * 3];
        for (int i = 0; i < vertexes.length; i ++) {
            fTargets[i * 3] = vertexes[i].col.x;
            fTargets[i * 3 + 1] = vertexes[i].col.y;
            fTargets[i * 3 + 2] = vertexes[i].col.z;
        }
        return fTargets;
    }
    public float[] getTexFloatArray(Vertex @NotNull [] vertexes) {
        float[] fTargets = new float[vertexes.length * 2];
        for (int i = 0; i < vertexes.length; i ++) {
            fTargets[i * 2] = vertexes[i].tex.x;
            fTargets[i * 2 + 1] = vertexes[i].tex.y;
        }
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
