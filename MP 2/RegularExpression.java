import java.lang.reflect.Array;
import java.util.ArrayList;

public class RegularExpression {

    String regularExpression;

    int numberOfTestCases;
    ArrayList<String> testCases;
    ArrayList<String> postfix;

    public RegularExpression() {
        regularExpression = "";
        postfix = new ArrayList<>();
        numberOfTestCases = 0;
        testCases = new ArrayList<>();
    }

    public String getRegularExp() {
        return regularExpression;
    }

    public void setRegularExp(String regularExp) {
        this.regularExpression = regularExp;
    }

    public int getNumberOfTestCases() {
        return numberOfTestCases;
    }

    public void setNumberOfTestCases(int numberOfTestCases) {
        this.numberOfTestCases = numberOfTestCases;
    }

    public void addtestCase(String testCase){
        testCases.add(testCase);
    }

    public String accessTestCase(int i){
        return testCases.get(i);
    }

    public void process(){
        toPostFix();
    }

    public void toPostFix(){

        ArrayList<String> operands = new ArrayList<>();
        ArrayList<String> operations = new ArrayList<>();
        int counter = 0;
        boolean close = false;
        boolean needsX = false;
        int checkNext = 0;

        for (int i = 0; i < regularExpression.length(); i++){

            if (regularExpression.charAt(i) == ' '){

            }else{

                if (needsX){
                    if (regularExpression.charAt(i) == '*'){
                        checkNext++;
                        //do nothing
                    }else if (checkNext > 1){
                        needsX = false;
                        checkNext = 0;
                    }else if (regularExpression.charAt(i) == '('){
                        operations.add("x");
                        needsX = false;
                    }
                }

                if (regularExpression.charAt(i) == '(' && close){
                    operations.add(")");
                    close = false;
                }
                if (i == 0 && regularExpression.charAt(0) != '('){
                    operations.add("(");
                    close = true;
                }
                if (regularExpression.charAt(i) == 'a' || regularExpression.charAt(i) == 'A'){
                    operands.add("a");
                    counter++;
                    if (counter == 2){
                        operations.add("x");
                    }
                }else if (regularExpression.charAt(i) == 'b' || regularExpression.charAt(i) == 'B'){
                    operands.add("b");
                    counter++;
                    if (counter == 2){
                        operations.add("x");
                    }
                }else {
                    operations.add(""+regularExpression.charAt(i));
                    counter = 0;
                    if (regularExpression.charAt(i) == ')'){
                        needsX = true;
                    }
                }

            }
        }

        if (close){
            operations.add(")");
        }

        System.out.println("Operands: ");
        for (String a: operands){
            System.out.print(a+" - ");
        }
        System.out.println("\nOperations: ");
        for (String b: operations){
            System.out.print(b+" - ");
        }

        //toPostFix

        int operandCounter = 0;
        boolean parenthesisSpotted = false;
        ArrayList<String> tempOperations = new ArrayList<>();
        int x = 0;

        while (true){
            if (operations.size() <= 0){
                break;
            }
            if (!operations.contains("(")){
                for (String e: operations){
                    tempOperations.add(e);
                }
            }
            for (int i = 0; i < operations.size(); i++){
                if (operations.get(i).equalsIgnoreCase("*")){
                    tempOperations.add(operations.get(i));
                    operations.remove(i);
                    break;
                }if (operations.get(i).equalsIgnoreCase("(")){
                    int j = i;
                    for (j = i; j < operations.size(); j++){
                        if (tempOperations.equals(")")){
                            break;
                        }
                        tempOperations.add(operations.get(j));
                    }
                    for (int y = i; y <= j; y++){
                        operations.remove(y);
                    }
                    break;
                }

            }

            for (int p = 0; p < tempOperations.size(); p++){
                if (p == '*'){
                    if (postfix.isEmpty()){
                        postfix.add(operands.get(0));
                        operands.remove(0);
                    }else {
                        postfix.add("*");
                    }
                }
                //fixme
            }

        }

    }

}
