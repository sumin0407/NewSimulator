package action;

import agents.CS;
import core.SoSObject;

public class Action extends SoSObject {

    CS target;
    Action parentAction;
    public Func onComplete;

    public Action(CS target) {
        this.target = target;
    }

    public void setParentAction(Action parentAction) {
        this.parentAction = parentAction;
    }

    public void start() {
        target.addChild(this);
    }
    public void stop() {
        target.removeChild(this);
    }

    public void complete() {
        if(onComplete != null) {
            onComplete.invoke();
        }
        stop();
    }

}