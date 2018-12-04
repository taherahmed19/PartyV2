
import java.util.ArrayList;
import java.util.Iterator;

public class Guest extends Person {

    //used to store highest happiness found for a type of guest
    protected int highestHappiness = -1;
    //temp counter which will be reset after happiness at location calculated
    protected int counter = 0;
    //stores happinessLevel
    protected int happinessLevel;
    
    /**
    *@return the happiness of the guest 
    */
    protected int getHappiness() {
        return happinessLevel;
    }
    
    /**
    *set the guests level
    *@Param new level to set to guest
    */
    protected void setHappiness(int level) {
        this.happinessLevel = level;
    }
    /**
    *find happier location for the guest
    *method ensures each type of guest does not need to override method as they need to check for other specfic guests
    *@param field the field of the party
    *@param the guest to move
    */
    protected void moveToBestLocation(Field field, Guest guest) {
        //current happiest location equals current as unmoved
        Location highestHappinessLocation = guest.location;
        Iterator<Location> locations = field.adjacentLocations(guest.location, ModelConstants.DISTANCE);
        Person person;
        //loop through adjacent locations for a specific distance
        while (locations.hasNext()) {
            //counter is used find the happiness of moving to a certain location maybe discarded if not greater than current value
            counter = 0;
            //store the next location prevent program failure
            Location currentLocation = locations.next();
            if ((person = field.getObjectAt(currentLocation)) != null && person.getSocial()) {
                //ensures the person is social to move to
                Location location = field.freeAdjacentLocation(currentLocation);
                if (location != null && ModelConstants.DISTANCE > 0) {
                    //at potentially new location check adjacent locations by 1
                    Iterator<Location> locations2 = field.adjacentLocations(location, 1);
                    while (locations2.hasNext()) {
                        Location cLocation = locations2.next();

                        //block of if statements ensures guest do not go out of bounds and switch sides
                        if (cLocation != null && guest.location.getRow() <= ModelConstants.DEPTH - 1 && cLocation.getRow() >= 0
                                && cLocation.getRow() < (guest.location.getRow() - ModelConstants.DISTANCE)) {
                            cLocation = null;
                        }
                        if (cLocation != null && guest.location.getCol() <= ModelConstants.WIDTH - 1 && cLocation.getCol() >= 0
                                && cLocation.getCol() < (guest.location.getCol() - ModelConstants.DISTANCE)) {
                            cLocation = null;
                        }
                        if (cLocation != null && guest.location.getRow() >= 0 && cLocation.getRow() <= ModelConstants.DEPTH
                                && cLocation.getRow() > (guest.location.getRow() + ModelConstants.DISTANCE)) {
                            cLocation = null;
                        }
                        if (cLocation != null && guest.location.getCol() >= 0 && cLocation.getCol() <= ModelConstants.WIDTH
                                && cLocation.getCol() > (guest.location.getCol() + ModelConstants.DISTANCE)) {
                            cLocation = null;
                        }

                        if (guest instanceof Scientist && cLocation != null) {
                            //check if a guest is found at location and surronding guests are ones the scientist wants interact with
                            if (field.getObjectAt(cLocation) != null && checkArtistSurrondings(field.getObjectAt(cLocation))) {
                                counter++;
                            }
                        }
                        if (guest instanceof Engineer && cLocation != null) {
                            //check if a guest is found at location and surronding guests are ones the engineer wants interact with
                            if (field.getObjectAt(cLocation) != null && checkEngineerSurrondings(field.getObjectAt(cLocation))) {
                                counter++;
                            }
                        }
                        if (guest instanceof Artist && cLocation != null) {
                            //check if a guest is found at location and surronding guests are ones the artist wants interact with
                            if (field.getObjectAt(cLocation) != null && checkArtistSurrondings(field.getObjectAt(cLocation))) {
                                counter++;
                            }
                        }
                    }
                    //initially set the values of highesthappineess and the location
                    if (highestHappiness == -1) {
                        highestHappiness = counter;
                        highestHappinessLocation = location;
                    }
                    //if a location is found where the happiness can be higher set new location
                    if (counter > highestHappiness) {
                        highestHappiness = counter;
                        highestHappinessLocation = location;
                    }
                }
            }
        }
        //clear current location - prevent duplication
        field.clearLocation(guest.location);
        //set highesthappiness location if not found it remains in its original position
        guest.setLocation(highestHappinessLocation);
        //after highest happiness has been found set happiness otherwise
        guest.setHappiness(highestHappiness);
        //place guest at new location
        field.place(guest, highestHappinessLocation);
    }

    /**
    *ensures guests interact with other guests they like to socialise with
    *@param the current person check others to socialise with
    *@return boolean values true if guests favourable are found 
    */
    protected boolean checkArtistSurrondings(Person person) {
        if (person instanceof Artist || person instanceof Host) {
            return true;
        }
        return false;
    }
     /**
     *ensures guests interact with other guests they like to socialise with
     *@param the current person check others to socialise with
     *@return boolean values true if guests favourable are found 
    */
    protected boolean checkEngineerSurrondings(Person person) {
        if (person instanceof Artist || person instanceof Host || person instanceof Scientist
                || person instanceof Engineer) {
            return true;
        }
        return false;
    }
     /**
     *ensures guests interact with other guests they like to socialise with
     *@param the current person check others to socialise with
     *@return boolean values true if guests favourable are found 
    */
    protected boolean checkScientistSurrondings(Person person) {
        if (person instanceof Artist || person instanceof Host || person instanceof Scientist) {
            return true;
        }
        return false;
    }
    //method to be overriden
    @Override
    protected void act() {
    }
    
    /**
    *@param location to set the new location of the guest
    */
    @Override
    protected void setLocation(Location location) {
        this.location = location;
    }
    /**
    *@return current location of the guest
    */
    @Override
    protected Location getLocation() {
        return this.location;
    }
    /**
    *@return if the guest is social or not true if yes else false
    */
    @Override
    protected boolean getSocial() {
        return this.isSocial;
    }
    
    /**
    *@param random boolean value to set the guest
    */
    @Override
    protected void setSocial(boolean isSocial) {
        this.isSocial = isSocial;
    }

}
//                        if (cLocation != null && guest.location.getRow() == ModelConstants.DEPTH - 1 && cLocation.getRow() != 0) {
//                            System.out.println("current Row" + guest.location.getRow() + " new " + cLocation.getRow());
//                        }
//                        if (cLocation != null && guest.location.getCol() == ModelConstants.WIDTH - 1 && cLocation.getCol() != 0) {
//                            System.out.println("current Col" + guest.location.getCol() + " new " + cLocation.getCol());
//                        }
//                        if (cLocation != null && guest.location.getRow() == 0) {
//                            System.out.println("current Row " + guest.location.getRow() + " new " + cLocation.getRow());
//                        }
//                        if (cLocation != null && guest.location.getCol() == 0) {
//                            System.out.println("current Col " + guest.location.getCol() + " new " + cLocation.getCol());
//                        } 
