

public abstract class Person {
    //each person (host,guest) has a location store in this
    protected Location location;

    //each person should act ie print out who they are 
    protected abstract void act();
    //each person should set their location 
    protected abstract void setLocation(Location location);
    //used to get each person's location
    protected abstract Location getLocation();

}
