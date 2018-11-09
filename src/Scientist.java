
public class Scientist extends Guest {

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
    public Location getLocation(){
        return this.location;
    }
}
