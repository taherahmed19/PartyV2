
public class Artist extends Guest {

    public Artist() {

    }

    @Override
    public void act() {
        System.out.println("I'm here: Artist");
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

}
