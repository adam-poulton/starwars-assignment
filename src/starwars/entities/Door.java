package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWAffordance;
import starwars.SWEntity;
import starwars.actions.Exit;


public class Door extends SWEntity {

    /**
     * Constructor for the <code>Door</code> class.
     *
     * @param 	m <code>MessageRenderer</code> to display messages.
     */
    public Door(MessageRenderer m) {
        super(m);

        this.setLongDescription("a door.");
        this.setShortDescription("a door. where could it lead?");
        this.setSymbol("#");
        this.setHitpoints(1000);
    }

    @Override
    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }


}