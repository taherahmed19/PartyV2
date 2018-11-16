
import java.util.Iterator;

public abstract class Guest extends Person {
    protected Location location;
    protected abstract void act();
    protected abstract void setLocation(Location location);
    protected abstract Location getLocation();
}
