
import java.util.Random;

class RandomGenerator {

    private static Random random = null;
    private static int seed = 27;
    
    //intialises random object and updates seed value
    public static void initialiseWithSeed(int s) {
        seed = s;
        random = new Random(seed);
    }
    
    //returns reandom object 
    public static Random getRandom() {
        if (random == null) {
            random = new Random(seed);
        }
        return random;
    }
}
