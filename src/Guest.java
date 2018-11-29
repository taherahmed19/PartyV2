
import java.util.ArrayList;

public class Guest extends Person {

    protected int happinessLevel;

    protected int getHappiness() {
        return happinessLevel;
    }

    protected void setHappiness(int level) {
        this.happinessLevel = level;
    }

    public void moveToBestLocation(Field field) {

    }

    @Override
    protected void act(Field field, ArrayList<Person> person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void act() {
        System.out.println("I'm here: Host");
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
    public boolean getSocial() {
        return this.isSocial;
    }

    @Override
    public void setSocial(boolean isSocial) {
        this.isSocial = isSocial;
    }

}
