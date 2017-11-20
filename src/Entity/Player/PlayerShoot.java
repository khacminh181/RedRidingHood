package Entity.Player;

import bases.GameObject;
import bases.inputs.InputManager;
import tklibs.AudioUtils;

import javax.sound.sampled.Clip;

public class PlayerShoot {
    boolean spellDisabled = false;
    final int COOL_DOWN_TIME = 3;
    int coolDownCount;
    boolean spellDisabled1 = false;
    final int COOL_DOWN_TIME_1 = 30;
    int coolDownCount1;
    public static int count = 0;
    public AudioUtils audioUtils = new AudioUtils();
    Clip clip, clip1;

    public PlayerShoot () {
        clip = AudioUtils.loadSound("assets/SFX/Laser_Shoot14.wav");
        clip1 = AudioUtils.loadSound("assets/SFX/power.wav");
    }

    public void run (Player owner) {
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

            //clip = AudioUtils.loadSound("assets/SFX/throwaxe.wav");
            AudioUtils.play(clip);
        }

        if (InputManager.shootPressed){
            if (!clip1.isRunning()) {
                AudioUtils.play(clip1);
            }
            if (count < 20) {
                count++;
            }
            spellDisabled = true;

        }
        if (InputManager.xPressed) {
            clip1.stop();
        }

    }
}
