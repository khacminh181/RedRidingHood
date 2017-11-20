package bases.action;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;

public class ActionMoveBy implements Action {
    Vector2D velocity;
    FrameCounter frameCounter;

    public ActionMoveBy(Vector2D velocity, int frameCount) {
        this.velocity = velocity;
        frameCounter = new FrameCounter(frameCount);
    }

    @Override
    public boolean run(GameObject owner) {
        if (frameCounter.run()) {
            return true;
        }
        else {
            owner.position.addUp(velocity);
            return false;
        }

    }

    @Override
    public void reset() {
        frameCounter.reset();
    }
}
