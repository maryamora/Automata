import java.util.ArrayList;

public class Data {

    int numberOfRegEx;
    ArrayList<RegularExpression> regularExpressions;

    public Data() {
        numberOfRegEx = 0;
        regularExpressions = new ArrayList<>();
    }

    public int getNumberOfRegEx() {
        return numberOfRegEx;
    }

    public void setNumberOfRegEx(int numberOfRegEx) {
        this.numberOfRegEx = numberOfRegEx;
    }

    public void addRegEx(RegularExpression newRegEx){
        regularExpressions.add(newRegEx);
    }

    public void printData(){
        for (RegularExpression re: regularExpressions){
            System.out.println("Regular Expression : "+re.getRegularExp());
            for (int i = 0; i < re.getNumberOfTestCases(); i++){
                System.out.println("test case "+(i+1)+" = "+re.accessTestCase(i));
            }
            System.out.println();
        }
    }

    public void process(){
        for (RegularExpression re: regularExpressions){
            re.process();
        }
    }

}