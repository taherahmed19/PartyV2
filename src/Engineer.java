
import java.util.ArrayList;
import java.util.Iterator;

public class Engineer extends Guest {

    //  private int happinessLevel;
    public Engineer() {

    }

    @Override
    public void act(Field field, ArrayList<Person> persons) {

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
    public void act() {
        System.out.println("I'm here: Engineer");
    }

    @Override
    public int getHappiness() {
        return this.happinessLevel;
    }

    @Override
    public void setHappiness(int level) {
        this.happinessLevel = level;
    }

    @Override
    public boolean getSocial() {
        return this.isSocial;
    }

    @Override
    public void setSocial(boolean isSocial) {
        this.isSocial = isSocial;

    }

      @Override
    public void moveToBestLocation(Field field) {
        Iterator<Location> locations = field.adjacentLocations(this.location);
        Person person;
        while (locations.hasNext() &&(person = field.getObjectAt(locations.next())) != null) {
            if (locations != this.location) {
                if (person.getSocial()) {
                    Location location = field.freeAdjacentLocation(this.location);
                    field.clearLocation(this.location);

                    this.setLocation(location);
                    this.setHappiness(happinessLevel);
                    field.place(this, location);
                }
            }
        }
    }
}
