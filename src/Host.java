
import java.util.Iterator;

/**
 * A simple model of Host.
 *
 * @author Maria Chli
 * @version 18-10-2006
 */
public class Host extends Person {

    private Location location;

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

    @Override
    public void act(Field field) {
        //field.clearLocation(location);
        Iterator<Location> locations = field.adjacentLocations(location);
        field.place(this, locations.next());
    }
}
