package bases.action;

import bases.GameObject;

public class ActionRepeatForever implements Action {
    Action action;
    public ActionRepeatForever(Action action) {
        this.action = action;
    }
    @Override
    public boolean run(GameObject owner) {
        if(action.run(owner)) {
            action.reset();
        }
        return false;
    }

    @Override
    public void reset() {
        action.reset();
    }
}
