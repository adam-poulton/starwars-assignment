package starwars.entities.actors;

import edu.monash.fit2099.gridworld.Grid.CompassBearing;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Move;
import starwars.actions.TakeOwnership;
import starwars.entities.actors.behaviors.Follow;


public class Droid extends SWActor {

	private String name;
	private SWActor owner;
	/**
	 * Constructor for the <code>Droid</code>.
	 * <p>
	 * The constructor initializes the <code>actions</code> list of this <code>Droid</code>.
	 * <p>
	 * @param 	team to which this <code>SWActor</code> belongs to
	 * @param 	hitpoints initial hitpoints of this <code>SWActor</code> to start with
	 * @param 	m	message renderer for this <code>SWActor</code> to display messages
	 * @param 	world the <code>World</code> to which <code>SWActor</code> belongs to
	 * 
	 * @see 	#team
	 * @see 	#hitpoints
	 * @see 	#world
	 * 
	 * @author asmin1
	 */
	public Droid(int hitpoints, String name, MessageRenderer m, SWWorld world) {
		super(Team.NEUTRAL, 50, m, world);
		// TODO Auto-generated constructor stub
		this.name = name;
		this.setOwner(null);
		this.addAffordance(new TakeOwnership(this, m));//add the Take affordance so that the droid can be owner by an actor
	}

	@Override
	public void act() {
		if (isDead()) {
			//i think this is sufficient, still. I mentioned that the Droid would be immobile
			//in my design rationale and this is still true, just different terminology.
			//i assume there will be more functionality in assignment 3 for this property
			immobilityMessage(); //maybe
			return;			
		}
		checkForBadlands(); //droids take damage in the badlands
		if (this.owner != null){
			CompassBearing d = Follow.Follow(this.owner, this, world);
			if (d == null){
				return;
			}
			else{
				//bit of TuskenRaider code
				Move myMove = new Move(d, messageRenderer, world);
				scheduler.schedule(myMove, this, 1);
			}
		}
		else{
			return; //no new move? why not stand still!
		}

	}

	@Override
	public String getShortDescription() {
		return name + " the Droid";
	}
	@Override
	public String getLongDescription() {
		return this.getShortDescription();
	}
	private String describeLocation() {
		SWLocation location = this.world.getEntityManager().whereIs(this);
		return this.getShortDescription() + " [" + this.getHitpoints() + "] is at " + location.getShortDescription();

	}
	private String immobilityMessage() {
		return (this.getShortDescription() + " is immobile." );
	}
	/**
	 * Method for the <code>Droid</code> class. This method will,
	 * <ul>
	 * 	<li>check the location of the <code>Droid</code></li>
	 * 	<li>damage the <code>Droid</code> by 10 hitpoints if the <code>Droid</code> is in the badlands</li>
	 * </ul>
	 * 
	 * 
	 * @author asmin1
	 */
	public void checkForBadlands() {
		SWLocation location = this.world.getEntityManager().whereIs(this);
		if(location.getSymbol() == 'b'){ 
		    takeDamage(10);
		}
}

	/**
	 * Method for the <code>Droid</code> class. This method will,
	 * <ul>
	 * 	<li>return the owner of the <code>Droid</code></li>
	 * </ul>
	 * 
	 * 
	 * @author asmin1
	 */
	public SWActor getOwner() {
		return owner;
	}
	/**
	 * Method for the <code>Droid</code> class. This method will,
	 * <ul>
	 * 	<li>set the owner of the <code>Droid</code></li>
	 * </ul>
	 * @param owner <code>SWActor</code>
	 * 
	 * @author asmin1
	 */

	public void setOwner(SWActor owner) {
		this.owner = owner;
	}
}
