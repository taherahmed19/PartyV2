
import java.util.Iterator;

public class Host extends Person {

    /**
    *Constructor to create host object
     */
    public Host() {
        super();
    }

    /**
    *override super class method as print statement specific for this object
     */
    @Override
    public void act() {
        System.out.println("I'm here: Host");
    }

    /**
    *used to set host location
    *@param location to set host to
     */
    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
    *@return get the current location of host
     */
    @Override
    public Location getLocation() {
        return this.location;
    }

    /**
    *method used to move host to adjacent free location
    *@param field to interact with 
    */
    public void act(Field field) {
        //get locations from current with certain distance
        Iterator<Location> adjacentLocations = field.adjacentLocations(this.location, ModelConstants.DISTANCE);
        Location location = null;
        //loop through location at specific distance
        while (adjacentLocations.hasNext()) {
            Location adjacentLocation = adjacentLocations.next();
            location = field.freeAdjacentLocation(adjacentLocation);

            //block of if statements ensures hosts dont move from right to left and bottom to top
            if (location != null && this.location.getRow() <= ModelConstants.DEPTH - 1 && location.getRow() >= 0
                    && location.getRow() < (this.location.getRow() - ModelConstants.DISTANCE)) {
                location = null;
            }
            if (location != null && this.location.getCol() <= ModelConstants.WIDTH - 1 && location.getCol() >= 0
                    && location.getCol() < (this.location.getCol() - ModelConstants.DISTANCE)) {
                location = null;
            }
            if (location != null && this.location.getRow() >= 0 && location.getRow() <= ModelConstants.DEPTH
                    && location.getRow() > (this.location.getRow() + ModelConstants.DISTANCE)) {
                location = null;
            }
            if (location != null && this.location.getCol() >= 0 && location.getCol() <= ModelConstants.WIDTH
                    && location.getCol() > (this.location.getCol() + ModelConstants.DISTANCE)) {
                location = null;
            }
            if (location != null) {
                break;
            }

        }
        if (location != null) {
            //clear host location - prevent duplication
            field.clearLocation(this.location);
            //set new location of host and add new location to field
            this.setLocation(location);
            field.place(this, location);
        }
    }

   


}
