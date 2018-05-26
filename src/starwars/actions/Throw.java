package starwars.actions;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.gridworld.Grid.CompassBearing;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWAction;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.SWWorld;
import edu.monash.fit2099.simulator.space.Location;


/**
 * Command to throw entities.
 * 
 * This affordance is attached to all thowable entities
 * 
 * @author David.Squire@monash.edu (dsquire)
 */
/*
 * Change log
 * 2017/02/03	Fixed the bug where the an actor could attack another actor in the same team (asel)
 * 2017/02/08	Attack given a priority of 1 in constructor (asel)
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
	 * @return 	true any <code>SWActor</code> can always try an attack, it just won't do much 
	 * 			good unless this <code>SWActor a</code> has a suitable weapon.
	 */
	@Override
	public boolean canDo(SWActor a) {
		//from Dip
		SWEntityInterface item = a.getItemCarried();
		if (item!= null) {
			return item.hasCapability(Capability.THROWABLE);
		}
		return false;
	}

	
	/**
	 * Perform the <code>Throw</code> command on an entity.
	 * <p>
	 * ***
	 * <p>
	 * 
	 * 
	 * @author 	dsquire -  adapted from the equivalent class in the old Eiffel version
	 * @author 	Asel - bug fixes.
	 * @param 	a the <code>SWActor</code> who is attacking
	 * @pre 	this method should only be called if the <code>SWActor a</code> is alive
	 * @pre		an <code>Attack</code> must not be performed on a dead <code>SWActor</code>
	 * @post	if a <code>SWActor</code>dies in an <code>Attack</code> their <code>Attack</code> affordance would be removed
	 * @see		starwars.SWActor#isDead()
	 * @see 	starwars.Team
	 */
	@Override
	public void act(SWActor a) {
		
		
		for (SWEntityInterface e: SWWorld.getEntitymanager().contents(SWAction.getEntitymanager().whereIs(a))){
			if (e != a){    			//luke isn't affected by the grenade
				e.takeDamage(20); 		//entities within the location lose 20 points
			}
		}
		
		//ArrayList<Direction> possibledirections = new ArrayList<Direction>();

		for (Grid.CompassBearing d : Grid.CompassBearing.values()) {
			if (SWWorld.getEntitymanager().seesExit(a, d)) {
				SWLocation location = (SWLocation) SWWorld.getEntitymanager().whereIs(a).getNeighbour(d);
				a.takeDamage(1);
				a.say("bitty i'm up to here!1");
				//Location y = SWWorld.getEntitymanager().whereIs(a).getNeighbour(d);
				//List<SWEntityInterface> contents = SWWorld.getEntitymanager().contents(location);

				//List x = SWWorld.getEntitymanager().contents(SWAction.getEntitymanager().whereIs(a));
				//if (y!=null){
				if (canLoopList(SWWorld.getEntitymanager().contents(location))) {
				    for(SWEntityInterface e: SWWorld.getEntitymanager().contents(location)) {
				    	if (e != a){    			//luke isn't affected by the grenade
							e.takeDamage(20); 		//entities within the location lose 20 points
						}
				}
				}
//				for (SWEntityInterface e: SWWorld.getEntitymanager().contents(location)){
//					String string4 = String.format("A string %s", location);
//					a.say(string4);
//					a.say("e: ");
//					String string = String.format("A string %s", e);
//					String string2 = String.format("A string %s", e.getHitpoints());
//					a.say(string);
//					a.say(string2);;
//					if (e != a){    			//luke isn't affected by the grenade
//						e.takeDamage(20); 		//entities within the location lose 20 points
//					}
//					String string3 = String.format("A string %s", e.getHitpoints());
//					a.say(string3);;
//				}
				//}
				//for (SWEntityInterface e: SWWorld.getEntitymanager().contents( SWAction.getEntitymanager().whereIs(a).getNeighbour(d))){
				
			}
		}
		
		
		
		
		
		//for (Direction d : possibledirections) {
		//	Location x = SWAction.getEntitymanager().whereIs(a);
		//	for (SWEntityInterface e: SWWorld.getEntitymanager().contents(x.getNeighbour(d))){
		//		e.takeDamage(10);		//Entities in locations that can be reached in one step from the location where the grenade is thrown lose 10 points
		//	}
			
		//}

//		
		//ArrayList<Direction> possibledirections = new ArrayList<Direction>();
		//SWAction.getEntitymanager().whereIs(a).getNeighbour(NORTH)
		
	//	for (Location d : SWAction.getEntitymanager().getNeighbour(whereIs(a)) ) { 
			//if (SWWorld.getEntitymanager().seesExit(a, d)){
				
				
	//		}
	//		possibledirections.add(d);
	//		}
		
		
		
		
		
//		SWEntityInterface target = this.getTarget();
//		boolean targetIsActor = target instanceof SWActor;
//		SWActor targetActor = null;
//		
//		if (targetIsActor) {
//			targetActor = (SWActor) target;
//		}
//					
//		
//		if (targetIsActor && (a.getTeam() == targetActor.getTeam())) { //don't attack SWActors in the same team
//			a.say("\t" + a.getShortDescription() + " says: Silly me! We're on the same team, " + target.getShortDescription() + ". No harm done");
//		}
//		else if (a.isHumanControlled() // a human-controlled player can attack anyone
//			|| (targetIsActor && (a.getTeam() != targetActor.getTeam()))) {  // others will only attack actors on different teams
//				
//			a.say(a.getShortDescription() + " is attacking " + target.getShortDescription() + "!");
//			
//			SWEntityInterface itemCarried = a.getItemCarried();
//			
//			}
//			
//			
//			
//			
//			
//				
//				
//			
//			if (this.getTarget().getHitpoints() <= 0) {  // can't use isDead(), as we don't know that the target is an actor
//				target.setLongDescription(target.getLongDescription() + ", that was killed in a fight");
//							
//			

				
			
		//} 
		a.setItemCarried(null);
		target.addAffordance(new Take((SWEntityInterface) target, this.messageRenderer)); 
		target.removeAffordance(new Leave((SWEntityInterface) target, this.messageRenderer)); //this might not be right

		//remove leave affordance, add take affordance
		
	}
	public static boolean canLoopList(List<?> list) {
	    if (list != null && !list.isEmpty()) {
	        return true;
	    }
	    return false;
	}
}


//remove attack affordance for each dead player ater grenade
