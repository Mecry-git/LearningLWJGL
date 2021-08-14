package Engine.Graphics;

import Engine.Utils.TextureLoader;
import org.lwjgl.opengl.GL13;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Material {
    private final String path;
    public Dimension size;
    public int textureID;

    public Material(String path) {
        this.path = path;
    }

    public void create() {
        BufferedImage texture = new TextureLoader().getTextureAsBI(path);

        size = new Dimension(texture.getWidth(), texture.getHeight());
        textureID = new TextureLoader().getTextureID(texture);
    }

    public void Destroy() {
        GL13.glDeleteTextures(textureID);
    }
}
