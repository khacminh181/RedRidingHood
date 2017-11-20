package Entity.Player;

import Entity.Boss.Boss;
import Entity.Boss.Ghost;
import Entity.Enemy.FlowerWolf;
import Entity.Enemy.NormalWolf;
import Entity.Platform.Platform;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.renderers.Animation;
import tklibs.SpriteUtils;

public class PlayerBullet extends GameObject {
    BoxCollider boxCollider;
    public boolean facingRight;


    //Nem riu
    public int bulletHeight; // goc nem
    final int SPEED = 8; // vx
    Vector2D velocity;
    private final float GRAVITY = 0.8f;


    public PlayerBullet() {
        super();
        this.renderer = new Animation(1,
                SpriteUtils.loadImage("assets/images/Player/bag0.png"),
                SpriteUtils.loadImage("assets/images/Player/bag1.png"),
                SpriteUtils.loadImage("assets/images/Player/bag2.png"),
                SpriteUtils.loadImage("assets/images/Player/bag3.png"),
                SpriteUtils.loadImage("assets/images/Player/bag4.png"),
                SpriteUtils.loadImage("assets/images/Player/bag5.png"),
                SpriteUtils.loadImage("assets/images/Player/bag6.png"),
                SpriteUtils.loadImage("assets/images/Player/bag7.png"),
                SpriteUtils.loadImage("assets/images/Player/bag8.png"),
                SpriteUtils.loadImage("assets/images/Player/bag9.png"),
                SpriteUtils.loadImage("assets/images/Player/bag10.png"),
                SpriteUtils.loadImage("assets/images/Player/bag11.png")

        );
        //this.renderer = new ImageRenderer("assets/images/Player/Bullet.png");
        velocity = new Vector2D();
        boxCollider = new BoxCollider(16 ,16);
        this.children.add(this.boxCollider);
    }


    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        velocity.x = 0;
        velocity.y += GRAVITY;

        if (facingRight) {
            if (Physics.collideWith(
                    screenPosition.add(0, 0),
                    boxCollider.getWidth(),
                    boxCollider.getHeight(),
                    Player.class) != null) {
                velocity.y = -bulletHeight;
            }
            velocity.x += -SPEED;

        }
        else{
            if (Physics.collideWith(
                    screenPosition.add(0, 0),
                    boxCollider.getWidth(),
                    boxCollider.getHeight(),
                    Player.class) != null) {
                velocity.y = -bulletHeight;
            }
            velocity.x += SPEED;

        }
        this.position.x += velocity.x;
        this.screenPosition.x += velocity.x;
        this.position.y += velocity.y;
        this.screenPosition.y += velocity.y;


        // Va cham enemy
        NormalWolf enemy = Physics.collideWith(this.boxCollider, NormalWolf.class);
        FlowerWolf flowerWolf = Physics.collideWith(this.boxCollider, FlowerWolf.class);
        Boss boss = Physics.collideWith(this.boxCollider, Boss.class);
        Ghost ghost = Physics.collideWith(this.boxCollider, Ghost.class);
        if (enemy != null) {
            enemy.getHit(this.velocity);
            this.isActive = false;
        }
        if (flowerWolf != null) {
            flowerWolf.getHit();
            this.isActive = false;
        }
        if (boss != null) {
            boss.getHit();
            this.isActive = false;
        }
        if (ghost != null) {
            ghost. getHit();
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



