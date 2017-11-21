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
        rightAnimation = new Animation(10,
                SpriteUtils.loadImage("assets/images/Enemies/flowerwolf/flowerwolf_right_0.png"),
                SpriteUtils.loadImage("assets/images/Enemies/flowerwolf/flowerwolf_right_1.png"),
                SpriteUtils.loadImage("assets/images/Enemies/flowerwolf/flowerwolf_right_2.png")
        );

        leftAnimation = new Animation(10,
                SpriteUtils.loadImage("assets/images/Enemies/flowerwolf/flowerwolf_left_0.png"),
                SpriteUtils.loadImage("assets/images/Enemies/flowerwolf/flowerwolf_left_1.png")
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
