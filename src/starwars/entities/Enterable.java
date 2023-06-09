package starwars.entities;


import starwars.SWEntityInterface;

/**
 * Interface for SWEntities that can be entered
 *
 * All enterable entities must have capability ENTERABLE.
 * @author adamp
 * @see {@link starwars.Capability}
 */
public interface Enterable {
    void engulf(SWEntityInterface theTarget);
}
