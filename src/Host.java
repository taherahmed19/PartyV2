
import java.util.ArrayList;
import java.util.Iterator;

public class Host extends Person {

    /*
        Construtctor to create host object
     */
    public Host() {
        super();
    }

    /*
    override super class method as print statement specific for this object
     */
    @Override
    public void act() {
        System.out.println("I'm here: Host");
    }

    /*
        used to set host location
        @param location to set host to
     */
    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    /*
        @return get the current location of host
     */
    @Override
    public Location getLocation() {
        return this.location;
    }

    /*
        method used to move host to adjacent free location
        @param field to interact with 
     */
    
    public void act(Field field) {
        //get locations from current with certain distance
        Iterator<Location> adjacentLocations = field.adjacentLocations(this.location, ModelConstants.DISTANCE);
        Location location = null;
        //loop through location at specific distance
        while (adjacentLocations.hasNext()) {
            Location currentLocation = adjacentLocations.next();
            location = field.freeAdjacentLocation(currentLocation);

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
//        if(this.location.getRow() <= ModelConstants.DEPTH-1 && this.location.getRow() > 0 ){
//            System.out.println("current Row" + this.location.getRow() + " new " + location.getRow() );
//        }
//        if(this.location.getCol()<= ModelConstants.WIDTH-1 && this.location.getCol() > 0){
//            System.out.println("current Col" + this.location.getCol()+ " new " + location.getCol());
//        }
//        if(this.location.getRow() == 0){
//            System.out.println("current Row " + this.location.getRow() + " new " + location.getRow() );
//        }
//        if(this.location.getCol()== 0){
//            System.out.println("current Col " + this.location.getCol()+ " new " + location.getCol());
//        }
        if (location != null) {
            //clear host location - prevent duplication
            field.clearLocation(this.location);
            //set new location of host and add new location to field
            this.setLocation(location);
            field.place(this, location);
        }
    }

    /*
        get if host is social
        @return if host is social
     */
    @Override
    public boolean getSocial() {
        return this.isSocial;
    }

    /*
        used to set host to social or not
        @param random boolean value to set social to true or false
     */
    @Override
    public void setSocial(boolean isSocial) {
        this.isSocial = isSocial;
    }


}
