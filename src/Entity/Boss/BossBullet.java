package Entity.Boss;

import Entity.Platform.Platform;
import Entity.Player.Player;
import bases.GameObject;
import bases.Vector2D;
import bases.action.Action;
import bases.action.ActionRepeatForever;
import bases.action.ActionSequence;
import bases.action.ActionWait;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.renderers.Animation;
import tklibs.SpriteUtils;

public class BossBullet extends GameObject {
    BoxCollider boxCollider;
    public int speed = 5;
    public Vector2D velocity;

    public BossBullet() {
        super();
        this.renderer = new Animation(1,
                SpriteUtils.loadImage("assets/images/Enemies/flowerwolf/flowerwolf_bullet_0.png"),
                SpriteUtils.loadImage("assets/images/Enemies/flowerwolf/flowerwolf_bullet_1.png"),
                SpriteUtils.loadImage("assets/images/Enemies/flowerwolf/flowerwolf_bullet_2.png"),
                SpriteUtils.loadImage("assets/images/Enemies/flowerwolf/flowerwolf_bullet_3.png"));
        this.boxCollider = new BoxCollider(12, 12);
        this.children.add(this.boxCollider);
        velocity = new Vector2D();

    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        this.position.addUp(velocity);

        // va cham player
        Player player = Physics.collideWith(this.boxCollider, Player.class);
        if (player != null) {
            player.getHit(velocity);
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
