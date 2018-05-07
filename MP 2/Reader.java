import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {

    BufferedReader reader;
    ArrayList<String> traverse;
    Data checkThis;

    public Reader() {
        traverse = new ArrayList<>();
        checkThis = new Data();
    }

    public void process(){
        //ask user the file name
        startReadProcess(getFileName());
        print();
        organize();
        checkThis.process();
    }

    public String getFileName(){
        System.out.print("Please enter filename: ");
        Scanner scan = new Scanner(System.in);
        return scan.next();
    }

    public void startReadProcess(String inputFileName){
        try{
            reader = new BufferedReader(new FileReader(inputFileName));
            String line = reader.readLine();
            while (line != null){
                //read next line
                traverse.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void print() {

        System.out.println("Text file: ");
        for (String s : traverse) {
            System.out.println(s);
        }
        System.out.println();
    }

    public void organize(){

        System.out.println("Organizing...\n");
        int mainCounter = 0;
        int testCaseCounter = 0;
        RegularExpression temp = new RegularExpression();
        boolean hasRegEx = false;

        for (int i = 0; i < traverse.size();i++){
            if (i == 0){
                try{
                    mainCounter = Integer.parseInt(traverse.get(i));
                }catch (Exception e){
                    System.out.println("Wrong format!");
                    break;
                }
            }else{
                if (hasRegEx && testCaseCounter == 0){
                    try{
                        testCaseCounter = Integer.parseInt(traverse.get(i));
                        temp.setNumberOfTestCases(testCaseCounter);
                        hasRegEx = false;
                    } catch (Exception e){
                        System.out.println("Wrong format!");
                        break;
                    }
                } else if (testCaseCounter == 0){
                        if (mainCounter < Integer.parseInt(traverse.get(0))){
                            checkThis.addRegEx(temp);
                        }
                        temp = new RegularExpression();
                        temp.setRegularExp(traverse.get(i));
                        mainCounter--;
                        hasRegEx = true;
                }else {
                    temp.addtestCase(traverse.get(i));
                    testCaseCounter--;
                }
            }

        }
        checkThis.addRegEx(temp);
        checkThis.printData();
    }

}
