package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWAction;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.entities.actors.Droid;

/**
 * <code>SWAction</code> that lets a <code>SWActor</code> take ownership of a <code>Droid</code>.
 * 
 * @author ram
 * @author asmin1 (modified 11/5/2018)
 */

public class TakeOwnership extends SWAffordance {

	/**
	 * Constructor for the <code>TakeOwnership</code> Class. Will initialize the message renderer, the target and 
	 * set the priority of this <code>Action</code> to 1 (lowest priority is 0).
	 * 
	 * @param theTarget a <code>SWEntity</code> that is being taken
	 * @param m the message renderer to display messages
	 */
	public TakeOwnership(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		priority = 1;
	}


	/**
	 * Returns if or not this <code>TakeOwnership</code> can be performed by the <code>SWActor a</code>.
	 * <p>
	 * This method returns true if and only if the target <code>Droid</code> has no owner.
	 *  
	 * @author 	ram
	 * @author 	Asel (26/01/2017)
	 * @author  asmin1 (modified 11/5/2018)
	 * @param 	a the <code>SWActor</code> being queried
	 * @return 	true if the <code>Droid</code> has no owner, false otherwise
	 */
	@Override
	public boolean canDo(SWActor a) {
		Droid droid = (Droid) target; //added a cast? oh well fixed it.
		return (droid.getOwner() == null);
	}

	/**
	 * Perform the <code>TakeOwnership</code> action by setting the <code>Droid<code>'s owner to the <code>SWActor</code> 
	 * the <code>Droid</code>would be the target of this <code>TakeOwership</code>).
	 * <p>
	 * This method should only be called if the target is a <code>Droid</code>.
	 * 
	 * @author 	ram
	 * @author 	Asel (26/01/2017)
	 * @author  asmin1 (modified 11/5/2018)
	 * @param 	a the <code>SWActor</code> that is taking ownership of the target droid
	 * @see 	{@link #theTarget}
	 * @see		{@link starwars.SWActor#isDead()}
	 */
	@Override
	public void act(SWActor a) {
		if (target instanceof Droid) {
			SWEntityInterface theDroid = (SWEntityInterface) target;
			((Droid) theDroid).setOwner(a);//another cast insta-fix? what's a cast???
			//remove the takeOwnership affordance
			theDroid.removeAffordance(this);
		}
	}

	/**
	 * A String describing what this action will do, suitable for display in a user interface
	 * 
	 * @author ram
	 * @author asmin1 (modified 11/5/2018)
	 * @return String comprising "take " and the short description of the target of this <code>Take</code>
	 */
	@Override
	public String getDescription() {
		return "take ownership of " + target.getShortDescription();
	}

}
