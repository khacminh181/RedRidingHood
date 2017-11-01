package Entity.Player;

import bases.GameObject;
import bases.inputs.InputManager;

public class PlayerShoot {
    boolean spellDisabled = false;
    final int COOL_DOWN_TIME = 10;
    int coolDownCount;
    public int bulletCounter = 0;


    public void run (Player owner, PlayerCastSpell playerCastSpell) {
        if (spellDisabled) {
            coolDownCount++;
            if (coolDownCount >= COOL_DOWN_TIME) {
                spellDisabled = false;
                coolDownCount = 0;
            }
            return;
        }

        if (InputManager.instance.xPressed) {
            PlayerBullet newBullet = GameObject.recycle(PlayerBullet.class);
            newBullet.position.set(owner.position);
            newBullet.facingRight = owner.facingRight;

            spellDisabled = true;
            if (playerCastSpell.isCast) {
                bulletCounter = 0;
                playerCastSpell.isCast = false;
            }
            bulletCounter++;

            System.out.println(bulletCounter);
        }

    }
}