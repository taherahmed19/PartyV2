
import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * A simple party simulator, based on a field containing hosts, artists and
 * other persons
 *
 * @author Maria Chli
 * @version 17-10-2007
 */
public class Simulator implements Runnable{

    // The list of persons at the party
    private ArrayList<Person> persons;
    // The current state of the field.
    private Field partyRoom;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;

    public Simulator(int depth, int width, int seed) {

        if (width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = ModelConstants.DEFAULT_DEPTH;
            width = ModelConstants.DEFAULT_WIDTH;
        }
        persons = new ArrayList<Person>();
        partyRoom = new Field(depth, width);
        view = new SimulatorView(depth, width);

        // Create a view of the state of each location in the field.
        view = new SimulatorView(depth, width);
        view.setColor(Artist.class, Color.magenta);
        view.setColor(Host.class, Color.orange);
        view.setColor(Scientist.class, Color.black);
        view.setColor(Engineer.class, Color.blue);

        RandomGenerator.initialiseWithSeed(seed);

        // Setup a valid starting point.
        start();

    }

    public void start() {
        step = 0;

        partyRoom.clear();
        populate(partyRoom);

        // Show the starting state in the view.
        view.showStatus(step, partyRoom);
    }

    /**
     * Populate the field with persons
     */
    private void populate(Field field) {
        double p1 = ModelConstants.ARTIST_CREATION_PROBABILITY;
        double p2 = p1 + ModelConstants.HOST_CREATION_PROBABILITY;
        double p3 = p2 + ModelConstants.SCIENTIST_CREATION_PROBABILITY;
        double p4 = p3 + ModelConstants.ENGINEER_CREATION_PROBABILITY;

        Random rand = RandomGenerator.getRandom();
        field.clear();
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                double p = rand.nextDouble();
                if (0 <= p && p < p1) {
                    Artist artist = new Artist();
                    persons.add(artist);
                    artist.setLocation(new Location(row, col));
                    artist.setSocial(rand.nextBoolean());
                    artist.setHappiness(rand.nextInt(11));
                    field.place(artist, row, col);
                } else if (p1 <= p && p < p2) {
                    Host host = new Host();
                    persons.add(host);
                    host.setLocation(new Location(row, col));
                    host.setSocial(rand.nextBoolean());
                    //not asked to add happiness to host objects?
                    //  host.setHappiness(rand.nextInt(11));
                    field.place(host, row, col);
                } else if (p2 <= p && p < p3) {
                    Scientist scientist = new Scientist();
                    persons.add(scientist);
                    scientist.setLocation(new Location(row, col));
                    scientist.setSocial(rand.nextBoolean());
                    scientist.setHappiness(rand.nextInt(11));
                    field.place(scientist, row, col);
                } else if (p3 <= p && p < p4) {
                    Engineer engineer = new Engineer();
                    persons.add(engineer);
                    engineer.setLocation(new Location(row, col));
                    engineer.setSocial(rand.nextBoolean());
                    engineer.setHappiness(rand.nextInt(11));
                    field.place(engineer, row, col);
                } else {
                    //Create nothing. Leave the location empty.
                }
            }
        }
    }

    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     */
    public void simulate(int numSteps) {
        //   for (int step = -5; step <= numSteps && view.isViable(partyRoom); step++) {
        for (int i = 0; i < numSteps; i++) {
            simulateOneStep(partyRoom);
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            };
        }
    }

    /**
     * Run the simulation from its current state for a single step. Iterate over
     * the whole field updating the state of each person.
     */
    public void simulateOneStep(Field field) {
        step++;
        Location location;
        // let all persons act
        for (Iterator<Person> iter = persons.iterator(); iter.hasNext();) {
            Person person = iter.next();
            person.act();
            person.act(field, persons);
            view.showStatus(step, field);

            if (person instanceof Guest) {
                //   Field field2 = field.cloneField();
                ((Guest) person).moveToBestLocation(field);
                //field = field2;
                view.showStatus(step, field);
            }
        }
        System.out.println(step);
    }

    public static void main(String[] args) {

        //alert user to enter input
        SimulatorSetup setup = new SimulatorSetup();
        //seed must be less than depth / width

    }

    @Override
    public void run() {
        simulate(ModelConstants.LENGTH);
    }

}

//        Scanner scan = new Scanner(System.in);
//        while (!scan.hasNextInt()) {
//            System.out.println("Input is not a number.");
//            enterSimulation();
//            scan.nextLine();
//        }
//        int number = scan.nextInt();
