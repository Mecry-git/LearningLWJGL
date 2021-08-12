package Engine.Graphics;

import static org.lwjgl.opengl.GL30.*;

public record Renderer(Shader shader) {
    public void renderMesh(Mesh mesh) {
        shader.bind();

        // Draw the mesh
        glBindVertexArray(mesh.getVAO());
        glDrawElements(GL_TRIANGLES, mesh.getIndices().length, GL_UNSIGNED_INT, 0);

        // Restore state
        glBindVertexArray(0);

        shader.unbind();
    }
}
