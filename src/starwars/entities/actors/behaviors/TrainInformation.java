package starwars.entities.actors.behaviors;

import edu.monash.fit2099.simulator.matter.Affordance;
import starwars.SWEntityInterface;

public class TrainInformation {

	public SWEntityInterface entity;
	public Affordance affordance;
	public TrainInformation(SWEntityInterface e, Affordance a) {
		entity = e;
		affordance = a;
	}
}
