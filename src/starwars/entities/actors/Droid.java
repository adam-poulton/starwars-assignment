package starwars.entities.actors;

import java.util.ArrayList;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWAction;
import starwars.SWActor;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Move;
import starwars.entities.actors.behaviors.AttackInformation;
import starwars.entities.actors.behaviors.AttackNeighbours;

public class Droid extends SWActor {

	private String name;

	public Droid(int hitpoints, String name, MessageRenderer m, SWWorld world) {
		super(Team.NEUTRAL, 50, m, world);
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	@Override
	public void act() {
		if (isDead()) {
			//i think this is sufficient, still. I mentioned that the Droid would be immobile
			//in my design rationale and this is still true, just different terminology
			immobilityMessage();
			return ;
			
		}
		checkForBadlands();

	}

	@Override
	public String getShortDescription() {
		return name + " the Droid";
	}

	@Override
	public String getLongDescription() {
		return this.getShortDescription();
	}

	private String describeLocation() {
		SWLocation location = this.world.getEntityManager().whereIs(this);
		return this.getShortDescription() + " [" + this.getHitpoints() + "] is at " + location.getShortDescription();

	}
	private String immobilityMessage() {
		return (this.getShortDescription() + " is immobile." );
	}
	public void checkForBadlands() {
		SWLocation location = this.world.getEntityManager().whereIs(this);
		if(location.getSymbol() == 'b'){ 
		    takeDamage(5);
		}
}}
