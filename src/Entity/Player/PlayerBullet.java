package Entity.Player;

import Entity.Enemy.NormalWolf;
import Entity.Platform.Platform;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.renderers.ImageRenderer;

public class PlayerBullet extends GameObject {


    final int SPEED = 20;
    BoxCollider boxCollider;
    public boolean facingRight;


    public PlayerBullet() {
        super();
        this.renderer = new ImageRenderer("assets/images/Player/Bullet.png");
        boxCollider = new BoxCollider(16 ,16);
        this.children.add(this.boxCollider);
    }


    public void run(Vector2D parentPosition) {
        super.run(parentPosition);

        if (facingRight) {
            this.position.subtractBy(SPEED, 0);

        }
        else{
            this.position.subtractBy(-SPEED, 0);

        }
        //System.out.println(facingRight);
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



