package bases.action;

import bases.GameObject;

public class ActionSequence implements Action {
    Action[] actions;
    int currentActionIndex;

    public ActionSequence (Action ... actions) {
        this.actions = actions;
        currentActionIndex = 0;
    }

    @Override
    public boolean run(GameObject owner) {
        Action currentAction = this.actions[currentActionIndex];
        if (currentAction.run(owner)) {
            if (currentActionIndex == actions.length - 1) {
                return true;
            }
            else {
                currentActionIndex ++;
                return false;
            }
        }
        else {
            return false;
        }
    }

    @Override
    public void reset() {
        currentActionIndex = 0;
        for (Action action : actions) {
            action.reset();
        }
    }
}
