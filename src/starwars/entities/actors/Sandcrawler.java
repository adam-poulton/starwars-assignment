package starwars.entities.actors;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWGrid;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.*;

/**
 * Constructor for the <code>Sandcrawler</code>.
 * <p>
 * The constructor initializes the <code>actions</code> list of this <code>Sandcrawler</code>.
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
 * @author adamp (modified from various SWActors 27/05/2018)
 */
public class Sandcrawler extends SWActor {

    private String name;
    private SWGrid internalSpace;

    public Sandcrawler(int hitpoints, String name, MessageRenderer m, SWWorld world) {
        super(Team.NEUTRAL, 1000, m, world);
        this.name = name;
        this.addAffordance(new Enter(this, m));
    }

    public void act(){

    }

    public void engulf(SWActor theTarget){

    }
}
