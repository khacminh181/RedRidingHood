package Entity.Enemy;

import bases.GameObject;
import bases.ParticleEffect;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicsBody;
import tklibs.AudioUtils;
import tklibs.Utils;

import javax.sound.sampled.Clip;

public class FlowerWolf extends GameObject implements PhysicsBody {
    BoxCollider boxCollider;
    int hP = 5;


    boolean spellDisabled = false;
    final int COOL_DOWN_TIME = 100;
    int coolDownCount;

    public boolean facingRight;
    PlayerDamage playerDamage;
    Clip clip;
    FlowerWolfAnimator flowerWolfAnimator;

    public FlowerWolf() {
        super();
        flowerWolfAnimator = new FlowerWolfAnimator();
        this.renderer = flowerWolfAnimator;
        boxCollider = new BoxCollider(32, 64);
        this.children.add(this.boxCollider);
        playerDamage = new PlayerDamage();
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        shoot();
        playerDamage.run(this);
        flowerWolfAnimator.run(this);

    }

    public void addParticle() {
//        ParticleEffect particleEffect1 = GameObject.recycle(ParticleEffect.class);
//        particleEffect1.position.set(this.position);
        ParticleEffect particleEffect = new ParticleEffect();
        particleEffect.position.set(this.position);
        GameObject.add(particleEffect);

    }

    private void shoot() {
        if (spellDisabled) {
            coolDownCount++;
            if (coolDownCount >= COOL_DOWN_TIME) {
                spellDisabled = false;
                coolDownCount = 0;
            }
            return;
        }

        FlowerWolfBullet newFlowerWolfBullet = GameObject.recycle(FlowerWolfBullet.class);
        newFlowerWolfBullet.position.set(this.position.x, this.position.y - 16);
        if (!facingRight) {
            newFlowerWolfBullet.speed = -5;
        }
        else newFlowerWolfBullet.speed = 5;
        spellDisabled = true;

    }

    public void getHit() {
        hP--;
        clip = AudioUtils.loadSound("assets/SFX/Hit_Hurt4.wav");
        AudioUtils.play(clip);
        if (hP <= 0) {
            Clip clip1 = AudioUtils.loadSound("assets/SFX/Scream_2.wav");
            AudioUtils.play(clip1);
            isActive = false;
            for (int i = 0; i <= 7; i++) {
                addParticle();
            }
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
