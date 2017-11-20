package Entity.Enemy;

import Entity.Boss.Boss;
import Entity.Player.Player;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.physics.PhysicsBody;

public class PlayerDamage {
    public void run (PhysicsBody owner) {
        BoxCollider boxCollieder = owner.getBoxCollider();
        Player player = Physics.collideWith(boxCollieder, Player.class);
        if (player != null) {
            if (owner instanceof Boss) {
                player.getHitByBoss(owner.getVelocity());
            }
            else {
                player.getHit(owner.getVelocity());
            }
        }
    }
}
