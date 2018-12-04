
/*
 An Scientist is a type of guest will use methods from super class
 */
public class Scientist extends Guest {

    /**
    *Constructor to create scientist object
     */
    public Scientist() {
        super();
    }
    
    //override super class method as print statement specific for this object
    @Override
    public void act() {
        System.out.println("I'm here: Scientist");
    }

}
