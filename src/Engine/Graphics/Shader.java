package Engine.Graphics;

import Engine.Utils.FileUtils;

import static org.lwjgl.opengl.GL20.*;

public record Shader(String vFP, String fFP) {
    private static int pID;

    public void create() {
        String vF = FileUtils.loadAsString(vFP);
        String fF = FileUtils.loadAsString(fFP);

        pID = glCreateProgram();

        int vID = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vID, vF);
        glCompileShader(vID);
        if (glGetShaderi(vID, GL_COMPILE_STATUS) == GL_FALSE)
            throw new IllegalStateException("SHADER ERROR: Vertex Shader: " +
                    glGetShaderInfoLog(vID));

        int fID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fID, fF);
        glCompileShader(fID);
        if (glGetShaderi(fID, GL_COMPILE_STATUS) == GL_FALSE)
            throw new IllegalStateException("SHADER ERROR: Fragment Shader: " +
                    glGetShaderInfoLog(fID));

        glAttachShader(pID, vID);
        glAttachShader(pID, fID);

        glLinkProgram(pID);
        if (glGetProgrami(pID, GL_LINK_STATUS) == GL_FALSE)
            throw new IllegalStateException("SHADER ERROR: Program Linking: " +
                    glGetShaderInfoLog(pID));

        glValidateProgram(pID);
        if (glGetProgrami(pID, GL_VALIDATE_STATUS) == GL_FALSE)
            throw new IllegalStateException("SHADER ERROR: Program Validating: " +
                    glGetShaderInfoLog(pID));

        glDeleteShader(vID);
        glDeleteShader(fID);
    }

    public void bind() {
        glUseProgram(pID);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void destroy() {
        glDeleteProgram(pID);
    }
}
