package starwars;


/**
 * This class represents "The Force"
 * Each <code>SWActor</code> has their ability to interact with the force defined within a <code>Force</code> class
 *
 * @author Adam Poulton
 *
 */
public class Force {

    /**The minimum <code>ability</code> required to wield a <code>LightSaber</code>*/
    private static final int LIGHTSABER_REQUIREMENT = 1; //Requirement to wield a lightsaber arbitrarily set to 1

    /**The amount of <code>ability</code> associated with the instance of <code>Force</code>*/
    private int ability;

    /**
     * Default constructor for the <code>Force</code>.
     *
     * Initialises <code>ability</code> to be zero as appropriate for a non force-user.
     */
    public Force(){
        setAbility(0);
    }

    /**
     * Constructor for the <code>Force</code>.
     *
     * @param   ability the initial value of the ability for this <code>Force</code>.
     */
    public Force(int ability){
        setAbility(ability);
    }

    /**
     * Returns the <code>ability</code> associated with this <code>Force</code>.
     *
     * @return  the <code>ability</code> of this <code>Force</code>.
     */
    public int getAbility(){
        return this.ability;
    }

    /**
     * Method sets the <code>ability</code> attribute for an instance of the force.
     * @param   ability the value that ability will be set to.
     * @pre     <code>ability</code> should not be negative
     */
    public void setAbility(int ability) {
        assert (ability >= 0) : "force ability cannot be negative";
        this.ability = ability;
    }

    /**
     * Method evaluates if the current state of <code>Force</code> permits wielding a lightsaber.
     *
     * @return true if <code>Force</code> permits wielding a lightsaber, false if it does not.
     */
    public boolean canUseLightSaber(){
        return getAbility() >= LIGHTSABER_REQUIREMENT;
    }
}
