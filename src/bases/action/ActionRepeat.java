package bases.action;

import bases.GameObject;

public class ActionRepeat implements Action {
    Action action;
    int countMax;
    int count;

    public ActionRepeat(Action action, int countMax) {
        this.action = action;
        this.countMax = countMax;
        this.count = 0;
    }


    @Override
    public boolean run(GameObject owner) {
        if (count == countMax) {
            return true;
        }

        if (action.run(owner)) {
            count++;
        }

        return false;
    }

    @Override
    public void reset() {
        count = 0;
        action.reset();
    }
}
