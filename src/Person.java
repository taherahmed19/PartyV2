

public abstract class Person {
    //each person (host,guest) has a location store in this
    protected Location location;

    //each person should act ie print out who they are 
    protected abstract void act();
    //each person should set their location 
    protected abstract void setLocation(Location location);
    //used to get each person's location
    protected abstract Location getLocation();
    //each person is social or not
    protected boolean isSocial;
    //return person social 
    protected abstract boolean getSocial();
    //set person to be social or not
    protected abstract void setSocial(boolean isSocial);

}
