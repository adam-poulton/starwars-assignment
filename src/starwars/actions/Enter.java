package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActionInterface;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.entities.actors.Sandcrawler;

/**
 * <code>SWAction</code> that lets a <code>SWActor</code> enter another <code>SWActor</code>..
 *
 * An affordance for entering <code>SWActor</code>s.
 * Can only be applied in partnership with an <code>Entity</code> that is <code>Enterable</code>.
 *
 * @author adamp
 * @see {@link starwars.entities.Enterable}
 */
public class Enter extends SWAffordance implements SWActionInterface {

    public Enter(SWActor theTarget, MessageRenderer m) {
        super(theTarget, m);
        SWActor destination = theTarget;
    }

    @Override
    public String getDescription(){
        return "enter " + this.target.getShortDescription();
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
     * Determine whether a particular <code>SWActor a</code> can enter the target.
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
        //TODO: de-spaghetti (probs not tho, its tasty)
        SWEntityInterface target = this.getTarget();
        boolean targetIsSandcrawler = target instanceof Sandcrawler;
        Sandcrawler targetSandcrawler;

        if (targetIsSandcrawler) {
            targetSandcrawler = (Sandcrawler) target;
            targetSandcrawler.engulf(a);
        }

    }


}
