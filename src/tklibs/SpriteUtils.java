package tklibs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteUtils {

    public static BufferedImage loadImage(String url) {
        try {
            return ImageIO.read(new File(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
