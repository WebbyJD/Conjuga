import javax.swing.*;
import java.awt.*;

public class GraphicsHandler {
    public JTextPane Text_Pane; // The text output in the GUI for Output_String
    public JFrame Main_Menu;
    public JFrame Conjugation;

    public void mainMenuPage(){
        Conjuga Conjuga_Instance = new Conjuga();

        Main_Menu = new JFrame("Conjuga Menu");
        Main_Menu.setPreferredSize(new Dimension(300,200));
        Main_Menu.setBounds(500,500,250,170);
        Main_Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with a button
        JPanel Panel = new JPanel();
        Panel.setBounds(0, 0, 200, 300);
        JButton Start_Button = new JButton("Start");
        Start_Button.setBounds(10, 10, 100, 50);
        Start_Button.addActionListener(Conjuga_Instance);
        Panel.add(Start_Button);
        Main_Menu.getContentPane().add(Panel);

        //Display the window
        Main_Menu.pack();
        Main_Menu.setVisible(true);
    }

    public void conjugationPage(){
        Conjuga Conjuga_Instance = new Conjuga(); // Necessary to instantiate class for ActionListener

        //Create and set up the window
        Conjugation = new JFrame("Conjuga");
        Conjugation.setPreferredSize(new Dimension(250,170));
        Conjugation.setBounds(500,500,250,170);
        Conjugation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with a button
        JPanel Panel = new JPanel();
        JButton Run_Button = new JButton("Run");
        Run_Button.addActionListener(Conjuga_Instance);
        Panel.add(Run_Button);
        Text_Pane = new JTextPane();
        Panel.add(Text_Pane);
        Conjugation.getContentPane().add(Panel);

        //Display the window.
        Conjugation.pack();
        Conjugation.setVisible(true);
    }
}
