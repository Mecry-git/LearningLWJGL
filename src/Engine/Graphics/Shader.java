package Engine.Graphics;

import Engine.Maths.Matrix4F;/*
import Engine.Maths.Vector2F;
import Engine.Maths.Vector3F;*/
import Engine.Utils.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

public record Shader(String vFP, String fFP) {
    private static int pID, vID, fID;

    public void create() {
        String vF = FileUtils.loadAsString(vFP);
        String fF = FileUtils.loadAsString(fFP);

        pID = glCreateProgram();

        vID = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vID, vF);
        glCompileShader(vID);
        if (glGetShaderi(vID, GL_COMPILE_STATUS) == GL_FALSE)
            throw new IllegalStateException("\nSHADER ERROR: Vertex Shader: " +
                    glGetShaderInfoLog(vID));

        fID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fID, fF);
        glCompileShader(fID);
        if (glGetShaderi(fID, GL_COMPILE_STATUS) == GL_FALSE)
            throw new IllegalStateException("\nSHADER ERROR: Fragment Shader: " +
                    glGetShaderInfoLog(fID));

        glAttachShader(pID, vID);
        glAttachShader(pID, fID);

        glLinkProgram(pID);
        if (glGetProgrami(pID, GL_LINK_STATUS) == GL_FALSE)
            throw new IllegalStateException("\nSHADER ERROR: Program Linking: " +
                    glGetShaderInfoLog(pID));

        glValidateProgram(pID);
        if (glGetProgrami(pID, GL_VALIDATE_STATUS) == GL_FALSE)
            throw new IllegalStateException("\nSHADER ERROR: Program Validating: " +
                    glGetShaderInfoLog(pID));
    }

    public int getUniLoc(String name) {
        return glGetUniformLocation(pID, name);
    }
    /*
    public void setUni(String name, float value) {
        glUniform1f(getUniLoc(name), value);
    }
    public void setUni(String name, int value) {
        glUniform1i(getUniLoc(name), value);
    }
    public void setUni(String name, boolean value) {
        glUniform1i(getUniLoc(name), value ? 1 : 0);
    }
    public void setUni(String name, @NotNull Vector2F value) {
        glUniform2f(getUniLoc(name), value.x, value.y);
    }
    public void setUni(String name, @NotNull Vector3F value) {
        glUniform3f(getUniLoc(name), value.x, value.y, value.z);
    }*/
    public void setUni(String name, @NotNull Matrix4F value) {
        FloatBuffer matrix = MemoryUtil.memAllocFloat(Matrix4F.SIZE * Matrix4F.SIZE);
        matrix.put(value.getAll()).flip();
        glUniformMatrix4fv(getUniLoc(name), true, matrix);
    }

    public void bind() {
        glUseProgram(pID);
    }
    public void unbind() {
        glUseProgram(0);
    }

    public void destroy() {
        glDetachShader(pID, vID);
        glDetachShader(pID, fID);

        glDeleteShader(vID);
        glDeleteShader(fID);
        glDeleteProgram(pID);
    }
}
