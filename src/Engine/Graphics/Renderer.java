package Engine.Graphics;

import static org.lwjgl.opengl.GL30.*;

public record Renderer(Shader shader) {
    public void renderMesh(Mesh mesh) {
        shader.bind();

        glBindVertexArray(Mesh.vao);
        glDrawElements(GL_TRIANGLES, mesh.getIndices().length, GL_UNSIGNED_INT, 0);

        glBindVertexArray(0);

        shader.unbind();
    }
}
