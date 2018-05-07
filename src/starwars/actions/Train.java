package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.*;

/**
 * <code>SWAction</code> that lets a <code>SWActor</code> train another <code>SWActor</code> in the force.
 *
 * An affordance for training <code>SWActor</code>s.
 * Can only be applied in partnership with an <code>Entity</code> that is <code>Trainable</code>.
 *
 * @author adamp
 * @see {@link starwars.entities.Trainable}
 */
public class Train extends SWAffordance implements SWActionInterface {

    public Train(SWActor theTarget, MessageRenderer m){
        super(theTarget, m);
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public int getPriority(){
        return 1;
    }

    /**
     * Determine whether a particular <code>SWActor a</code> can train the target.
     *
     * @author 	adamp
     * @param 	a the <code>SWActor</code> being checked
     * @return 	true if the <code>SWActor</code> can always try an attack,
     */
    @Override
    public boolean canDo(SWActor a) {
        return false;
    }

    @Override
    public void act(SWActor a) {
        SWEntityInterface target = this.getTarget();
        boolean targetIsActor = target instanceof SWActor;
        SWActor targetActor = null;
        assert(this.getTarget().hasCapability(Capability.TRAINABLE)):"Target does not have trainable capability";
    }
}
