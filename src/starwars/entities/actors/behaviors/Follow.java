package starwars.entities.actors.behaviors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.gridworld.Grid.CompassBearing;
import edu.monash.fit2099.simulator.matter.EntityManager;
import edu.monash.fit2099.simulator.space.Direction;
import starwars.SWAction;
import starwars.SWActor;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.actions.Move;
import starwars.entities.actors.Droid;

 /** <code>Behaviour</code> that lets <code>SWActor</code>s follow owner around the map.
 * 
 * @author ram
 * @author asmin1 (modified 13/5/2018)
 */

public class Follow {
	/**
	 * The <code>Follow</code> behaviour class. Will determine the direction for the owner Droid to go.
	 * 
	 * @param owner <code>SWActor</code> owner of the droid, to be followed
	 * @param droid <code>Droid</code> that follows their owner
	 * @param world the world in which the <code>Follow</code> behaviour is invoked
	 * 
	 * @author asmin1 (modified from TuskenRaider 13/5/2018)
	 */
	
	public static CompassBearing Follow(SWActor owner, Droid droid, SWWorld world) {
		SWLocation locationOfDroid = SWAction.getEntitymanager().whereIs((SWEntityInterface) droid);
		SWLocation locationOfOwner = SWAction.getEntitymanager().whereIs((SWEntityInterface) owner);
		ArrayList<Direction> possibledirections = new ArrayList<Direction>();

		if (locationOfOwner == locationOfDroid){//in the same location?
			return null; 						//stay with the owner
		}
		else{
			for (Grid.CompassBearing d : Grid.CompassBearing.values()) { //else for all surrounding neighbours
				possibledirections.add(d);
				if(locationOfDroid.getNeighbour(d) == locationOfOwner){  //if the owner is present
					return d; 											 //return the direction of movement
			}
		} //can't find owner?
		Direction d = possibledirections.get((int) (Math.floor(Math.random() * possibledirections.size())));
		return (CompassBearing) d; //return random direction 
	}
	
	}
	
}
		


		



	
	

