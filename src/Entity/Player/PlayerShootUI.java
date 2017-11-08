package Entity.Player;

import bases.GameObject;
import bases.Vector2D;
import bases.renderers.ImageRenderer;

import java.awt.*;

public class PlayerShootUI extends GameObject{
    Vector2D velocity;
    ImageRenderer renderer;
    ImageRenderer renderer2;

    public PlayerShootUI() {
        velocity = new Vector2D();
        renderer = new ImageRenderer("assets/images/Player/UI.png");
        renderer2 = new ImageRenderer("assets/images/Player/power bar.png");
    }

    public Vector2D getVelocity() {
        return velocity;
    }


    @Override
    public void render(Graphics2D g2d, ViewPort viewPort) {
        for (int i = 0; i < PlayerShoot.count; i++){
            g2d.drawImage(renderer.image, (int) position.x + i *8 , (int) position.y - 20 , null);
        }
        g2d.drawImage(renderer2.image, (int) position.x, (int) position.y - 20, null);
    }

}
