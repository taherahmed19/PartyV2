import java.util.HashMap;
import java.util.Iterator;


public class FieldStats
{
    // counter for each type of host or guest in the simulation.
    private HashMap<Class, Counter> counters;
    // value to ensure counter is up to date.
    private boolean countsValid;

    /**
     * Contructor for fieldstats - used to create object of this.
     */
    public FieldStats()
    {
        // Set up a collection for counters for each type of person that
        // we might find
        counters = new HashMap<Class, Counter>();
        countsValid = true;
    }

    /**
     * @return A string describing who is in the field.
     */
    public String getPopulationDetails(Field field)
    {
        StringBuffer buffer = new StringBuffer();
        if(!countsValid) {
            generateCounts(field);
        }
        Iterator<Class> keys = counters.keySet().iterator();
        while(keys.hasNext()) {
            Counter info = counters.get(keys.next());
            buffer.append(info.getName());
            buffer.append(": ");
            buffer.append(info.getCount());
            buffer.append(' ');
        }
        return buffer.toString();
    }
    
    /**
     * Invalidate the current set of statistics; reset all 
     * counts to zero.
     */
    public void reset()
    {
        countsValid = false;
        Iterator<Class> keys = counters.keySet().iterator();
        while(keys.hasNext()) {
            Counter cnt = counters.get(keys.next());
            cnt.reset();
        }
    }

    /**
     * Increment the count for one class of animal.
     */
    public void incrementCount(Class animalClass)
    {
        Counter cnt = (Counter) counters.get(animalClass);
        if(cnt == null) {
            // we do not have a counter for this species yet - create one
            cnt = new Counter(animalClass.getName());
            counters.put(animalClass, cnt);
        }
        cnt.increment();
    }

    /**
     * Indicate that an person count has been completed.
     */
    public void countFinished()
    {
        countsValid = true;
    }

    /**
     * Determine whether the simulation is still viable.
     * I.e., should it continue to run.
     * @return true If there is more than one types of Person present.
     */
    public boolean isViable(Field field)
    {
        // How many counts are non-zero.
        int nonZero = 0;
        if(!countsValid) {
            generateCounts(field);
        }
        Iterator<Class> keys = counters.keySet().iterator();
        while(keys.hasNext()) {
            Counter info = counters.get(keys.next());
            if(info.getCount() > 0) {
                nonZero++;
            }
        }
        return nonZero > 1;
    }
    
    /**
     * Generate counts of the number of persons.
     * These are not kept up to date as persons
     * are placed in the field, but only when a request
     * is made for the information.
     */
    private void generateCounts(Field field)
    {
        reset();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                Object animal = field.getObjectAt(row, col);
                if(animal != null) {
                    incrementCount(animal.getClass());
                }
            }
        }
        countsValid = true;
    }
}