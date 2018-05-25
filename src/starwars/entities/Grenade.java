package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWEntity;
import starwars.actions.Take;
import starwars.actions.Throw;

/*
 * 2017/02/04 Removed the Unicode symbol of the sword to a 's' since it wasn't displayed on the text interface(asel)
 * 2017/02/08 takeDamage method was overridden to change it's descriptions when the Swords hitpoints are zero or less
 * 			  the takeDamage method will also remove the CHOPPER and WEAPON capabilities from the Blaster as it should not be possible
 * 			  to attack or chop with a broken Blaster (asel)
 */
public class Grenade extends SWEntity {

	/**
	 * Constructor for the <code>Grenade</code> class. This constructor will,
	 * <ul>
	 * 	<li>Initialize the message renderer for the <code>Grenade</code></li>
	 * 	<li>Set the short description of this <code>Grenade</code> to "a grenade"</li>
	 * 	<li>Set the long description of this <code>Grenade</code> to "A cool looking grenade"</li>
	 * 	<li>Add a <code>Take</code> affordance to this <code>Grenade</code> so it can be taken</li> 
	 * </ul>
	 * 
	 * @param m <code>MessageRenderer</code> to display messages.
	 * 
	 * @see {@link starwars.actions.Take}
	 * @see {@link starwars.Capability}
	 */
	public Grenade(MessageRenderer m) {
		super(m);
		
		this.shortDescription = "a grenade";
		this.longDescription = "A cool looking grenade";
		this.hitpoints = 100; // start with a fully charged pistol

		
		this.capabilities.add(Capability.THROWABLE);   // and THROWABLE so that it can be used to throw

		this.addAffordance(new Take(this, m));//add the Take affordance so that the grenade can be picked up
		this.addAffordance(new Throw(this, m));//add the Throw affordance so that the grenade can be picked up?
	}
	
	
	/**
	 * A symbol that is used to represent the Blaster on a text based user interface
	 * 
	 * @return 	Single Character string "b"
	 * @see 	{@link starwars.SWEntityInterface#getSymbol()}
	 */
	public String getSymbol() {
		return "g"; 
	}

	
	

}
