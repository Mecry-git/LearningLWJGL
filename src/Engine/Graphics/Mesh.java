package Engine.Graphics;

import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public record Mesh(Vertex[] vertices, int[] indices, Material material) {
    public static int vao, pbo, cbo, tbo, ibo;

    public void create() {
        material.create();

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        FloatBuffer posBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
        posBuffer.put(new Vertex().getPosFloatArray(vertices)).flip();
        pbo = storeData(posBuffer, 0, 3);

        FloatBuffer colBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
        colBuffer.put(new Vertex().getColFloatArray(vertices)).flip();
        cbo = storeData(colBuffer, 1, 3);

        FloatBuffer texBuffer = MemoryUtil.memAllocFloat(vertices.length * 2);
        texBuffer.put(new Vertex().getTexFloatArray(vertices)).flip();
        tbo = storeData(texBuffer, 2, 2);

        IntBuffer indBuffer = null;
        try {
            ibo = glGenBuffers();
            indBuffer = MemoryUtil.memAllocInt(indices.length);
            indBuffer.put(indices).flip();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indBuffer, GL_STATIC_DRAW);
        } finally {
            if (indBuffer != null) {
                MemoryUtil.memFree(indBuffer);
            }
        }

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    private int storeData(FloatBuffer buffer, int index, int size) {
        int bufferID;
        try {
            bufferID = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, bufferID);
            glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
            glEnableVertexAttribArray(0);
            glVertexAttribPointer(index, size, GL_FLOAT, false,
                    0, 0);
        } finally {
            if (buffer != null) {
                MemoryUtil.memFree(buffer);
            }
        }

        return bufferID;
    }

    public Material getMaterial() {
        return material;
    }

    public int[] getIndices() {
        return indices;
    }

    public void destroy() {
        material.Destroy();

        glDeleteVertexArrays(vao);

        glDeleteBuffers(pbo);
        glDeleteBuffers(cbo);
        glDeleteBuffers(tbo);
        glDeleteBuffers(ibo);
    }
}
