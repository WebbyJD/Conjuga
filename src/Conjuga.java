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
    public static JTextPane Text_Pane; // The text output in the GUI for Output_String

    public static void main(String[] args){
        Conjuga Conjuga_Instance = new Conjuga(); // Necessary to instantiate class for ActionListener

        Rules_List = readInRules("forms");
        Choice_List = readInChoices("forms");
        Categories = readInCategories("forms", Choice_List.size());

        //Create and set up the window
        JFrame Frame = new JFrame("Conjuga");
        Frame.setPreferredSize(new Dimension(250,170));
        Frame.setBounds(500,500,250,170);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with a button
        JPanel Panel = new JPanel();
        JButton Run_Button = new JButton("Run");
        Run_Button.addActionListener(Conjuga_Instance);
        Panel.add(Run_Button);
        Text_Pane = new JTextPane();
        Panel.add(Text_Pane);
        Frame.getContentPane().add(Panel);

        //Display the window.
        Frame.pack();
        Frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        String Action = e.getActionCommand();
        if (Action.equals("Run")) {
            Output_String = Run(Choice_List, Rules_List, Categories);
            Text_Pane.setText(Output_String);
        }
    }

    public static String Run(ArrayList<LinkedList<String>> Choice_List, ArrayList<LinkedList<Integer>> Rules_Lists, String[] Categories){
        int Choice = -1;
        StringBuilder Full_Out = new StringBuilder();

        for (int i = 0; i < Choice_List.size(); i ++){
            LinkedList<String> Choice_Actual = Choice_List.get(i);
            if (Rules_Lists.size() >= i && Choice > -1 ) {
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

    public static ArrayList<LinkedList<Integer>> readInRules(String File_Name){
        File Rules_Listefile = new File("Option_Files/" + File_Name + ".rules");
        ArrayList<LinkedList<Integer>> Rules_Lists = new ArrayList<>();
        try (Scanner reader = new Scanner(Rules_Listefile)){
            while (reader.hasNextLine()){
                LinkedList<Integer> Rules_Sub_List = new LinkedList<>();
                String Current_Line = reader.nextLine();
                while (true) {
                    int Index_Of_Bracket_Open = Current_Line.indexOf('[');
                    int Index_Of_Bracket_Closed = Current_Line.indexOf(']');
                    int Index_Of_Brace_Open = Current_Line.indexOf('{');
                    int Index_Of_Brace_Closed = Current_Line.indexOf('}');
                    Rules_Sub_List.add(Integer.parseInt(Current_Line.substring(Index_Of_Bracket_Open + 1, Index_Of_Bracket_Closed)));
                    Rules_Sub_List.add(Integer.parseInt(Current_Line.substring(Index_Of_Brace_Open + 1, Index_Of_Brace_Closed)));
                    if (Index_Of_Brace_Closed + 2 < Current_Line.length()) Current_Line = Current_Line.substring(Index_Of_Brace_Closed + 1);
                    else break;
                }
                Rules_Lists.add(Rules_Sub_List);
            }
        } catch (FileNotFoundException e){
            System.out.println("Cannot read rules file");
            e.printStackTrace();
        }
        return Rules_Lists;
    }

    public static ArrayList<LinkedList<String>> readInChoices(String File_Name){
        ArrayList<LinkedList<String>> Choice_List = new ArrayList<>();
        File choice = new File("Option_Files/" + File_Name + ".choice");

        try (Scanner Reader = new Scanner(choice)) {
                int Choice_Index = 0;
                while (Reader.hasNextLine()) {
                    String Current_Line = Reader.nextLine();
                    LinkedList<String> Choice_Sub_List = new LinkedList<>();

                    int Number_At_Line_End = 0;
                    int Index_After_Number = 0;
                    for (int i = Current_Line.length() - 1; i > 1; i --){
                        if (Current_Line.charAt(i) == '['){
                            Index_After_Number = i;
                        }
                        if (Current_Line.charAt(i) == ']' && Index_After_Number != 0){
                            Number_At_Line_End = Integer.parseInt(Current_Line.substring(i + 1, Index_After_Number));
                            break;
                        }
                    }

                    for (int i = 0; i < Number_At_Line_End - Choice_Index; i ++){
                        int Index_Of_End_Bracket = Current_Line.indexOf(']');
                        if (Index_Of_End_Bracket == -1) break;
                        Choice_Sub_List.add(Current_Line.substring(Current_Line.indexOf('[') + 1, Index_Of_End_Bracket));
                        Current_Line = Current_Line.substring(Index_Of_End_Bracket + 1);
                    }
                    Choice_List.add(Choice_Sub_List);
                }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read choices file");
            e.printStackTrace();
        }
        return Choice_List;
    }

    public static String[] readInCategories(String File_Name, int Categories_Length){
        String[] Categories = new String[Categories_Length];
        File choice = new File("Option_Files/" + File_Name + ".categories");

        try (Scanner Reader = new Scanner(choice)) {
            int Category_Index = 0;
            while (Reader.hasNextLine()) {
                Categories[Category_Index] = Reader.nextLine();
                Category_Index ++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read choices file");
            e.printStackTrace();
        }
        return Categories;
    }
}
