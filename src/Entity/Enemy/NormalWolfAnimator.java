package Entity.Enemy;

import bases.Vector2D;
import bases.renderers.Animation;
import bases.renderers.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;

public class NormalWolfAnimator implements Renderer {
    Animation leftAnimation;
    Animation rightAnimation;
    Animation currentAnimation;

    public NormalWolfAnimator() {
        rightAnimation = new Animation(
                SpriteUtils.loadImage("assets/images/Enemies/normal_wolf/normal_wolf_move_right_1.png"),
                SpriteUtils.loadImage("assets/images/Enemies/normal_wolf/normal_wolf_move_right_2.png"),
                SpriteUtils.loadImage("assets/images/Enemies/normal_wolf/normal_wolf_move_right_3.png"),
                SpriteUtils.loadImage("assets/images/Enemies/normal_wolf/normal_wolf_move_right_4.png")
        );

        leftAnimation = new Animation(
                SpriteUtils.loadImage("assets/images/Enemies/normal_wolf/normal_wolf_move_left_1.png"),
                SpriteUtils.loadImage("assets/images/Enemies/normal_wolf/normal_wolf_move_left_2.png"),
                SpriteUtils.loadImage("assets/images/Enemies/normal_wolf/normal_wolf_move_left_3.png"),
                SpriteUtils.loadImage("assets/images/Enemies/normal_wolf/normal_wolf_move_left_4.png")
        );
        currentAnimation = rightAnimation;
    }

    public void run(NormalWolf owner) {
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
