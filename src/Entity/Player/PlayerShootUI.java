package Entity.Player;

import bases.GameObject;
import bases.Vector2D;

import java.awt.*;

public class PlayerShootUI extends GameObject{
    Vector2D velocity;

    public PlayerShootUI() {
        velocity = new Vector2D();
    }

    public Vector2D getVelocity() {
        return velocity;
    }


    @Override
    public void render(Graphics2D g2d, ViewPort viewPort) {
        System.out.println(position.x);

        g2d.setColor(Color.RED);
        //g2d.drawLine(100,100 , 200, 100);
        g2d.drawLine((int) position.x , (int) position.y - 40, (int) position.x + PlayerShoot.count, (int)position.y - 40);
    }

}
