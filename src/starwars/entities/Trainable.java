package starwars.entities;

/**
 * Interface for SWEntities trainable in the force
 * 
 * All trainable entities must have capability TRAINABLE.
 * @author adamp
 * @see {@link starwars.Capability}
 */
public interface Trainable {
	/**
	 * Train this SWActor in the force.
	 */
	void train();
}
