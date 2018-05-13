package starwars.actions;

import edu.monash.fit2099.simulator.matter.EntityManager;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWAction;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.SWLocation;

public class Leave extends SWAffordance {

	/**
	 * Constructor for the <code>Leave</code> Class. Will initialize the message renderer, the target and 
	 * set the priority of this <code>Action</code> to 1 (lowest priority is 0).
	 * 
	 * @param theTarget a <code>SWEntity</code> that is being left
	 * @param m the message renderer to display messages
	 */
	public Leave(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		priority = 1;
	}


	/**
	 * Returns if or not this <code>Leave</code> can be performed by the <code>SWActor a</code>.
	 * <p>
	 * This method returns true if and only if <code>a</code> is carrying any item.
	 *  
	 * @author 	ram
	 * @author 	Asel (26/01/2017)
	 * @author  asmin1 (modified from Take 9/5/2018)
	 * @param 	a the <code>SWActor</code> being queried
	 * @return 	true if the <code>SWActor</code> is can leave an item, false otherwise
	 * @see		{@link starwars.SWActor#getItemCarried()}
	 */
	@Override
	public boolean canDo(SWActor a) {
		return a.getItemCarried()!=null;
	}

	/**
	 * Perform the <code>Leave</code> action by setting the item carried by the <code>SWActor</code> to the target's position (
	 * the <code>SWActor a</code>'s item carried would be the target of this <code>Leave</code>).
	 * <p>
	 * This method should only be called if the <code>SWActor a</code> is alive.
	 * 
	 * @author 	ram
	 * @author 	Asel (26/01/2017)
	 * @author  asmin1 (modified from Take 9/5/2018)
	 * @param 	a the <code>SWActor</code> that is taking the target
	 * @see 	{@link #theTarget}
	 * @see		{@link starwars.SWActor#isDead()}
	 */
	@Override
	public void act(SWActor a) {
		if (target instanceof SWEntityInterface) {
			//After this is done
			//the actor should be holding nothing
			a.setItemCarried(null);
			//item should be in the location of the actor when it was done,
			SWAction.getEntitymanager().setLocation((SWEntityInterface) target, SWAction.getEntitymanager().whereIs(a));
			target.removeAffordance(this);
			//and the item should be able to be picked up again
			target.addAffordance(new Take((SWEntityInterface) target, this.messageRenderer)); 
		    }
	}

	/**
	 * A String describing what this action will do, suitable for display in a user interface
	 * 
	 * @author ram
	 * @author asmin1 (modified from Take 9/5/2018)
	 * @return String comprising "leave " and the short description of the target of this <code>Leave</code>
	 */
	@Override
	public String getDescription() {
		return "leave " + target.getShortDescription();
	}

}
