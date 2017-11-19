package Entity.Enemy;

import Entity.Platform.Platform;
import bases.GameObject;
import bases.ParticleEffect;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.renderers.ImageRenderer;
import tklibs.AudioUtils;

import javax.sound.sampled.Clip;

public class NormalWolf extends GameObject implements PhysicsBody {
    private BoxCollider boxCollider;
    private final float GRAVITY = 0.4f;
    private Vector2D velocity;
    int hP = 2;

    private final int SPEED = 5;
    int speed = SPEED;
    Clip clip;
    PlayerDamage playerDamage;

    public boolean facingRight;
    public boolean knockBack;
    NormalWolfAnimator normalWolfAnimator;

    public Vector2D attackerVelocity;


    public NormalWolf() {
        super();
        normalWolfAnimator = new NormalWolfAnimator();
        this.renderer = normalWolfAnimator;
        this.velocity = new Vector2D();
        this.boxCollider = new BoxCollider(32, 32);
        this.children.add(boxCollider);
        playerDamage = new PlayerDamage();
        attackerVelocity = new Vector2D();
    }

    public void addParticle() {
        ParticleEffect particleEffect = GameObject.recycle(ParticleEffect.class);
        particleEffect.position.set(this.position);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    @Override
    public Vector2D getVelocity() {
        return this.velocity;
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        updatePosition();
        playerDamage.run(this);
        normalWolfAnimator.run(this);
    }

    private void updatePosition() {
        velocity.x = 0;
        velocity.y += GRAVITY;
        velocity.x += speed;
        updateVerticalPhysics();
        updateHorizontalPhysics();
        checkFacingRight();
        checkKnockBack(attackerVelocity);
    }

    private void checkKnockBack(Vector2D bulletVelocity) {
        if (knockBack) {
            if (bulletVelocity.x > 0) {
                position.y -= 20;
                screenPosition.y -= 20;
                position.x += 20;
                screenPosition.x += 20;
            }
            else if (bulletVelocity.x < 0) {
                position.y -= 20;
                screenPosition.y -= 20;
                position.x -= 20;
                screenPosition.x -= 20;
            }
            knockBack = false;
        }
    }

    private void checkFacingRight() {
        if (speed > 0) {
            facingRight = false;
        }
        else facingRight = true;
    }

    private void updateVerticalPhysics() {
        Vector2D checkPosition = screenPosition.add(0, velocity.y);
        Platform platform = Physics.collideWith(checkPosition, boxCollider.getWidth(), boxCollider.getHeight(), Platform.class);
        if (platform != null) {
            while(Physics.collideWith(screenPosition.add(0, Math.signum(velocity.y)), boxCollider.getWidth(), boxCollider.getHeight(), Platform.class) == null) {
                position.addUp(0, Math.signum(velocity.y));
                screenPosition.addUp(0, Math.signum(velocity.y));
            }
            velocity.y = 0;
        }

        this.position.y += velocity.y;
        this.screenPosition.y += velocity.y;
    }

    private void updateHorizontalPhysics() {
        Vector2D checkPosition = screenPosition.add(velocity.x, 0);
        Platform platform = Physics.collideWith(checkPosition, boxCollider.getWidth(), boxCollider.getHeight(), Platform.class);

        if (platform != null) {
            float dx = Math.signum(velocity.x);
            while(Physics.collideWith(screenPosition.add(dx, 0), boxCollider.getWidth(), boxCollider.getHeight(), Platform.class) == null) {
                position.addUp(dx, 0);
                screenPosition.addUp(dx, 0);
            }
            velocity.x = 0;
            speed *= -1;
        }

        this.position.x += velocity.x;
        this.screenPosition.x += velocity.x;
    }

    public void getHit(Vector2D bulletVelocity) {
        attackerVelocity.set(bulletVelocity);
        knockBack = true;
        hP--;
        clip = AudioUtils.loadSound("assets/SFX/dog_howls2.wav");
        AudioUtils.play(clip);
        if (hP <= 0) {
            isActive = false;
            for (int i = 0; i < 7; i++) {
                addParticle();
            }
        }
    }

}
