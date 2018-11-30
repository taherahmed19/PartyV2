
import java.util.ArrayList;
import java.util.Iterator;

public class Engineer extends Guest {

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
        Location highestHappinessLocation = this.location;
        int highestHappiness = -1;
        int counter = 0;
        Iterator<Location> locations = field.adjacentLocations(this.location, ModelConstants.DISTANCE);
        Person person;
        while (locations.hasNext()) {
            //counter is used find the happiness of moving to a certain location maybe discarded if not greater than current value
            counter = 0;
            Location currentLocation = locations.next();
            if ((person = field.getObjectAt(currentLocation)) != null) {
                //ensures the person is social to move to
                if (person.getSocial()) {
                    Location location = field.freeAdjacentLocation(currentLocation);
                    if (location != null) {
                        Iterator<Location> locations2 = field.adjacentLocations(location, 1);
                        //loops through adjacent locations trying to find the highest happiness
                        while (locations2.hasNext()) {
                            Location cLocation = locations2.next();
                            if (field.getObjectAt(cLocation) != null) {
                                counter++;
                            }
                        }
                        //sets the initial values for highestHappiness and highestHappinessLocation
                        if (highestHappiness == -1) {
                            highestHappiness = counter;
                            highestHappinessLocation = location;
                        }
                        //if a location with a higher happiness is found update variables
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
        //set highesthappiness location if not found it remains in its original position
        this.setLocation(highestHappinessLocation);
        //after highest happiness has been found set happiness otherwise
        this.setHappiness(highestHappiness);
        field.place(this, highestHappinessLocation);
    }
}
