
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.NumberFormatter;

public class SimulatorSetup {

    private JFrame frame;
    private Container contents;
    Simulator s;

    public SimulatorSetup() {

        frame = new JFrame();
        frame.setTitle("Setup Simulation");
        frame.setSize(500, 500);
        frame.setLocation((int) ((getWidth() / 2) - (frame.getWidth() / 2)), (int) ((getHeight() / 2)) - (frame.getHeight() / 2));
        // Make sure that closing the window ends the application
        exit();
        contents = frame.getContentPane();
        contents.setLayout(new BorderLayout());

        addButtons();
        frame.setVisible(true);
    }

    public double getHeight() {
        return Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    }

    public void exit() {
        frame.setDefaultCloseOperation(
                WindowConstants.DISPOSE_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public double getWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }
    //creation probability
    JLabel host = new JLabel("Host");
    JLabel scientist = new JLabel("Scientist");
    JLabel enginner = new JLabel("Engineer");
    JLabel artist = new JLabel("artist");
    NumberFormat longFormat = NumberFormat.getIntegerInstance();
    NumberFormatter numberFormatter = new NumberFormatter(longFormat);

    JTextArea lengthTextArea = new JTextArea("");
    JTextArea seedTextArea = new JTextArea("");
    JTextArea partyWidthTextArea = new JTextArea("");
    JTextArea partyHeightTextArea = new JTextArea("");
    JTextArea distanceTextArea = new JTextArea("");
    JTextArea hostTextArea = new JTextArea("");
    JTextArea scientistTextArea = new JTextArea("");
    JTextArea engineerTextArea = new JTextArea("");
    JTextArea artistTextArea = new JTextArea("");
    int hostText, distanceText, artistText, scientistText, engineerText,
            sLengthText, seedText, partyHeightText, partyWidthText;

    public void addButtons() {
        JPanel buttonsPanel = new JPanel();
        //row columns horizontal and vertical gap
        buttonsPanel.setLayout(new GridLayout(11, 11, 3, 3));

        JLabel sLength = new JLabel("Simulation Length");

        JLabel seed = new JLabel("Random Number Generator Seed");

        //sLength.setBackground(Color.BLUE);
        JLabel partyWidth = new JLabel("Party Room Width");

        JLabel partyHeight = new JLabel("Party Room Height");

        JLabel distance = new JLabel("Distance To Find People");

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
        buttonsPanel.add(enter);
        contents.add(buttonsPanel, BorderLayout.CENTER);
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (hostTextArea.getText().equals("") || artistTextArea.getText().equals("")
                        || scientistTextArea.getText().equals("") || engineerTextArea.getText().equals("")
                        || lengthTextArea.getText().equals("") || seedTextArea.getText().equals("") || partyWidthTextArea.getText()
                        .equals("") || partyHeightTextArea.getText().equals("") || distanceTextArea.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Empty Field");
                } else {
                    if (Integer.parseInt(seedTextArea.getText()) <= Integer.parseInt(partyHeightTextArea.getText()) - 2) {
                        ModelConstants.HOST_CREATION_PROBABILITY = Double.parseDouble(hostTextArea.getText());
                        ModelConstants.ARTIST_CREATION_PROBABILITY = Double.parseDouble(artistTextArea.getText());
                        ModelConstants.SCIENTIST_CREATION_PROBABILITY = Double.parseDouble(scientistTextArea.getText());
                        ModelConstants.ENGINEER_CREATION_PROBABILITY = Double.parseDouble(engineerTextArea.getText());

                        ModelConstants.LENGTH = Integer.parseInt(lengthTextArea.getText());
                        ModelConstants.SEED = Integer.parseInt(seedTextArea.getText());
                        ModelConstants.DEFAULT_WIDTH = Integer.parseInt(partyWidthTextArea.getText());
                        ModelConstants.DEFAULT_DEPTH = Integer.parseInt(partyHeightTextArea.getText());
                        ModelConstants.WINDOW = Integer.parseInt(distanceTextArea.getText());
                        Thread thread = new Thread(new Simulator(ModelConstants.DEFAULT_DEPTH, ModelConstants.DEFAULT_WIDTH, ModelConstants.SEED));
                        thread.start();
                        if(thread.isAlive()){
                            System.out.println("test");
                            frame.hide();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Seed needs to be less than 2 of the width");
                    }

                }

            }

        });
        frame.setContentPane(contents);
    }

    public int getHostInput() {
        return hostText;
    }

    public int getArtistInput() {
        return artistText;
    }

    public int getScientistInput() {
        return scientistText;
    }

    public int getEngineerInput() {
        return engineerText;
    }

    public int getSimulationLengthInput() {
        return sLengthText;
    }

    public int getSeedInput() {
        return seedText;
    }

    public int getPartyHeightInput() {
        return partyHeightText;
    }

    public int getPartryWidthInput() {
        return partyWidthText;
    }

    public int getDistanceInput() {
        return distanceText;
    }

//    @Override
//    public void run() {
//        s = new Simulator(ModelConstants.DEFAULT_DEPTH, ModelConstants.DEFAULT_WIDTH, ModelConstants.SEED);
//        s.simulate(ModelConstants.LENGTH);
//    }
}
