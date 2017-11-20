package Entity.Boss;

import bases.Vector2D;
import bases.renderers.Animation;
import bases.renderers.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;

public class BossAnimator implements Renderer {

    Animation currentAnimation;
    Animation straightAnimation;
    Animation shootAnimation;


    public BossAnimator() {
        straightAnimation = new Animation(10,
                SpriteUtils.loadImage("assets/images/Enemies/boss/boss.png")
        );

        shootAnimation = new Animation(10,
                SpriteUtils.loadImage("assets/images/Enemies/boss/BossShoot.png")
        );
        currentAnimation = straightAnimation;
    }

    @Override
    public void render(Graphics g, Vector2D position) {
        currentAnimation.render(g, position);
    }

    public void run(Boss owner) {
        if (owner.isShooting) {
            currentAnimation = shootAnimation;
        }
        else {
            currentAnimation = straightAnimation;
        }

    }
}
