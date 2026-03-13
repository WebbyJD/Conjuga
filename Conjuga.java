import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.*;

class Conjuga extends JFrame implements ActionListener{

    public static ArrayList<LinkedList<String>> clist;
    public static ArrayList<int[]> rul;
    public static String out;
    public static JTextPane text;

    public static void main(String[] args){
        Conjuga c = new Conjuga();

        clist = Read_In_Options("forms");
        rul = Read_In_Rules("forms");

        //Create and set up the window.
        JFrame frame = new JFrame("Conjuga");
        frame.setPreferredSize(new Dimension(400,300));
        frame.setBounds(500,500,400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with a button
        JPanel panel = new JPanel();
        JButton runButton = new JButton("Run");
        runButton.addActionListener(c);
        panel.add(runButton);
        //JLabel[] jls = new JLabel[clist.size()];
        text = new JTextPane();
        panel.add(text);
        frame.getContentPane().add(panel);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


    // if the button is pressed
    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        if (s.equals("Run")) {
            out = Run(clist, rul);
            text.setText(out);
        }
    }

    public static String Run(ArrayList<LinkedList<String>> choi, ArrayList<int[]> rules){
        int prev_choice = -1;
        String Full_Out = "";
        for (int i = 0; i < choi.size(); i ++){
            LinkedList<String> choice_actual = choi.get(i);
            if (prev_choice > -1 && rules.size() >= i && rules.get(i - 1)[0] == prev_choice){
                choice_actual.remove(rules.get(i - 1)[1]);
            }
            int choice = (int) (Math.random() * choice_actual.size());
            prev_choice = choice;
            Full_Out = ( Full_Out + "Choice for stage " + (i + 1) + " is " + choice_actual.get(choice) + "\n");
        }
        System.out.println(Full_Out);
        return Full_Out;
    }

    public static ArrayList<int[]> Read_In_Rules(String File_Name){
        File rulefile = new File("Choices/" + File_Name + ".rules");
        ArrayList<int[]> rules = new ArrayList<>();
        try (Scanner reader = new Scanner(rulefile)){
            while (reader.hasNextLine()){
                int[] temp = new int[2];
                String pa = reader.nextLine();
                temp[0] = Integer.parseInt(pa.charAt(0) + "");
                temp[1] = Integer.parseInt(pa.charAt(2) + "");
                rules.add(temp);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        //System.out.println(rules.get(0)[0] + " " + rules.get(0)[1]);
        return rules;
    }

    public static ArrayList<LinkedList<String>> Read_In_Options(String File_Name){
        String alp = "abcdefghijklmnopqrstuvwxyz";
        ArrayList<LinkedList<String>> choices = new ArrayList<>();
        File choice = new File("Choices/" + File_Name + ".choice");

        try (Scanner reader = new Scanner(choice)) {
            int prev_eolli = 0;
            while (reader.hasNextLine()){
                String line = reader.nextLine();
                int eolli = 0;
                for (int i = line.length() - 1; i > 1; i --){
                    if (line.charAt(i) == '[') {
                        eolli = alp.indexOf(line.charAt(i - 1));
                        break;
                    }
                }

                LinkedList<String> cl = new LinkedList<>();

                for (int j = prev_eolli; j < eolli; j ++){
                    int ht = line.indexOf("]"); // HT is just made up, used for remembering where the word ends "]"
                    if (ht < 0) break;
                    cl.add(line.substring(line.indexOf("[") + 1, ht));
                    line = line.substring(ht + 1);
                }
                choices.add(cl);
                prev_eolli = eolli;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read file");
            e.printStackTrace();
        }
        return choices;
    }
}
