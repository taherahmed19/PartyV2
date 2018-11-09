
public class ModelConstants 
{
	// The simulation length
    public static int LENGTH = 125;
	// The seed for the shared random number generator.
    public static int SEED = 125;
	// The default width for the grid.
	public static int DEFAULT_WIDTH = 50;
	// The default depth of the grid.
	public static int DEFAULT_DEPTH = 50;
	// The "manhattan" radius of the neighbourhood. Used by guests to evaluate potential positions to move to.
	public static int WINDOW = 10;
	// The probability that a artist will be created in any given grid position.
	public static double ARTIST_CREATION_PROBABILITY = 0.10;
	// The probability that a host will be created in any given grid position.
	public static double HOST_CREATION_PROBABILITY = 0.20;    
	// The probability that a scientist will be created in any given grid position.
	public static double SCIENTIST_CREATION_PROBABILITY = 0.05;
	// The probability that as engineer will be created in any given grid position.
	public static double ENGINEER_CREATION_PROBABILITY = 0.05;	
}
