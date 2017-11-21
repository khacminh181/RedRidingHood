package Entity.Boss;

import Entity.Platform.Platform;
import Entity.Player.Player;
import bases.GameObject;
import bases.Vector2D;
import bases.action.*;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.renderers.Animation;
import tklibs.AudioUtils;
import tklibs.SpriteUtils;

import javax.sound.sampled.Clip;

public class BossBigBullet extends GameObject {
    BoxCollider boxCollider;
    public Vector2D velocity;

    public BossBigBullet() {
        super();
        this.renderer = new Animation(1,
                SpriteUtils.loadImage("assets/images/Enemies/flowerwolf/bossbullet.png"),
                SpriteUtils.loadImage("assets/images/Enemies/flowerwolf/bossbullet2.png")

        );
        this.boxCollider = new BoxCollider(32, 32);
        this.children.add(this.boxCollider);
        velocity = new Vector2D();

        this.actions.clear();
    }

    public void configActions() {
        Action shootAction = new Action() {
            @Override
            public boolean run(GameObject owner) {
                for (float angle = 360; angle >= 0; angle-= 45) {
                    BossBullet newBossBullet = GameObject.recycle(BossBullet.class);
                    newBossBullet.position.set(owner.position.x, owner.position.y);
                    Vector2D velocity1 = Vector2D.DOWN.rotate(angle).multiply(10);
                    newBossBullet.velocity.set(velocity1);
                }
                return true;
            }

            @Override
            public void reset() {

            }
        };

        Action ExplosionAction = new Action() {
            @Override
            public boolean run(GameObject owner) {
                owner.isActive = false;
                Clip clip1 = AudioUtils.loadSound("assets/SFX/82109__gniffelbaf__balloon-burst-01.wav");
                AudioUtils.play(clip1);
                return true;

            }

            @Override
            public void reset() {

            }
        };

        Action groupAction = new ActionGroup(
                ExplosionAction,
                shootAction
        );

        Action shootSequence = new ActionSequence(
                new ActionMoveBy(velocity.multiply(2), 30),
                new ActionMoveBy(velocity.multiply(1), 60),


                groupAction
        );

        this.addAction(shootSequence);
    }

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        // va cham player
        Player player = Physics.collideWith(this.boxCollider, Player.class);
        if (player != null) {
            System.out.println("Hit player");
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
