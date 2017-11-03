package Tilemap;

import Entity.Player.ViewPort;
import bases.GameObject;
import bases.Vector2D;
import bases.renderers.ImageRenderer;

import java.awt.*;

public class Background extends GameObject{
    ImageRenderer renderer;
    Vector2D velocity;

    public Background(String url) {
        this.position.set(0, 0);
        renderer = new ImageRenderer(url);
        velocity = new Vector2D();
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    @Override
    public void render(Graphics2D g2d, ViewPort viewPort) {
//        g2d.drawImage(renderer.image, (int)position.x, (int)position.y, null);
//        if (position.x < 0 ) {
//            g2d.drawImage(renderer.image, (int)position.x + 800, (int)position.y, null);
//        }
//        if (position.x > 0) {
//            g2d.drawImage(renderer.image, (int)position.x - 800, (int)position.y, null);
//        }

        g2d.drawImage(renderer.image, (int)position.x, (int)position.y, null);
        g2d.drawImage(renderer.image, (int)position.x + 800, (int)position.y , null);
    }

    @Override
    public void run(Vector2D parentPosition) {
//        this.position.subtractBy(20, 0);
        if (position.x > -800){
            this.position.subtractBy(velocity.x * 0.1f, 0);
        }
        else {
            System.out.println("oke");
            position.x = 0;
        }
        System.out.println(position.x);
    }
}
