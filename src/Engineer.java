

/*
 An Engineer is a type of guest will use methods from super class
*/
public class Engineer extends Guest {
    /*
        Construtctor to create engineer object
    */
    public Engineer() {
        super();
    }
    
    //override super class method as print statement specific for this object
    @Override
    public void act() {
        System.out.println("I'm here: Engineer");
    }

}
