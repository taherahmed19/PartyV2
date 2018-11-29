
import java.util.ArrayList;
import java.util.Iterator;

public class Scientist extends Guest {
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
    public void act(Field field, ArrayList<Person> persons) {

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
        Location highestHappinessLocation = this.location;
        int highestHappiness = -1;
        int counter = 0;
        Iterator<Location> locations = field.adjacentLocations(this.location, ModelConstants.DISTANCE);
        Person person;
        while (locations.hasNext()) {
            counter = 0;
            Location currentLocation = locations.next();
            if ((person = field.getObjectAt(currentLocation)) != null) {
                if (person.getSocial() && person instanceof Artist || person instanceof Host || person instanceof Scientist) {
                    Location location = field.freeAdjacentLocation(currentLocation);
                    if (location != null) {
                        Iterator<Location> locations2 = field.adjacentLocations(location, 1);
                        while (locations2.hasNext()) {
                            Location cLocation = locations2.next();
                            if (field.getObjectAt(cLocation) != null) {
                                counter++;
                            }
                        }
                        if (highestHappiness == -1) {
                            highestHappiness = counter;
                            highestHappinessLocation = location;
                        }
                        if (counter > highestHappiness) {
                            highestHappiness = counter;
                            highestHappinessLocation = location;
                        }
                        System.out.println("happiness " + highestHappiness + " " + this.toString());
                    }

                }
            }
        }
        field.clearLocation(this.location);
        this.setLocation(highestHappinessLocation);
        this.setHappiness(highestHappiness);
        field.place(this, highestHappinessLocation);
    }
}
