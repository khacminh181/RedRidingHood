package Entity.Player;

import bases.GameObject;
import bases.inputs.InputManager;

public class PlayerShoot {
    boolean spellDisabled = false;
    final int COOL_DOWN_TIME = 3;
    int coolDownCount;
    boolean spellDisabled1 = false;
    final int COOL_DOWN_TIME_1 = 30;
    int coolDownCount1;
    public static int count = 0;



    public void run (Player owner, PlayerCastSpell playerCastSpell) {
        if (spellDisabled) {
            coolDownCount++;
            if (coolDownCount >= COOL_DOWN_TIME) {
                spellDisabled = false;
                coolDownCount = 0;
            }
            return;
        }

        if (spellDisabled1) {
            coolDownCount1++;
            if (coolDownCount1 >= COOL_DOWN_TIME_1) {
                spellDisabled1 = false;
                coolDownCount1 = 0;
            }
            return;
        }

        if (InputManager.instance.xPressed) {
                PlayerBullet newBullet = GameObject.recycle(PlayerBullet.class);
                newBullet.position.set(owner.position);
                newBullet.bulletHeight = count;
                newBullet.facingRight = owner.facingRight;

                InputManager.xPressed = false;

                count = 0;
                spellDisabled1 = true;

        }

        if (InputManager.shootPressed){
            if (count < 20) {
                count++;
            }
            if (count == 20) {
                count = 0;
            }

            spellDisabled = true;

        }

    }
}
