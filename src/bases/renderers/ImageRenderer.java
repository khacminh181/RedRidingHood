package bases.renderers;

import bases.Vector2D;
import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageRenderer implements Renderer{
    public BufferedImage image;

    public ImageRenderer(BufferedImage image) {
        this.image = image;
    }

    public ImageRenderer(String url) {
        this.image = SpriteUtils.loadImage(url);
    }

    public void render(Graphics g, Vector2D position) {
        if (image != null) {
            g.drawImage(
                    image,
                    (int)(position.x - image.getWidth() / 2),
                    (int)(position.y - image.getHeight() / 2),
                    null);
        }
    }
}
