package Entity.Boss;

import Entity.Enemy.NormalWolf;
import Entity.Enemy.PlayerDamage;
import bases.GameObject;
import bases.ParticleEffect;
import bases.Vector2D;
import bases.action.*;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import tklibs.AudioUtils;
import tklibs.Utils;

import javax.sound.sampled.Clip;
import java.util.Random;

public class Boss extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;
    public int hP = 20;

    PlayerDamage playerDamage;

    Clip clip;

    Random r = new Random();

    BossAnimator bossAnimator;
    public boolean isShooting;
    int x;

    public Boss() {
        super();
        bossAnimator = new BossAnimator();
        this.renderer = bossAnimator;
        this.boxCollider = new BoxCollider(244, 302);
        this.children.add(boxCollider);
        playerDamage = new PlayerDamage();

        Action waitAction = new ActionWait(200);
        Action waitAction1 = new ActionWait(20);

        Action shootAction = new Action() {
            @Override
            public boolean run(GameObject owner) {
                x = r.nextInt(5);
                isShooting = true;
                BossBigBullet newBossBullet = GameObject.recycle(BossBigBullet.class);
                newBossBullet.position.set(owner.position.x , owner.position.y);
                newBossBullet.velocity.set(-4 ,0);
                newBossBullet.configActions();
                clip = AudioUtils.loadSound("assets/SFX/boss-shoot.wav");
                AudioUtils.play(clip);
                return true;
            }

            @Override
            public void reset() {

            }
        };

        Action resetShoot = new Action() {
            @Override
            public boolean run(GameObject owner) {
                isShooting = false;
                return true;
            }

            @Override
            public void reset() {

            }
        };


        Action shootSequence = new ActionRepeatForever(new ActionSequence( shootAction, waitAction1, resetShoot, waitAction));
        this.addAction(shootSequence);
    }


    public void addParticle() {
        ParticleEffect particleEffect = new ParticleEffect();
        particleEffect.position.set(this.position);
        GameObject.add(particleEffect);
//        ParticleEffect particleEffect = GameObject.recycle(ParticleEffect.class);
//        particleEffect.position.set(this.position);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public Vector2D getVelocity() {
        return new Vector2D();
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        playerDamage.run(this);
        bossAnimator.run(this);
    }

    public void getHit() {
        hP--;
        clip = AudioUtils.loadSound("assets/SFX/dog_howls2.wav");
        AudioUtils.play(clip);
        if (hP <= 0) {
            isActive = false;
            for (int i = 0; i < 20; i++) {
                addParticle();
            }
        }
    }
}
