package Entity.Boss;

import Entity.Enemy.PlayerDamage;
import Entity.Player.Player;
import Entity.Player.ViewPort;
import bases.GameObject;
import bases.ParticleEffect;
import bases.Vector2D;
import bases.action.Action;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import tklibs.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ghost extends GameObject implements PhysicsBody{
    BoxCollider boxCollider;
    PlayerDamage playerDamage;

    public Ghost() {
        boxCollider = new BoxCollider(30, 30);
        this.renderer = new ImageRenderer("assets/images/Enemies/wolf.png");
        this.playerDamage = new PlayerDamage();
        this.children.add(this.boxCollider);

    }

    public void addParticle() {
        ParticleEffect particleEffect = new ParticleEffect();
        particleEffect.position.set(this.position);
        GameObject.add(particleEffect);
//        ParticleEffect particleEffect = GameObject.recycle(ParticleEffect.class);
//        particleEffect.position.set(this.position);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        position.addUp(0, 2);
        this.playerDamage.run(this);
        deactiveIfNeeded();
    }

    private void deactiveIfNeeded() {
        if (position.y > 600) {
            isActive = false;
        }
    }

    public void getHit() {
        isActive = false;
        for (int i = 0; i < 5; i++) {
            addParticle();
        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    @Override
    public Vector2D getVelocity() {
        return new Vector2D();
    }


}
