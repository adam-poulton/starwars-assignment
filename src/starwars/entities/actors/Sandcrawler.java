package starwars.entities.actors;

import edu.monash.fit2099.simulator.matter.Affordance;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.*;
import starwars.actions.*;
import starwars.SWActor;
import edu.monash.fit2099.gridworld.Grid.CompassBearing;
import edu.monash.fit2099.simulator.matter.EntityManager;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.space.Location;
import edu.monash.fit2099.simulator.space.World;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.actions.Take;
import starwars.actions.Train;
import starwars.entities.*;
import starwars.entities.actors.*;
import starwars.entities.actors.behaviors.*;

import java.util.ArrayList;
import java.util.List;


public class Sandcrawler extends SWActor implements Enterable{

    private String name;
    private SWGrid internalGrid;
    private Patrol path;

    /**
     * Constructor for the <code>Sandcrawler</code>.
     * <p>
     * The constructor initializes the <code>actions</code> list of this <code>Sandcrawler</code>.
     * <p>
     * @param 	hitpoints initial hitpoints of this <code>SWActor</code> to start with
     * @param 	name of this <code>SWActor</code>
     * @param 	m	message renderer for this <code>SWActor</code> to display messages
     * @param 	world the <code>World</code> to which <code>SWActor</code> belongs to
     *
     * @see 	#team
     * @see 	#hitpoints
     * @see 	#world
     *
     * @author adamp (modified from various SWActors 27/05/2018)
     */
    public Sandcrawler(int hitpoints, String name, MessageRenderer m, SWWorld world,
                       int internalHeight, int internalWidth) {
        super(Team.NEUTRAL, hitpoints, m, world);

        assert internalHeight > 0:"height must be positive";
        assert internalWidth > 0:"width must be positive";

        Direction [] patrolmoves = {CompassBearing.EAST, CompassBearing.EAST,
                CompassBearing.SOUTH,
                CompassBearing.WEST, CompassBearing.WEST,
                CompassBearing.SOUTH,
                CompassBearing.EAST, CompassBearing.EAST,
                CompassBearing.NORTHWEST, CompassBearing.NORTHWEST};

        this.path = new Patrol(patrolmoves);
        this.setShortDescription("Sandcrawler");
        this.setLongDescription("Sandcrawler, loved by all.");
        this.name = name;
        this.addAffordance(new Enter(this, m));
        SWLocation.SWLocationMaker factory = SWLocation.getMaker();
        this.internalGrid = new SWGrid(factory,2, 2);
        SWLocation loc;

        for (int row=0; row < internalHeight; row++) {
            for (int col=0; col < internalWidth; col++) {
                loc = internalGrid.getLocationByCoordinates(col, row);
                //inside of the sandcrawler need not be rendered
                loc.setLongDescription("Sandcrawler " + this.name + " (" + col + ", " + row + ")");
                loc.setShortDescription("Sandcrawler " + this.name + " (" + col + ", " + row + ")");
                loc.setSymbol('.');
            }
        }
    }

    /**
     * Methods handles the movement of the sandcrawler and also its diet of droids
     * Sandcrawler will poll current location for droids, eating all that it finds
     * @author adamp 27/05/18
     */
    public void act(){
        if(!isDead()) {
            SWLocation location = world.getEntityManager().whereIs(this);
            EntityManager<SWEntityInterface, SWLocation> em = world.getEntityManager();
            List<SWEntityInterface> entities = em.contents(location);

            // eat some droids

            for (SWEntityInterface e : entities) {
                if(e instanceof Droid){
                    engulf(e);
                }
            }

            Direction newdirection = path.getNext();
            say(getShortDescription() + " moves " + newdirection);
            Move myMove = new Move(newdirection, messageRenderer, world);

            scheduler.schedule(myMove, this, 2);
        }




    }

    /**
     * Method lets <code>Sandcrawler</code> to 'engulf' the target <code>SWActor</code> by changing its location to
     * a location that is 'inside' the <code>Sandcrawler</code>
     * @param theTarget the SWActor that the <code>Sandcrawler</code> is eating
     */
    public void engulf(SWEntityInterface theTarget){
        SWLocation loc = internalGrid.getLocationByCoordinates(0, 0);
        SWWorld.getEntitymanager().setLocation(theTarget, loc);
    }

    /**
     * Methods lets <code>Sandcrawler</code> to 'spit out' the target <code>SWActor</code> by changing its location to
     * the location that the <code>Sandcrawler</code> is currently occupying
     * @param theTarget
     * @author adamp 27/05/18
     */
    public void spitOut(SWEntityInterface theTarget){
        SWLocation loc = world.getEntityManager().whereIs(this);
        SWWorld.getEntitymanager().setLocation(theTarget, loc);
    }

    /**
     *
     * @return the <code>SWGrid</code> representing the inside of the <code>Sandcrawler</code>
     * @author adamp 27/05/18
     */
    public SWGrid getInternalGrid(){
        return internalGrid;
    }
}
