
/*
 An artist is a type of guest will use methods from super class
*/
public class Artist extends Guest {
    
    /*
        Construtctor to create artist object
    */
    public Artist() {
        super();
    }
    
    //override super class method as print statement specific for this object
    @Override
    public void act() {
        System.out.println("I'm here: Artist");
    }
}
