
import java.util.Iterator;

public class Scientist extends Guest {

    private Location location;

    /**
     * Create a scientist.
     */
    public Scientist() {

    }

    @Override
    public void act() {
        System.out.println("I'm here: Scientist");
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
        field.clearLocation(location);
        Iterator<Location> locations = field.adjacentLocations(location);
        
    }
}
