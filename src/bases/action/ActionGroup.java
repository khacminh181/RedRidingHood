package bases.action;

import bases.GameObject;

public class ActionGroup implements Action {
    Action[] actions;

    public ActionGroup(Action... actions) {
        this.actions = actions;
    }

    @Override
    public boolean run(GameObject owner) {
        int count = 0;
        for (Action action : actions) {
            if (action.run(owner)) {
                count ++;
            }
        }
        return count == actions.length;
    }

    @Override
    public void reset() {
        for (Action action : actions) {
            action.reset();
        }
    }
}
