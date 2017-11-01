package Entity.Player;

import Entity.Platform.Platform;
import bases.GameObject;
import bases.Vector2D;
import bases.inputs.InputManager;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.renderers.ImageRenderer;

public class Player extends GameObject {
    private Vector2D velocity;
    private final float GRAVITY = 0.4f;
    private BoxCollider boxCollider;

    PlayerShoot playerShoot;
    PlayerCastSpell playerCastSpell;

    boolean facingRight;


    public Player() {
        super();
        this.renderer = new ImageRenderer("assets/images/Player/RedRidingHood.png");
        this.velocity = new Vector2D();
        this.boxCollider = new BoxCollider(64, 64);
        this.children.add(boxCollider);
        playerShoot = new PlayerShoot();
        playerCastSpell = new PlayerCastSpell();
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        updatePhysics();
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    private void updatePhysics() {
//        velocity.y += GRAVITY;
        //fall();
        velocity.x = 0;

        jump();
        fall();

        playerShoot.run(this, playerCastSpell);
        playerCastSpell.run(this, playerShoot.bulletCounter);


        moveHorizontal();

        updateHorizontalPhysics();

        updateVerticalPhysics();

    }



    private void moveHorizontal() {
        if (InputManager.instance.leftPressed) {
            facingRight = true;
            velocity.x -= 5;

        }

        if (InputManager.instance.rightPressed) {
            facingRight = false;
            velocity.x += 5;
        }
    }

    private void jump() {
        if (InputManager.instance.cPressed) {
            if (Physics.collideWith(
                    screenPosition.add(0, 1),
                    boxCollider.getWidth(),
                    boxCollider.getHeight(),
                    Platform.class) != null) {
                velocity.y = -10f;
            }
        }

    }

    private void fall() {
        if (velocity.y > 0 && InputManager.instance.zPressed) {
            velocity.y += GRAVITY * 0.1f ;
        }
        else velocity.y += GRAVITY;

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
        }

        this.position.y += velocity.y;
        this.screenPosition.y += velocity.y;
    }
}