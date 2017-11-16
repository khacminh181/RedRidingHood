package Entity.Enemy;

import Entity.Platform.Platform;
import Entity.Player.Player;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.renderers.ImageRenderer;

public class FlowerWolfBullet extends GameObject {
    BoxCollider boxCollider;
    public int speed;

    public FlowerWolfBullet() {
        super();
        this.renderer = new ImageRenderer("assets/images/Enemies/flowerwolf/flowerwolfbullet.png");
        this.boxCollider = new BoxCollider(12, 12);
        this.children.add(this.boxCollider);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.subtractBy(speed, 0);

        // va cham player
        Player player = Physics.collideWith(this.boxCollider, Player.class);
        if (player != null) {
            System.out.println("Hit player");
            player.getHit();
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
