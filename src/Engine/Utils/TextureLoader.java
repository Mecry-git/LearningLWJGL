package Engine.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.jetbrains.annotations.NotNull;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL12;

import static org.lwjgl.opengl.GL11.*;

public class TextureLoader {
    public int getTextureID(@NotNull BufferedImage image) {
        image = fixImage(image);

        int[] pixels = new int[image.getWidth() * image.getHeight()];
        pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(),
                pixels, 0, image.getWidth());

        int BYTES_PER_PIXEL = 4;
        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() *
                image.getHeight() * BYTES_PER_PIXEL);

        int pixel;
        for(int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y ++) {
                pixel = pixels[x * image.getHeight() + y];

                buffer.put((byte) ((pixel >> 16) & 0xFF));
                buffer.put((byte) ((pixel >> 8) & 0xFF));
                buffer.put((byte) (pixel & 0xFF));
                buffer.put((byte) ((pixel >> 24) & 0xFF));
            }
        }
        buffer.flip();

        int textureID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureID);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        return textureID;
    }
    private BufferedImage fixImage(BufferedImage image) {
        Image iImg = ImageRotate.imageMisro(ImageRotate.BufferedImageToImage(image));
        iImg = ImageRotate.rotateImage90(iImg);
        if (iImg == null)
            throw new IllegalStateException("\nERROR: TEXTURE: Failed to edit image!");
        image = ImageRotate.ImageToBufferedImage(iImg);

        return image;
    }

    public BufferedImage getTextureAsBI(String path) {
        URL image = TextureLoader.class.getResource(path);
        if (image == null)
            throw new IllegalStateException("\nERROR: Unable to loadImage, path: " + path);
        try {
            return ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("\nERROR: Unable to loadImage, path: " + path);
    }
}
