package starwars.entities.actors.behaviors;

import edu.monash.fit2099.simulator.matter.Affordance;
import edu.monash.fit2099.simulator.matter.EntityManager;
import starwars.SWActor;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.actions.Train;

import java.util.ArrayList;
import java.util.List;

public class TrainNeighbours {

	
	public static TrainInformation trainLocals(SWActor actor, SWWorld world) {
		SWLocation location = world.getEntityManager().whereIs(actor);
		EntityManager<SWEntityInterface, SWLocation> em = world.getEntityManager();
		List<SWEntityInterface> entities = em.contents(location);

		// select the trainable things that are here

		ArrayList<TrainInformation> trainables = new ArrayList<>();
		for (SWEntityInterface e : entities) {
			// Figure out if we should be training this entity
			// We exclude actors that are not on the same team
			if( e != actor && 
					(e instanceof SWActor &&
							(((SWActor)e).getTeam() == actor.getTeam())
					)) {
				for (Affordance a : e.getAffordances()) {
					if (a instanceof Train && ((SWActor) e).isUntrained()) {
						trainables.add(new TrainInformation(e, a));
						break;
					}
				}
			}
		}

		// if there's at least one thing we can train, randomly choose
		// something to train
		if (trainables.size() > 0) {
			return trainables.get((int) (Math.floor(Math.random() * trainables.size())));
		} else {
			return null;
		}
	}
}
