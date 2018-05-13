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

public class Follow {

	
	public static CompassBearing Follow(SWActor owner, Droid droid, SWWorld world) {
		SWLocation locationOfDroid = SWAction.getEntitymanager().whereIs((SWEntityInterface) droid);
		SWLocation locationOfOwner = SWAction.getEntitymanager().whereIs((SWEntityInterface) owner);

		if (locationOfOwner == locationOfDroid){
			droid.say(String.format("help alex Im stuck.")); 

			return null;
		}
		
		else{
		// build a list of available directions
			for (Grid.CompassBearing d : Grid.CompassBearing.values()) {
				if(locationOfDroid.getNeighbour(d) == locationOfOwner){
					return d;
			}
	
		//droid.say(String.format("help alex Im stuck.")); 

		}
		return null;
	}
	
	}
	
}
		


		



	
	

