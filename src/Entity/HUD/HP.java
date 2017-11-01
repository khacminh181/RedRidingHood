package Entity.HUD;

import Entity.Player.Player;
import Entity.Player.PlayerShoot;
import Entity.Player.ViewPort;
import bases.GameObject;
import bases.renderers.ImageRenderer;

import java.awt.*;

public class HP extends GameObject{
    ImageRenderer rendererHP;
    ImageRenderer rendererMP;
    int MP = 0;
    public HP() {
        this.position.set(0, 30);
        rendererHP = new ImageRenderer("assets/images/HUD/HP.png");
        rendererMP = new ImageRenderer("assets/images/HUD/MP.png");
    }

    @Override
    public void render(Graphics2D g2d, ViewPort viewPort) {
        for (int i = 0; i < Player.HP; i++){
            g2d.drawImage(rendererHP.image, (int) position.x + i * 16, (int) position.y, null);
        }
        for (int i = 0; i < PlayerShoot.bulletCounter / 5 ; i++) {
            g2d.drawImage(rendererMP.image, (int) position.x + i * 20, (int) position.y + 16, null);

        }


    }
}
