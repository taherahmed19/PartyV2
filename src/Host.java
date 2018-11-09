
/**
 * A simple model of Host.
 *
 * @author Maria Chli
 * @version 18-10-2006
 */
public class Host extends Person {

    /**
     * Create a new Host.
     */
    public Host() {

    }

    @Override
    public void act() {
        System.out.println("I'm here: Host");
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }
}
