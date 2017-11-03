package Entity.Player;

import Entity.Enemy.FlowerWolf;
import Entity.Enemy.NormalWolf;
import Entity.Platform.Platform;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.renderers.ImageRenderer;

public class PlayerFireBall extends GameObject{

    final int SPEED = 10;
    BoxCollider boxCollider;
    public boolean facingRight;

    public PlayerFireBall() {
        super();
        this.renderer = new ImageRenderer("assets/images/Player/Fireball.png");
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

        NormalWolf enemy = Physics.collideWith(this.boxCollider, NormalWolf.class);
        FlowerWolf flowerWolf = Physics.collideWith(this.boxCollider, FlowerWolf.class);
        if (enemy != null) {
            System.out.println("Hit");
            enemy.getHit();
            enemy.getHit();
        }
        if (flowerWolf != null) {
            flowerWolf.getHit();
        }

        hitPlatformer();
    }

    private void hitPlatformer() {
        if (Physics.collideWith(this.boxCollider, Platform.class) != null) {
            this.isActive = false;
        }
    }


}
