package Entity.Enemy;

import bases.Vector2D;
import bases.renderers.Animation;
import bases.renderers.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;

public class FlowerWolfAnimator implements Renderer {
    Animation leftAnimation;
    Animation rightAnimation;
    Animation currentAnimation;

    public FlowerWolfAnimator() {
        rightAnimation = new Animation(
                SpriteUtils.loadImage("assets/images/Enemies/flowerwolf/wolf.png"),
                SpriteUtils.loadImage("assets/images/Enemies/flowerwolf/wolf1.png")
        );

        leftAnimation = new Animation(
                SpriteUtils.loadImage("assets/images/Enemies/flowerwolf/wolfleft.png"),
                SpriteUtils.loadImage("assets/images/Enemies/flowerwolf/wolf1left.png")
        );
        currentAnimation = rightAnimation;
    }

    public void run(FlowerWolf owner) {
        if (!owner.facingRight) {
            currentAnimation = rightAnimation;
        }
        else {
            currentAnimation = leftAnimation;
        }
    }

    @Override
    public void render(Graphics g, Vector2D position) {
        currentAnimation.render(g, position);
    }
}
