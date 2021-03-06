package Entity.Boss;

import Entity.Enemy.NormalWolf;
import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;

public class WolfSpawner extends GameObject {
    public boolean isWolf;
    FrameCounter frameCounter = new FrameCounter(600);
    FrameCounter frameCounter2 = new FrameCounter(5000);

    private int count = 0;
    public int bossHP;

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        if (frameCounter2.run()) {
            count = 0;
            frameCounter.reset();
        }

        if (frameCounter.run() && count <= 2) {
            frameCounter.reset();
            spawn();
            isWolf = true;
            count ++;
        }

    }

    private void spawn() {
        if (bossHP > 0) {
            NormalWolf normalWolf = GameObject.recycle(NormalWolf.class);
            normalWolf.position.set(21 * 31, 16 * 32 - 8);
        }

    }
}
