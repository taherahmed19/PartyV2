
public abstract class Guest extends Person {

    protected int happinessLevel;
    protected abstract int getHappiness();
    protected abstract void setHappiness(int level);
    public abstract void moveToBestLocation(Field field);
}
