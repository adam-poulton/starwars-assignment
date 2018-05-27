package starwars.actions;

import java.util.List;

import edu.monash.fit2099.gridworld.Grid;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWAction;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.SWWorld;


/**
 * Command to throw entities.
 * 
 * This affordance is attached to all thowable entities
 * 
 * @author David.Squire@monash.edu (dsquire)
 * @author asmin1 modified from various (26/5/2018)
 */

public class Throw extends SWAffordance{

	
	/**
	 * Constructor for the <code>Throw</code> class. Will initialize the <code>messageRenderer</code> and
	 * give <code>Throw</code> a priority of 1 (lowest priority is 0).
	 * 
	 * @param theTarget the target being thrown
	 * @param m message renderer to display messages
	 */
	public Throw(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);	
		priority = 1;
	}


	/**
	 * Returns the time is takes to perform this <code>Throw</code> action.
	 * 
	 * @return The duration of the Throw action. Currently hard coded to return 1.
	 */
	@Override
	public int getDuration() {
		return 1;
	}

	
	/**
	 * A String describing what this <code>Throw</code> action will do, suitable for display on a user interface
	 * 
	 * @return String comprising "throw " and the short description of the target of this <code>Affordance</code>
	 */
	@Override
	public String getDescription() {
		return "throw " + this.target.getShortDescription();
	}


	/**
	 * Determine whether a particular <code>SWActor a</code> can throw the target.
	 * 
	 * @author 	dsquire
	 * @param 	a the <code>SWActor</code> being queried
	 * @return 	true if the item carried by <code>SWActor</code> has the THROWABLE afforance  
	 * @return  false otherwise
	 * 			
	 */
	@Override
	public boolean canDo(SWActor a) {
		//reformed from Dip
		SWEntityInterface item = a.getItemCarried();
		if (item!= null) {
			return item.hasCapability(Capability.THROWABLE);  //just wanna make sure that the item is throwable 
		}
		return false;  //otherwise this action can't be performed
	}

	
	/**
	 * Perform the <code>Throw</code> command on an entity.
	 * <p>
	 * 	<li>Entities in the location where the grenade is thrown lose 20 hitpoints. </li>
	 * <li>Entities in locations that can be reached in one step from the location where the grenade is thrown lose 10 points.</li>
	 * <li>Entities in locations that can be reached in two steps from the location where the grenade is thrown lose 5 points</li>
	 * <p>
	 * 
	 * 
	 * @author 	dsquire -  adapted from the equivalent class in the old Eiffel version
	 * @author 	Asel - bug fixes.
	 * @param 	a the <code>SWActor</code> who is attacking
	 */
	@Override
	public void act(SWActor a) {	
		//Entities in the location where the grenade is thrown lose 20 hitpoints
		for (SWEntityInterface e: SWWorld.getEntitymanager().contents(SWAction.getEntitymanager().whereIs(a))){
			if (e != a){    			//The actor that throws the grenade is not affected.
				e.takeDamage(20); 		//entities within the location lose 20 points
			}
		}
		for (Grid.CompassBearing d : Grid.CompassBearing.values()) {  //going through the possible directions
			if (SWWorld.getEntitymanager().seesExit(a, d)) {
				SWLocation neighb = (SWLocation) SWWorld.getEntitymanager().whereIs(a).getNeighbour(d);
				if (SWWorld.getEntitymanager().contents(neighb)!= null) {
					
					//Entities in locations that can be reached in one step from the location where the grenade is thrown lose 10 points.
				    for(SWEntityInterface e: SWWorld.getEntitymanager().contents(neighb)) {
						e.takeDamage(10); 		
				    }
				    //Entities in locations that can be reached in two steps from the location where the grenade is thrown lose 5 points.
				    if (SWWorld.getEntitymanager().contents((SWLocation) neighb.getNeighbour(d))!= null){
				    	for(SWEntityInterface e: SWWorld.getEntitymanager().contents((SWLocation) neighb.getNeighbour(d))) {
							e.takeDamage(5); 		
				    	}	
				    }
				    //Entities in locations that can be reached in two steps from the location where the grenade is thrown lose 5 points.
				    //These positions are a knight's move away from the actor
				    if (SWWorld.getEntitymanager().contents((SWLocation) neighb.getNeighbour(d.turn(45)))!= null){
				    	for(SWEntityInterface e: SWWorld.getEntitymanager().contents((SWLocation) neighb.getNeighbour(d.turn(45)))) {
							e.takeDamage(5); 		
				    	}
				    }
				}
			}
		}
		a.setItemCarried(null);  //remove any remnants of the grenade to cover ;After a grenade is thrown, it is completely destroyed and disappears. 
		//remove leave affordance, add take affordance
		target.addAffordance(new Take((SWEntityInterface) target, this.messageRenderer)); 
		target.removeAffordance(new Leave((SWEntityInterface) target, this.messageRenderer)); 
	}
}
	


