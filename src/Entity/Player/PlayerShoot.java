package Entity.Player;

import bases.GameObject;
import bases.inputs.InputManager;

public class PlayerShoot {
    boolean spellDisabled = false;
    final int COOL_DOWN_TIME = 20;
    int coolDownCount;
    public static int bulletCounter = 0;


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
            if (bulletCounter < 20) {
                bulletCounter ++;
            }
            System.out.println(bulletCounter);
        }


        // Điều khiển góc ném rìu
        if (InputManager.instance.downPressed) {
            if (PlayerBullet.bulletHeight > 0) {
                PlayerBullet.bulletHeight -= 1;
            }
        }

        if (InputManager.instance.upPressed) {
            if (PlayerBullet.bulletHeight < 20) {
                PlayerBullet.bulletHeight += 1;
            }
        }

    }
}
