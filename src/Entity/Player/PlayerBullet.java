package Entity.Player;

import Entity.Enemy.NormalWolf;
import Entity.Platform.Platform;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.renderers.ImageRenderer;

public class PlayerBullet extends GameObject {

    final int SPEED = 10;
    BoxCollider boxCollider;
    public boolean facingRight;
    Vector2D velocity;
    private final float GRAVITY = 4f;

    Vector2D playerVelocity;

    public static int bulletHeight = 300;

    public PlayerBullet() {
        super();
        this.renderer = new ImageRenderer("assets/images/Player/Bullet.png");
        velocity = new Vector2D();
        boxCollider = new BoxCollider(16 ,16);
        this.children.add(this.boxCollider);
        playerVelocity = new Vector2D();
    }


    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        velocity.x = 0;

        if (position.y <= bulletHeight) {
            velocity.y += GRAVITY;
        }
        System.out.println(bulletHeight);

        if (facingRight) {
            if (Physics.collideWith(
                    screenPosition.add(0, 0),
                    boxCollider.getWidth(),
                    boxCollider.getHeight(),
                    Player.class) != null) {
                velocity.y = -20f;
            }
            velocity.x += -SPEED + playerVelocity.x;

        }

        else{
            if (Physics.collideWith(
                    screenPosition.add(0, 0),
                    boxCollider.getWidth(),
                    boxCollider.getHeight(),
                    Player.class) != null) {
                velocity.y = -20f;
            }
            velocity.x += SPEED - playerVelocity.x;

        }
        this.position.x += velocity.x;
        this.screenPosition.x += velocity.x;

        this.position.y += velocity.y;
        this.screenPosition.y += velocity.y;


        boxCollider.position.set(this.position);

        NormalWolf enemy = Physics.collideWith(this.boxCollider, NormalWolf.class);
        if (enemy != null) {
            System.out.println("Hit");
            enemy.getHit();
            this.isActive = false;
        }
        hitPlatformer();
    }


    private void hitPlatformer() {
        if (Physics.collideWith(this.boxCollider, Platform.class) != null) {
            this.isActive = false;
        }
    }
    }



