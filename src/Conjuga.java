import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.*;

/*
Variables - Example_Variable
Methods - exampleMethod
 */

class Conjuga extends JFrame implements ActionListener{
    public static ArrayList<LinkedList<Integer>> Rules_List; // List of rules outlining when to exclude certain options
    public static ArrayList<LinkedList<String>> Choice_List; // Available (choices)strings to call organized 2D
    public static String[] Categories;
    public static String Output_String; // A string that contains the entire output of the Run() function, potentially too large
    public static FileHandler Handler;
    public static GraphicsHandler Graphics;

    public static void main(String[] args){
        Handler = new FileHandler("forms");

        Rules_List = Handler.readInRules();
        Choice_List = Handler.readInChoices();
        Categories = Handler.readInCategories(Choice_List.size());

        Graphics = new GraphicsHandler();

        Graphics.mainMenuPage();
    }

    public static String Run(ArrayList<LinkedList<String>> Choice_List, ArrayList<LinkedList<Integer>> Rules_Lists, String[] Categories) {
        int Choice = -1;
        StringBuilder Full_Out = new StringBuilder();

        for (int i = 0; i < Choice_List.size(); i++) {
            LinkedList<String> Choice_Actual = Choice_List.get(i);
            if (Rules_Lists.size() >= i && Choice > -1) {
                for (int j = 0; j < Rules_Lists.get(i - 1).size(); j += 2) {
                    if (Rules_Lists.get(i - 1).get(j) == Choice) {
                        Choice_Actual.remove((int) Rules_Lists.get(i - 1).get(j + 1));
                        //System.out.println(Choice_Actual.remove((int) Rules_Lists.get(i - 1).get(j + 1)));
                    }
                }
            }
            Choice = (int) (Math.random() * Choice_Actual.size());
            Full_Out.append(Categories[i]).append(" is ").append(Choice_Actual.get(Choice)).append("\n");
        }
        System.out.println(Full_Out);
        return Full_Out.toString();
    }

    public void actionPerformed(ActionEvent e)
    {
        String Action = e.getActionCommand();
        switch (Action){
            case ("Start"):
                Graphics.conjugationPage();
                Graphics.Main_Menu.dispose();
                break;
            case ("Run"):
                Output_String = Run(Choice_List, Rules_List, Categories);
                Graphics.Text_Pane.setText(Output_String);
                break;
            default:
                System.exit(0);
        }
    }
}
