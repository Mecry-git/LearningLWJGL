package Engine.Graphics;

import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public record Mesh(Vertex[] vertices, int[] indices) {
    private static int vao, ibo;

    public void create() {
        FloatBuffer posBuffer = null;
        IntBuffer indicesBuffer = null;
        try {
            vao = glGenVertexArrays();
            glBindVertexArray(vao);

            int pbo = glGenBuffers();
            posBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
            posBuffer.put(new Vertex().getFloatArray(vertices)).flip();
            glBindBuffer(GL_ARRAY_BUFFER, pbo);
            glBufferData(GL_ARRAY_BUFFER, posBuffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(0);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

            ibo = glGenBuffers();
            indicesBuffer = MemoryUtil.memAllocInt(indices.length);
            indicesBuffer.put(indices).flip();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);
        } finally {
            if (posBuffer != null) {
                MemoryUtil.memFree(posBuffer);
            }
            if (indicesBuffer != null) {
                MemoryUtil.memFree(indicesBuffer);
            }
        }
    }

    public int[] getIndices() {
        return indices;
    }
    public int getVAO() {
        return vao;
    }
}
