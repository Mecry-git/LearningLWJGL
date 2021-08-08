package Engine.Graphics;

import static org.lwjgl.opengl.GL30.*;

public record Renderer(Shader shader) {
    public void renderMesh(Mesh mesh) {
        glBindVertexArray(mesh.getVAO());
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
        shader.bind();
        glDrawElements(GL_TRIANGLES, mesh.getIndices().length, GL_FLOAT, 0);
        shader.unbind();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
    }
}
