
public class ModelConstants {
    // The simulation length
    public static int LENGTH = 5;
    // The seed for the shared random number generator.
    public static int SEED = 40;
    // The default width for the grid.
    public static int WIDTH = 50;
    // The default depth of the grid.
    public static int DEPTH = 50;
    // The "manhattan" radius of the neighbourhood. Used by guests to evaluate potential positions to move to.
    public static int DISTANCE = 5;
    
    //probability should equal 1 or greater than 0
    // The probability that a artist will be created in any given grid position.
    public static double ARTIST_CREATION_PROBABILITY = 0.10;
    // The probability that a host will be created in any given grid position.
    public static double HOST_CREATION_PROBABILITY = 0.15;
    // The probability that a scientist will be created in any given grid position.
    public static double SCIENTIST_CREATION_PROBABILITY = 0.10;
    // The probability that as engineer will be created in any given grid position.
    public static double ENGINEER_CREATION_PROBABILITY = 0.10;
}
