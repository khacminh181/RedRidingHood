package Entity.Player;

import bases.Vector2D;
import bases.renderers.Animation;
import bases.renderers.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;

public class PlayerAnimator implements Renderer {
    Animation leftAnimation;
    Animation rightAnimation;
    Animation straightAnimationRight;
    Animation straightAnimationLeft;
    Animation currentAnimation;
    Animation jumpRightAnimation;
    Animation jumpLeftAnimation;

    public PlayerAnimator() {
        leftAnimation = new Animation(5,
                SpriteUtils.loadImage("assets/images/Player/player_left/player_move_left_0.png"),
                SpriteUtils.loadImage("assets/images/Player/player_left/player_move_left_1.png"),
                SpriteUtils.loadImage("assets/images/Player/player_left/player_move_left_2.png"),
                SpriteUtils.loadImage("assets/images/Player/player_left/player_move_left_3.png"),
                SpriteUtils.loadImage("assets/images/Player/player_left/player_move_left_4.png"),
                SpriteUtils.loadImage("assets/images/Player/player_left/player_move_left.png")

        );

        rightAnimation = new Animation(5,
                SpriteUtils.loadImage("assets/images/Player/player_right/player_move_right_0.png"),
                SpriteUtils.loadImage("assets/images/Player/player_right/player_move_right_1.png"),
                SpriteUtils.loadImage("assets/images/Player/player_right/player_move_right_2.png"),
                SpriteUtils.loadImage("assets/images/Player/player_right/player_move_right_3.png"),
                SpriteUtils.loadImage("assets/images/Player/player_right/player_move_right_4.png"),
                SpriteUtils.loadImage("assets/images/Player/player_right/player_move_right.png")
        );
        jumpRightAnimation = new Animation(4,
                SpriteUtils.loadImage("assets/images/Player/player_right/player_jump_right_1.png"),
                SpriteUtils.loadImage("assets/images/Player/player_right/player_jump_right_2.png"),
                SpriteUtils.loadImage("assets/images/Player/player_right/player_jump_right_3.png"),
                SpriteUtils.loadImage("assets/images/Player/player_right/player_jump_right_4.png"),
                SpriteUtils.loadImage("assets/images/Player/player_right/player_jump_right_5.png"),
                SpriteUtils.loadImage("assets/images/Player/player_right/player_jump_right_6.png"),
                SpriteUtils.loadImage("assets/images/Player/player_right/player_jump_right_7.png"),
                SpriteUtils.loadImage("assets/images/Player/player_right/player_jump_right_8.png"),
                SpriteUtils.loadImage("assets/images/Player/player_right/player_jump_right_9.png"),
                SpriteUtils.loadImage("assets/images/Player/player_right/player_jump_right_10.png"),
                SpriteUtils.loadImage("assets/images/Player/player_right/player_jump_right_10.png"),
                SpriteUtils.loadImage("assets/images/Player/player_right/player_jump_right_11.png"),
                SpriteUtils.loadImage("assets/images/Player/player_right/player_move_right.png")

        );
        jumpLeftAnimation = new Animation(4,
                SpriteUtils.loadImage("assets/images/Player/player_left/player_jump_left_1.png"),
                SpriteUtils.loadImage("assets/images/Player/player_left/player_jump_left_2.png"),
                SpriteUtils.loadImage("assets/images/Player/player_left/player_jump_left_3.png"),
                SpriteUtils.loadImage("assets/images/Player/player_left/player_jump_left_4.png"),
                SpriteUtils.loadImage("assets/images/Player/player_left/player_jump_left_5.png"),
                SpriteUtils.loadImage("assets/images/Player/player_left/player_jump_left_6.png"),
                SpriteUtils.loadImage("assets/images/Player/player_left/player_jump_left_7.png"),
                SpriteUtils.loadImage("assets/images/Player/player_left/player_jump_left_8.png"),
                SpriteUtils.loadImage("assets/images/Player/player_left/player_jump_left_9.png"),
                SpriteUtils.loadImage("assets/images/Player/player_left/player_jump_left_10.png"),
                SpriteUtils.loadImage("assets/images/Player/player_left/player_jump_left_11.png"),
                SpriteUtils.loadImage("assets/images/Player/player_left/player_move_left.png")
        );

        straightAnimationRight = new Animation(5,
                SpriteUtils.loadImage("assets/images/Player/player_right/player_move_right.png")
        );

        straightAnimationLeft = new Animation(5,
                SpriteUtils.loadImage("assets/images/Player/player_left/player_move_left.png")
        );

        currentAnimation = straightAnimationRight;
    }

    public void run(Player player) {
        //Get player velocity
        Vector2D velocity = player.velocity;

        //Based on velocity, change animation
        int currentAnimIndex = 0;
        if (velocity.y < 0 && player.facingLeft) {

            if (currentAnimation == jumpRightAnimation) {
                currentAnimIndex = currentAnimation.currentImageIndex;
            }
            currentAnimation.currentImageIndex = currentAnimIndex;
            currentAnimation = jumpLeftAnimation;
        } else if (velocity.y < 0 && !player.facingLeft) {
            //int currentAnimIndex = 0;
            if (currentAnimation == jumpLeftAnimation) {
                currentAnimIndex = currentAnimation.currentImageIndex;
            }
            currentAnimation.currentImageIndex = currentAnimIndex;
            currentAnimation = jumpRightAnimation;

        } else if (velocity.x < 0 && velocity.y == 0) {
            currentAnimation = leftAnimation;

        } else if (velocity.x > 0 && velocity.y == 0) {
            currentAnimation = rightAnimation;
        } else {
            if (!player.facingLeft) {
                currentAnimation = straightAnimationRight;
            } else {
                currentAnimation = straightAnimationLeft;
            }
        }
    }

    @Override
    public void render(Graphics g, Vector2D position) {
        currentAnimation.render(g, position);
    }
}
