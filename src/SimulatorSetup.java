
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.text.NumberFormatter;

public class SimulatorSetup {

    //frame of gui
    private JFrame frame;
    //container to hold layout and content
    private Container contents;
    //label for text host
    private JLabel host = new JLabel("Host");
    //label for text scientist
    private JLabel scientist = new JLabel("Scientist");
    //label for text engineer
    private JLabel enginner = new JLabel("Engineer");
    //label for text artist
    private JLabel artist = new JLabel("artist");
    //label for text simulation length input
    private JLabel sLength = new JLabel("Simulation Length");
    //label for text simulation seed input
    private JLabel seed = new JLabel("Random Number Generator Seed");
    //label for text simulation party room width input
    private JLabel partyWidth = new JLabel("Party Room Width");
    //label for text simulation party room height input
    private JLabel partyHeight = new JLabel("Party Room Height");
    //label for text simulation distance input
    private JLabel distance = new JLabel("Distance To Find People");

    //text area to input value for length
    private JTextArea lengthTextArea = new JTextArea("");
    //text area to input value for seed
    private JTextArea seedTextArea = new JTextArea("");
    //text area to input value for party width
    private JTextArea partyWidthTextArea = new JTextArea("");
    //text area to input value for party height
    private JTextArea partyHeightTextArea = new JTextArea("");
    //text area to input value for distance
    private JTextArea distanceTextArea = new JTextArea("");
    //text area to input value for host probability
    private JTextArea hostTextArea = new JTextArea("");
    //text area to input value for scientist probability
    private JTextArea scientistTextArea = new JTextArea("");
    //text area to input value for engineer probability
    private JTextArea engineerTextArea = new JTextArea("");
    //text area to input value for artist probability
    private JTextArea artistTextArea = new JTextArea("");
    //variables to ensure values are valid
    private boolean hostInput = false, artistInput = false, engineerInput = false, scientistInput = false,
            lengthInput = false, seedInput = false, distanceInput = false, depthInput = false, widthInput = false;

    public SimulatorSetup() {
        //create frame incl size title location
        frame = new JFrame();
        frame.setTitle("Setup Simulation");
        frame.setSize(500, 500);
        //location set to middle of screen
        frame.setLocation((int) ((getWidth() / 2) - (frame.getWidth() / 2)), (int) ((getHeight() / 2)) - (frame.getHeight() / 2));
        // Make sure that closing the window ends the application
        exit();
        contents = frame.getContentPane();
        contents.setLayout(new BorderLayout());

        addButtons();
        frame.setVisible(true);
    }

    /**
     *
     * @return height of screen
     */
    public double getHeight() {
        return Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    }

    /**
     *
     * @return width of screen
     */
    public double getWidth() {
        //return screen width
        return Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }

    /**
     * allow user to exit application
     */
    public void exit() {
        frame.setDefaultCloseOperation(
                WindowConstants.DISPOSE_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * used to add buttons to frame
     */
    public void addButtons() {
        JPanel buttonsPanel = new JPanel();
        //row columns horizontal and vertical gap
        buttonsPanel.setLayout(new GridLayout(11, 11, 3, 3));

        buttonsPanel.add(sLength);
        buttonsPanel.add(lengthTextArea);
        buttonsPanel.add(seed);
        buttonsPanel.add(seedTextArea);
        buttonsPanel.add(partyWidth);
        buttonsPanel.add(partyWidthTextArea);
        buttonsPanel.add(partyHeight);
        buttonsPanel.add(partyHeightTextArea);
        buttonsPanel.add(distance);
        buttonsPanel.add(distanceTextArea);

        buttonsPanel.add(new JLabel());
        buttonsPanel.add(new JLabel("Creation Probability"));
        buttonsPanel.add(host);
        buttonsPanel.add(hostTextArea);
        buttonsPanel.add(scientist);
        buttonsPanel.add(scientistTextArea);
        buttonsPanel.add(enginner);
        buttonsPanel.add(engineerTextArea);
        buttonsPanel.add(artist);
        buttonsPanel.add(artistTextArea);
        JButton enter = new JButton("enter");
        JButton defaultSettings = new JButton("Use Default");
        buttonsPanel.add(enter);
        buttonsPanel.add(defaultSettings);
        contents.add(buttonsPanel, BorderLayout.CENTER);
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateConstants();
            }

        });
        defaultSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startThread();
            }
        });
        frame.setContentPane(contents);
    }
    /**
     * update input values and check values are valid
     */
    public void updateConstants() {
        //ensure any input field is not empty
        if (hostTextArea.getText().equals("") || artistTextArea.getText().equals("")
                || scientistTextArea.getText().equals("") || engineerTextArea.getText().equals("")
                || lengthTextArea.getText().equals("") || seedTextArea.getText().equals("") || partyWidthTextArea.getText()
                .equals("") || partyHeightTextArea.getText().equals("") || distanceTextArea.getText().equals("")) {
            //modal pop up
            JOptionPane.showMessageDialog(null, "Empty Field");
        } else {
            //ensure seed value is in bounds
            if (Integer.parseInt(seedTextArea.getText().replaceAll("\\s+", "")) <= Integer.parseInt(partyHeightTextArea.getText().replaceAll("\\s+", "")) - 2) { //if all inputs are correct ie no string added 
               //ensure value entered is double or 1 and less than or equal to 1 and greater than 0
                updateConstantValues();
                //if all values valid run simulation 
                if (inputsValid()) {
                    startThread();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Seed needs to be less than 2 of the width");
            }

        }
    }

    private void updateConstantValues() {
        //method replace all removes any blank spaces that may occur in input e.g pressing tab/space
        try {
            double value = Double.parseDouble(hostTextArea.getText().replaceAll("\\s+", ""));
            if (value > 1 || value < 0) {
                JOptionPane.showMessageDialog(null, "Value not within bounds");
            } else {
                ModelConstants.HOST_CREATION_PROBABILITY = Double.parseDouble(hostTextArea.getText().replaceAll("\\s+", ""));
                hostInput = true;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Not an INT or Double");
            hostInput = false;
        }
        try {
            double value = Double.parseDouble(artistTextArea.getText().replaceAll("\\s+", ""));
            if (value > 1 || value < 0) {
                JOptionPane.showMessageDialog(null, "Value not within bounds");
            } else {
                ModelConstants.ARTIST_CREATION_PROBABILITY = value;
                artistInput = true;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Not an INT or Double");
            artistInput = false;
        }
        try {
            double value = Double.parseDouble(scientistTextArea.getText().replaceAll("\\s+", ""));
            if (value > 1 || value < 0) {
                JOptionPane.showMessageDialog(null, "Value not within bounds");
            } else {
                ModelConstants.SCIENTIST_CREATION_PROBABILITY = value;
                scientistInput = true;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Not an INT or Double");
            scientistInput = false;
        }
        try {
            double value = Double.parseDouble(engineerTextArea.getText().replaceAll("\\s+", ""));
            if (value > 1 || value < 0) {
                JOptionPane.showMessageDialog(null, "Value not within bounds");
            } else {
                ModelConstants.ENGINEER_CREATION_PROBABILITY = value;
                engineerInput = true;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Not an INT or Double");
            engineerInput = false;
        }
        try {
            int value = Integer.parseInt(lengthTextArea.getText().replaceAll("\\s+", ""));
            if (value < 0) {
                JOptionPane.showMessageDialog(null, "Value not within bounds");
            } else {
                ModelConstants.LENGTH = value;
                lengthInput = true;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Not an INT or Double");
            lengthInput = false;
        }
        try {
            int value = Integer.parseInt(seedTextArea.getText().replaceAll("\\s+", ""));
            if (value < 0) {
                JOptionPane.showMessageDialog(null, "Value not within bounds");
            } else {
                ModelConstants.SEED = value;
                seedInput = true;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Not an INT or Double");
            seedInput = false;
        }
        try {
            int value = Integer.parseInt(partyWidthTextArea.getText().replaceAll("\\s+", ""));
            if (value < 0) {
                JOptionPane.showMessageDialog(null, "Value not within bounds");
            } else {
                ModelConstants.WIDTH = value;
                widthInput = true;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Not an INT or Double");
            widthInput = false;
        }
        try {
            int value = Integer.parseInt(partyHeightTextArea.getText().replaceAll("\\s+", ""));
            if (value < 0) {
                JOptionPane.showMessageDialog(null, "Value not within bounds");
            } else {
                ModelConstants.DEPTH = value;
                depthInput = true;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Not an INT or Double");
            depthInput = false;
        }
        try {
            int value = Integer.parseInt(distanceTextArea.getText().replaceAll("\\s+", ""));
            if (value < 0) {
                JOptionPane.showMessageDialog(null, "Value not within bounds");
            } else {
                ModelConstants.DISTANCE = value;
                distanceInput = true;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Not an INT or Double");
            distanceInput = false;
        }

    }
    /**
     * starts the simulation
     */
    public void startThread() {
        Thread thread = new Thread(new Simulator(ModelConstants.DEPTH, ModelConstants.WIDTH, ModelConstants.SEED));
        thread.start();
        if (thread.isAlive()) {
            frame.hide();
        }
    }
    /**
     * ensures input field are all valid
     * @return boolean true is valid false otherwise
     */
    public boolean inputsValid() {
        if (hostInput && artistInput && engineerInput && scientistInput && lengthInput && seedInput && distanceInput
                && depthInput && widthInput) {
            return true;
        }
        return false;
    }

}
