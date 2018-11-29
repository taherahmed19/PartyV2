
import java.util.ArrayList;
public class Host extends Person {

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
    public void act(Field field, ArrayList<Person> persons) {
        Location location = field.freeAdjacentLocation(this.location);

        if (location != null) {
            field.clearLocation(this.location);
            this.setLocation(location);
            field.place(this, location);
        }
    }

    @Override
    public boolean getSocial() {
        return this.isSocial;
    }

    @Override
    public void setSocial(boolean isSocial) {
        this.isSocial = isSocial;
    }

}
