package Entity.HUD;

import Entity.Player.ViewPort;
import bases.GameObject;
import bases.renderers.ImageRenderer;

import java.awt.*;

public class HP extends GameObject{
    ImageRenderer rendererHP;
    ImageRenderer rendererMP;
    public int hP;

    public HP() {
        this.position.set(0, 30);
        rendererHP = new ImageRenderer("assets/images/HUD/HP.png");
    }

    @Override
    public void render(Graphics2D g2d, ViewPort viewPort) {
        for (int i = 0; i < hP; i++){
            g2d.drawImage(rendererHP.image, (int) position.x + i * 16, (int) position.y, null);
        }




    }
}
