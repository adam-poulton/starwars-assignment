package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActionInterface;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;

/**
 * <code>SWAction</code> that lets a <code>SWActor</code> exit another <code>SWActor</code>.
 *
 * An affordance for exiting <code>SWActor</code>s.
 * Can only be applied in partnership with an <code>Entity</code> that is <code>Enterable</code>.
 *
 * @author adamp
 * @see {@link starwars.entities.Enterable}
 */
public class Exit extends SWAffordance implements SWActionInterface {

    public Exit(SWActor theTarget, MessageRenderer m) {
        super(theTarget, m);
    }

    @Override
    public String getDescription(){
        return "exit " + this.target.getShortDescription();
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
     * Determine whether a particular <code>SWActor a</code> can exit.
     *
     * @author 	adamp
     * @param 	a the <code>SWActor</code> being checked
     * @return 	true if the target is a force user
     */
    @Override
    public boolean canDo(SWActor a) {
        return a.canUseForce();
    }

    @Override
    public void act(SWActor a) {

    }


}
