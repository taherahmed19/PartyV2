
import java.util.Iterator;

public class Artist extends Guest {

    private Location location;

    public Artist() {

    }

    @Override
    public void act(Field field) {
        field.clearLocation(location);
        Iterator<Location> locations = field.adjacentLocations(location);
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
    protected void act() {
        System.out.println("I'm here: Artist");
    }

}
