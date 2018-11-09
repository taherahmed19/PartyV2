
/**
 * A simple model of a person in the simulation.
 *
 * @author Maria Chli
 * @version 18-10-2006
 */
public abstract class Person {
    protected Location location;
    public Person() {
    }

    protected abstract void act();
    protected abstract void setLocation(Location location);
    protected abstract Location getLocation();
}
