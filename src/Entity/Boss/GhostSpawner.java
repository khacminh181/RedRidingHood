package Entity.Boss;

import Entity.Player.Player;
import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;

import java.util.Random;

public class GhostSpawner extends GameObject{
    FrameCounter frameCounter = new FrameCounter(120);
    Random random = new Random();
    public int bossHP;

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (frameCounter.run()) {
            frameCounter.reset();
            spawn();
        }
    }

    private void spawn() {
        if (bossHP > 0) {
            Ghost ghost = GameObject.recycle(Ghost.class);
            ghost.position.set(random.nextInt(500) + 30, 0);
        }
    }

}
