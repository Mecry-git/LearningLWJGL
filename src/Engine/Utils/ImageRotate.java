package Engine.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageRotate {
    public static Image rotateImage90(Image image) {
        BufferedImage bufferedimage = ImageToBufferedImage(image);
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d =
                (img = new BufferedImage(h, w, type)).createGraphics()
        ).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(270), (float)w / 2, (float)h / 2 +
                (float)(w - h) / 2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();

        return BufferedImageToImage(img);

    }
    public static Image imageMisro(Image image) {
        try {
            BufferedImage bufferedimage = ImageToBufferedImage(image);

            int w = bufferedimage.getWidth();
            int h = bufferedimage.getHeight();

            int[][] datas = new int[w][h];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    datas[j][i] = bufferedimage.getRGB(j, i);
                }
            }
            int[][] tmps = new int[w][h];
            for (int i = 0; i < h; i++) {
                for (int j = 0, b = w - 1; j < w; j++, b--) {
                    tmps[b][i] = datas[j][i];
                }
            }
            for (int i = 0; i < h; i++){
                for (int j = 0; j<w ;j++){
                    bufferedimage.setRGB(j, i, tmps[j][i]);
                }
            }

            return bufferedimage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage ImageToBufferedImage(Image image) {
        BufferedImage bufferedimage = new BufferedImage
                (image.getWidth(null), image.getHeight(null),BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedimage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bufferedimage;
    }
    public static Image BufferedImageToImage(BufferedImage b) {
        return b;
    }
}
