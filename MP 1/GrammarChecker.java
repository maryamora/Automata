import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GrammarChecker {

    BufferedReader reader;

    int numberOfLines;
    ArrayList<String> checkThis;
    boolean function;
    boolean notDoneDec = true;
    ArrayList<String> toPrint;

    public GrammarChecker(){
        //constructor
        numberOfLines = 0;
        checkThis = new ArrayList<>();
        function = false;
        toPrint = new ArrayList<>();
    }

    public void process(){
        //ask user the file name
        startReadProcess(getFileName());
    }

    public String getFileName(){
        System.out.println("Please enter filename: ");
        Scanner scan = new Scanner(System.in);
        return scan.next();
    }

    public void startReadProcess(String inputFileName){
        try{
            reader = new BufferedReader(new FileReader(inputFileName));
            String line = reader.readLine();
            int counter = 0;
            while (line != null){
                if (counter == 0){

                   try{
                       numberOfLines = Integer.parseInt(line);
                   }catch (Exception e){
                       break;
                   }

                }else{

                    tokenize(line);
                    if (defOrFunc(line) && function == false){
                        if (checkEndOfDeclaration(line)) {
                            Declaration processThis = new Declaration(checkThis);
                            toPrint.add(processThis.process());

                            checkThis.removeAll(checkThis);
                            checkThis = new ArrayList<>();

                        }
                        //step 1 for definition, tokenize line
                        //step 2 the process
                    }else{

                        function = true;

                        if (checkEndOfFunc(line)){
                            //check here
                            Defintion def = new Defintion(checkThis);
                            toPrint.add(def.process());
                            function = false;
                            checkThis.removeAll(checkThis);
                            checkThis = new ArrayList<>();
                        }

                    }
                }
                //read next line
                line = reader.readLine();
                counter++;
            }
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        print();
    }


    public void print(){

        //printing in the file

        for (String s: toPrint){
            System.out.println(s);
        }

        BufferedWriter bw = null;
        try{
            bw = new BufferedWriter(new FileWriter("mpa.out"));
            for (String string: toPrint){
                bw.append(string);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if (bw != null){
                    bw.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    boolean checkEndOfFunc(String line){

        for (int i = 0; i < line.length(); i++){
            if (line.charAt(i) == '}'){
                return true;
            }
        }

        return false;
    }

    boolean checkEndOfDeclaration(String line){

        for (int i = 0; i < line.length(); i++){
            if (line.charAt(i) == ';'){
                return true;
            }
        }
        return false;
    }

    boolean defOrFunc(String line){
        for(int i = 0; i < line.length(); i++){
            if (line.charAt(i) == '{'){
                return false;
            }
        }
        return true;
    }

    void tokenize(String line){
        String temp = "";
        boolean added = false;

        for(int i = 0; i < line.length(); i++){
            if (line.charAt(i) == ' '){
                //if space
                if (temp.equals("") || temp == null|| temp.equals(" ")){
                    //do nothing
                }else{
                    checkThis.add(temp);
                    temp = "";
                }

            }else if (line.charAt(i) == ',' || line.charAt(i) == ';' || line.charAt(i) == '=' || line.charAt(i) == '(' || line.charAt(i) == ')' || line.charAt(i) == '{' || line.charAt(i) == '}' || line.charAt(i) == '+' || line.charAt(i) == '-' || line.charAt(i) == '*' || line.charAt(i) == '/' ){
                if (!temp.isEmpty() && !temp.equals(" ") && !temp.equals("")){
                    added = true;
                    checkThis.add(temp);
                }
                temp = "";
                checkThis.add(""+ line.charAt(i));
            }else {
                temp = temp +line.charAt(i);
            }

            if (!added && i == (line.length() - 1)){
                checkThis.add(temp);
            }
            added = false;
        }

    }

}