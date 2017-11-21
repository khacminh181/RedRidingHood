package Entity.Boss;

import Entity.Scenes.BossScene;
import bases.Vector2D;
import bases.renderers.Animation;
import bases.renderers.Renderer;
import tklibs.SpriteUtils;

import java.awt.*;


public class BossAnimator implements Renderer {

    Animation currentAnimation;
    Animation straightAnimation;
    Animation shootAnimation;
    Animation callAnimation;
    WolfSpawner wolfSpawner;
    public BossAnimator() {
        wolfSpawner = new WolfSpawner();
        straightAnimation = new Animation(20,
                SpriteUtils.loadImage("assets/images/Enemies/boss/boss_idle_0.png"),
                SpriteUtils.loadImage("assets/images/Enemies/boss/boss_idle_1.png")
        );

        shootAnimation = new Animation(10,
                SpriteUtils.loadImage("assets/images/Enemies/boss/boss_shoot_0.png")
        );
        callAnimation = new Animation(5,
                 SpriteUtils.loadImage("assets/images/Enemies/boss/boss_call_1.png"),
                SpriteUtils.loadImage("assets/images/Enemies/boss/boss_call_2.png"),
                SpriteUtils.loadImage("assets/images/Enemies/boss/boss_call_3.png"),
                SpriteUtils.loadImage("assets/images/Enemies/boss/boss_call_4.png"),
                SpriteUtils.loadImage("assets/images/Enemies/boss/boss_call_5.png"));
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
        else if (wolfSpawner.isWolf) {
            currentAnimation = callAnimation;
            wolfSpawner.isWolf = false;
        }
        else {
            currentAnimation = straightAnimation;
        }


    }
}
