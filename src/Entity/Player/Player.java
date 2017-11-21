package Entity.Player;

import Entity.Platform.Platform;
import Entity.Scenes.GameOverScene;
import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.inputs.InputManager;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.scenes.SceneManager;
import tklibs.AudioUtils;

import javax.sound.sampled.Clip;
import java.awt.*;

import static Entity.Platform.Platform.HORNTILE;

public class Player extends GameObject implements PhysicsBody {
    public Vector2D velocity;
    private final float GRAVITY = 0.4f;
    private BoxCollider boxCollider;

    PlayerShoot playerShoot;

    boolean facingLeft;
    public boolean flinching; //nhap nhay
    public boolean isJumping;

    public int HP = 8;

    PlayerAnimator animator;

    FrameCounter frameCounter = new FrameCounter(10);
    FrameCounter frameCounterFlinching = new FrameCounter(100);

    Clip clip;

    public Vector2D attackerVelocity;
    public boolean knockBack;


    public boolean checkDoor;
    public Player() {
        super();
        animator = new PlayerAnimator();
        this.renderer = animator;
        this.velocity = new Vector2D();
        this.boxCollider = new BoxCollider(30, 60);
        this.children.add(boxCollider);
        playerShoot = new PlayerShoot();
        attackerVelocity = new Vector2D();
    }

    @Override
    public void render(Graphics2D g2d, ViewPort viewPort) {
        if (flinching) {
            if (frameCounter.run()) {
                frameCounter.reset();
                return;
            }
        }
        super.render(g2d, viewPort);
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        updatePhysics();
        animator.run(this);
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    private void updatePhysics() {
        velocity.x = 0;
        jump();
        fall();
        playerShoot.run(this);
        if (flinching && frameCounterFlinching.run() ) {
            flinching = false;
            frameCounterFlinching.reset();
        }
        checkDie();
        checkKnockBack(attackerVelocity);
        moveHorizontal();
        updateHorizontalPhysics();
        updateVerticalPhysics();
        if (velocity.y == 0) {
            isJumping = false;
        }
    }

    private void checkKnockBack(Vector2D bulletVelocity) {
        if (knockBack) {
            if (bulletVelocity.x > 0) {
                velocity.addUp(30, 0);
            }
            else if (bulletVelocity.x < 0) {
                velocity.addUp(-30, 0);

            }
            else if (bulletVelocity.x == 0) {
                if (!facingLeft) {
                    velocity.addUp(-30, 0);

                }
                else if (facingLeft) {
                    velocity.addUp(30, 0);
                }

            }
//            velocity.addUp(0, -5);
            if (!isJumping) {
                velocity.addUp(0, -5);
            }
            knockBack = false;
        }
    }

    private void checkDie() {
        if(position.y >= 600) {
           SceneManager.changeScene(new GameOverScene());
        }
    }

    private void moveHorizontal() {
        if (InputManager.instance.leftPressed) {
            facingLeft = true;
            velocity.x -= 5;
        }
        if (InputManager.instance.rightPressed) {
            facingLeft = false;
            velocity.x += 5;
        }
    }

    private void jump() {
        if (InputManager.instance.cPressed) {
            if (Physics.collideWith(
                    screenPosition.add(0, 1),
                    boxCollider.getWidth(),
                    boxCollider.getHeight(),
                    Platform.class) != null ) {
                isJumping = true;
                velocity.y = -10f;
                clip = AudioUtils.loadSound("assets/SFX/jump.wav");
                AudioUtils.play(clip);
            }

        }
    }

    private void fall() {
       velocity.y += GRAVITY;
    }

    private void updateHorizontalPhysics() {
        Vector2D checkPosition = screenPosition.add(velocity.x, 0);
        Platform platform = Physics.collideWith(checkPosition, boxCollider.getWidth(), boxCollider.getHeight(), Platform.class);

        if (platform != null ) {
            float dx = Math.signum(velocity.x);
            while(Physics.collideWith(screenPosition.add(dx, 0), boxCollider.getWidth(), boxCollider.getHeight(), Platform.class) == null) {
                position.addUp(dx, 0);
                screenPosition.addUp(dx, 0);
            }
            velocity.x = 0;
            if (platform.isType == HORNTILE ) {
                getHit(new Vector2D(0,0));
            }

            if ( platform.isType == Platform.DOORTILE) {
                checkDoor = true;
            }
        }
        this.position.x += velocity.x;
        this.screenPosition.x += velocity.x;
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
            // Dẫm gai
            if (platform.isType == HORNTILE ) {
                getHit(new Vector2D(0, 0));
            }

            if (platform.isType == Platform.DOORTILE) {
                checkDoor = true;
            }
        }
        this.position.y += velocity.y;
        this.screenPosition.y += velocity.y;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    public void getHit(Vector2D bulletVelocity) {
        attackerVelocity.set(bulletVelocity);
        if (flinching) return;
        knockBack = true;
        clip = AudioUtils.loadSound("assets/SFX/Playergethit.wav");
        AudioUtils.play(clip);
        HP--;
        if (HP <= 0) {
            isActive = false;
            SceneManager.changeScene(new GameOverScene());
        }
        flinching = true;
    }

    public void getHitByBoss(Vector2D bulletVelocity) {
        attackerVelocity.set(bulletVelocity);
        if (flinching) return;
        knockBack = true;
        clip = AudioUtils.loadSound("assets/SFX/Playergethit.wav");
        AudioUtils.play(clip);
        HP -= 3;
        if (HP <= 0) {
            isActive = false;
            SceneManager.changeScene(new GameOverScene());
        }
        flinching = true;
    }
}
