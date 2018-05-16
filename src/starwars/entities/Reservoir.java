package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWAffordance;
import starwars.SWEntity;
import starwars.actions.Dip;

/**
 * Class to represent a water reservoir.  <code>Reservoirs</code> are currently pretty passive.
 * They can be dipped into to fill fillable entities (such as <code>Canteens</code>.  They
 * are assumed to have infinite capacity.
 * 
 * @author 	ram
 * @see 	{@link starwars.entities.Canteen}
 * @see {@link starwars.entities.Fillable}
 * @see {@link starwars.actions.Fill} 
 */
public class Reservoir extends SWEntity {

	/**
	 * Constructor for the <code>Reservoir</code> class. This constructor will,
	 * <ul>
	 * 	<li>Initialize the message renderer for the <code>Reservoir</code></li>
	 * 	<li>Set the short description of this <code>Reservoir</code> to "a water reservoir</li>
	 * 	<li>Set the long description of this <code>Reservoir</code> to "a water reservoir..."</li>
	 * 	<li>Add a <code>Dip</code> affordance to this <code>Reservoir</code> so it can be taken</li> 
	 *	<li>Set the symbol of this <code>Reservoir</code> to "T"</li>
	 * </ul>
	 * 
	 * @param 	m <code>MessageRenderer</code> to display messages.
	 * @see 	{@link starwars.actions.Dip} 
	 */
	public Reservoir(MessageRenderer m) {
		super(m);
		SWAffordance dip = new Dip(this, m);
		this.addAffordance(dip);	
		
		this.setLongDescription("a water reservoir.");
		this.setShortDescription("a water reservoir, full of cool, clear, refreshing water");
		this.setSymbol("W");

		//reservoir requirement - starting hitpoints
        //adamp 16/05/18
		this.setHitpoints(40);
	}

	@Override 
	public String getShortDescription() {
		return shortDescription;
	}
	
	public String getLongDescription() {
		return longDescription;
	}

    /**
     * Method calls the super method and then will,
     * <ul>
     *     <li>Check the remaining <code>hitpoints</code></li>
     *     <li>If <code>0 < hitpoints < 20</code>:</li>
     *     <ul>
     *         <li>Set the short description of this <code>Reservoir</code> to "a damaged water reservoir"</li>
     * 	       <li>Set the long description of this <code>Reservoir</code> to "a damaged water reservoir, leaking slowly"</li>
     * 	       <li>Set the symbol of this <code>Reservoir</code> to "V"</li>
     * 	   </ul>
     * 	   <li>If <code>hitpoints <= 0</code>:</li>
     *     <ul>
     *         <li>Set the short description of this <code>Reservoir</code> to "the wreckage of a water reservoir"</li>
     * 	       <li>Set the long description of this <code>Reservoir</code> to "the wreckage of a water reservoir, surrounded by slightly damp soil"</li>
     * 	       <li>Set the symbol of this <code>Reservoir</code> to "X"</li>
     * 	   </ul>
     * </ul>
     * @param damage amount of damage this <code>Reservoir</code> will take
     *
     * @author adamp 16/05/18
     */
	@Override
	public void takeDamage(int damage){
		super.takeDamage(damage);
		int hp = this.getHitpoints();
		if (hp > 0 && hp < 20) {
            this.setShortDescription("a damaged water reservoir");
            this.setLongDescription("a damaged water reservoir, leaking slowly");
            this.setSymbol("V");
        } else if (hp <= 0) {
		    this.setShortDescription("the wreckage of a water reservoir");
		    this.setLongDescription("the wreckage of a water reservoir, surrounded by slightly damp soil");
		    this.setSymbol("X");
        }
	}
}
