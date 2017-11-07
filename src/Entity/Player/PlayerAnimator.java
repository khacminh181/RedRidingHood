package Entity.Player;

import bases.Vector2D;
import bases.renderers.Animation;
import bases.renderers.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;

public class PlayerAnimator implements Renderer{

    Animation leftAnimation;
    Animation rightAnimation;
    Animation straightAnimationRight;
    Animation straightAnimationLeft;
    Animation currentAnimation;


    public PlayerAnimator() {
        leftAnimation = new Animation(
                SpriteUtils.loadImage("assets/images/Player/left.png")

        );

        rightAnimation = new Animation(
                SpriteUtils.loadImage("assets/images/Player/RedRidingHood0.png")

        );

        straightAnimationRight = new Animation(
                SpriteUtils.loadImage("assets/images/Player/RedRidingHood1.png")
        );

        straightAnimationLeft = new Animation(
                SpriteUtils.loadImage("assets/images/Player/RedRidingHood1left.png")
        );

        currentAnimation = straightAnimationRight;
    }

    public void run(Player player) {
        //Get player velocity
        Vector2D velocity = player.velocity;

        //Based on velocity, change animation
        if (velocity.x < 0) {
            currentAnimation = leftAnimation;

        }
        else if (velocity.x > 0) {
            currentAnimation = rightAnimation;
        }
        else {
            if (!player.facingRight) {
                currentAnimation = straightAnimationRight;
            }
            else {
                currentAnimation = straightAnimationLeft;
            }
        }
    }

    @Override
    public void render(Graphics g, Vector2D position) {
        currentAnimation.render(g, position);
    }
}
