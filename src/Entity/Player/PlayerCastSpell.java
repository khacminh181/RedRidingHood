package Entity.Player;

import bases.GameObject;
import bases.inputs.InputManager;

public class PlayerCastSpell {

    public static boolean isCast;

    public PlayerCastSpell() {
        isCast = false;
    }

    public void run(Player owner, int bulletCounter) {
        if (bulletCounter >= 20 && InputManager.instance.spacePressed ) {
            if (!isCast) {
                PlayerFireBall newFireBall = GameObject.recycle(PlayerFireBall.class);
                newFireBall.position.set(owner.position);
                newFireBall.facingRight = owner.facingRight;
                isCast = true;
            }
        }
    }
}
