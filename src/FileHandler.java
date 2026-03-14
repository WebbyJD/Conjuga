import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class FileHandler {
    public String File_Name;

    public FileHandler(String File_Name){
        this.File_Name = File_Name;
    }

    public ArrayList<LinkedList<Integer>> readInRules(){
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

    public ArrayList<LinkedList<String>> readInChoices(){
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

    public String[] readInCategories(int Categories_Length){
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
