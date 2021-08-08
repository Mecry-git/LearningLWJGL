package Engine.Graphics;

import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public record Mesh(Vertex[] vertices, int[] indices) {
    private static int vao, pbo, ibo;

    public void create() {
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        FloatBuffer posBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
        float[] posData = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            posData[i * 3] = vertices[i].getPos().x;
            posData[i * 3 + 1] = vertices[i].getPos().y;
            posData[i * 3 + 2] = vertices[i].getPos().z;
        }
        posBuffer.put(posData).flip();

        pbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, pbo);
        glBufferData(GL_ARRAY_BUFFER, posData, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT,
                false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        IntBuffer indBuffer = MemoryUtil.memAllocInt(indices.length);
        indBuffer.put(indices).flip();

        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indBuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public Vertex[] getVertices() {
        return vertices;
    }
    public int[] getIndices() {
        return indices;
    }
    public int getVAO() {
        return vao;
    }
    public int getPBO() {
        return pbo;
    }
    public int getIBO() {
        return ibo;
    }
}
