package bases.action;

import bases.FrameCounter;
import bases.GameObject;

public class ActionWait implements Action{
    FrameCounter frameCounter;
    public ActionWait (int frameMax) {
        frameCounter = new FrameCounter(frameMax);
    }

    @Override
    public boolean run(GameObject owner) {
        return frameCounter.run();
    }

    @Override
    public void reset() {
        frameCounter.reset();
    }
}
