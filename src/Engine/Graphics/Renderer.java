package Engine.Graphics;

import Engine.Maths.Matrix4F;
import Engine.Objects.ProgObj;
import Engine.io.Window.Window;
import org.jetbrains.annotations.NotNull;

import static org.lwjgl.opengl.GL30.*;

public record Renderer(Window window, Shader shader) {
    public void renderMesh(@NotNull ProgObj progObj) {
        glBindVertexArray(Mesh.vao);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, Mesh.ibo);
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, progObj.mesh.getMaterial().textureID);
        shader.bind();
        shader.setUni("model", Matrix4F.traF(progObj.pos, progObj.rot, progObj.scale));
        //shader.setUni("view", Matrix4F.view(Main.camera.pos, Main.camera.rot));
        shader.setUni("prjtn", window.prjtnMat);
        glDrawElements(GL_TRIANGLES, progObj.mesh.getIndices().length, GL_UNSIGNED_INT, 0);
        shader.unbind();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glBindVertexArray(0);
    }
}
