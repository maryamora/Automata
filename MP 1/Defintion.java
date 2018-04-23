
import java.util.ArrayList;

public class Defintion {

    ArrayList<String> tokenized;
    ArrayList<Variables> vars = new ArrayList<>(); //parameter
    ArrayList<Integer> pattern = new ArrayList<>();
    int x, y;

    int[][] checker = new int[][]{

            { 1,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
            {17,	2,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
            {17,	17,	3,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
            {17,	17,	17,	4,	17,	6,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
            {17,	17,	17,	17,	5,	17,	17,	17,	17, 17,	17,	17,	17,	17,	17,	17,	17,	17},
            {17,	17,	17,	17,	17,	6,	17,	17,	17,	17,	17,	17,	3,	17,	17,	17,	17,	17},
            {17,	17,	17,	17,	17,	17,	7,  17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17},
            {17,	17,	17,	17,	17,	17,	17,	8,	9,	17,	17,	17,	17,	17,	15,	17,	16,	17},
            {17,	17,	17,	17,	17,	17,	17,	17,	9,	17,	17,	17,	17,	17,	17,	17,	17,	17},
            {17,	17,	17,	17,	17,	17,	17,	17, 17,	10,	17,	17,	8,	7,	17,	17,	17,	17},
            {17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	11,	17,	17,	17,	17,	17,	17,	17},
            {17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	10,	8,	7,	17,	17,	17,	17},
            {17,	17, 17,	17, 17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	8,	7,	17,	17},
            {17,	17, 17,	17, 17,	17,	17,	17,	17,	17,	17,	17,	17,	7,	17,	17,	17,	17},
            {17,	17, 17,	17, 17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	15,	17,	16,	17},
            {17,	17, 17,	17, 17,	17,	17,	17,	17,	17,	17,	17,	17, 17,	17,	16,	16,	17},
            {17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	15,	17,	16,	17,	17,	17,	17},
            {17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17,	17, 17,	17,	17}

    };


    public Defintion(ArrayList<String> tokenized) {
        this.tokenized = tokenized;
        x = 0;
        y = 0;

        while (true){
            if (tokenized.contains("")){
                tokenized.remove("");
            }else{
                break;
            }
        }
    }

    public boolean checkFunctionName(String s){
        if (s.equals(null)){
            return false;
        } else if (s.equals("")){
            return false;
        } else if (s.charAt(0) >= '0' && s.charAt(0) <= '9'){
            return false;
        } else {
            for (int i = 0; i < s.length(); i++){
                if ((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')){

                }else{
                    if (s.charAt(i) == '_' || (i != 0 && s.charAt(i) >= '0' && s.charAt(i) < '9')){
                        //ok
                    }else{
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean checkVar(String s){
        if (checkFunctionName(s)){
            if (vars.isEmpty()){
                return true;
            }else{
                for (Variables v: vars){
                    if (v.getVariable().equals(s)){
                        return false;
                    }
                }
            }
        }else {
            return false;
        }
        return true;
    }

    public boolean checkIfOpenCurly(String s){
        if (s.equals("{")){
            return true;
        }
        return false;
    }

    public boolean checkIfReturn(String s){
        if (s.equals("return")){
            return true;
        }
        return false;
    }

    public boolean checkIfCloseCurly(String s){
        if (s.equals("}")){
            return true;
        }
        return false;
    }

    public boolean containsVar(String variableName){
        for (Variables varss: vars){
            if (varss.getVariable().equals(variableName)){
                return true;
            }
        }
        return false;
    }

    public Variables findThis(String str){
        Variables tempVar;
        tempVar = null;

        for (Variables ss: vars){
            if (ss.getVariable().equals(str)){
                tempVar = new Variables();
                tempVar.setType(ss.getType());
                tempVar.setVariable(ss.getVariable());
                return tempVar;
            }
        }
        return tempVar;
    }

    public boolean checkIfCost(String str, String currentVariable){
        //check if str is equal to currentVariable, if so then it's wrong
        //else, check whether str is equal to a variable in vars
        //^ if so, then check whether str and currentVariable has same type
        //else, check whether ... normal cost checking

        //boolean checked = false;

        //additional requirement

        Variables currentVariableV = findThis(currentVariable);


        //1st
            if (str.equals(currentVariable)){
                return false;
            }else{

        //check if str is equal to a variable in vars

            if (containsVar(str)){
                //specialized checking
                Variables strV = findThis(str);
                if (currentVariableV.getType().equals(strV.getType())){
                    return true;
                }else{
                    if ((currentVariableV.getType().equals("integer") || currentVariableV.getType().equals("float") || currentVariableV.getType().equals("double")) && (strV.getType().equals("integer") || strV.getType().equals("float") || strV.getType().equals("double"))){
                        return true;
                    }else{
                        return false;
                    }
                }
            }else{
            //normal checking
            //check for integer

                if (currentVariableV.getType().equals("integer") || currentVariableV.getType().equals("float") || currentVariableV.getType().equals("double")){
                    try{
                        Double.parseDouble(str);
                    }catch (Exception e){
                        return false;
                    }
                    return true;
                }else if (currentVariableV.getType().equals("character")){
                    if (str.length() < 3){
                        return false;
                    }
                    if (str.charAt(0) == 39 && str.charAt(2) == 39){
                        return true;
                    }else{
                        return false;
                    }
                }else if (currentVariableV.getType().equals("string")){
                    if (str.length() < 3){
                        return false;
                    }
                    if (str.charAt(0) == '"' && str.charAt(2) == '"'){
                        return true;
                    }else{
                        return false;
                    }
                }else {
                    return false;
                }
            }
        }
    }

    boolean checkIfCorrectReturnType(String str, String returnType){


        if (containsVar(str)){
            Variables yes = findThis(str);
            if (yes.getType().equals(returnType)){
                return true;
            }else {
                return false;
            }
        }else{
            if (returnType.equals("integer") || returnType.equals("float") || returnType.equals("double")){
                try{
                    Double.parseDouble(str);
                }catch (Exception e){
                    return false;
                }
                return true;
            }else if (returnType.equals("character")){
                if (str.length() < 3){
                    return false;
                }
                if (str.charAt(0) == 39 && str.charAt(2) == 39){
                    return true;
                }else {
                    return false;
                }
            } else if (returnType.equals("string")){
                if (str.length() < 3){
                    return false;
                }
                if (str.charAt(0) == '"' && str.charAt(2) == '"'){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }

    public String process(){
        Declaration dec = new Declaration(tokenized);
        String returnType = "";
        String currentParameter = "";
        boolean comma = false;
        boolean currentP = false;
        String currentVariable = "";
        boolean lineIsADeclaration = false;
        boolean closedCurly = false;

        for (String str: tokenized){
            if (x == 0){
                //return type
                if (checkReturnType(str).equals("")){
                    x = 17;
                }else{
                    returnType = checkReturnType(str);
                    x = checker[0][0];
                }
            } else if (x == 1){
                //function name
                if (checkFunctionName(str)){
                    x = checker[1][1];
                }else{
                    x = 17;
                }
            } else if (x == 2){
                //(
                if (dec.checkIfOpenParenthesis(str)){
                    x = checker[2][2];
                }else {
                    x = 17;
                }
            } else if (x == 3){
                //parameter type
                if (checkReturnType(str).equals("")){
                    x = 17;
                }else {
                    currentParameter = checkReturnType(str);
               //     System.out.println("CURRENT TYPE: "+currentParameter);
                    if (returnType.equals("void")){
                        x = 17;
                    }else{
                        if (comma){
                            x = 17;
                        }
                        x = checker[3][3];
                    }
                }
                comma = false;
            } else if (x == 4){
                //parameter name
                if (checkVar(str)){
                    Variables newVar = new Variables();
                    newVar.setType(currentParameter);
                    newVar.setVariable(str);
                    vars.add(newVar);
                    x = checker[4][4];
                }else if (dec.checkIfCloseParenthesis(str)){
                    x = checker[3][5];
                }else {
                    x = 17;
                }
            } else if (x == 5){
                if (dec.checkIfCloseParenthesis(str)){
                    x = checker[5][5];
                } else if (dec.checkIfComma(str)){
                    comma = true;
                    x = checker[5][12];
                } else {
                    x = 17;
                }
            } else if (x == 6){
                if (checkIfOpenCurly(str)){
                    x = checker[6][6];
                }else {
                    x = 17;
                }
            } else if (x == 7){
                if (checkIfReturn(str)){
                    x = checker[7][14];
                }else{

                    if (!checkReturnType(str).equals("") || !checkReturnType(str).equals("void")){
                        currentParameter = checkReturnType(str);
                        currentP = true;
                        x = checker[7][7];
                        lineIsADeclaration = true;
                    }else{
                        boolean isVar = false;
                        //check if body name
                        for (Variables z: vars){
                            if (str.equals(z.getVariable())){
                                x = checker[7][8];
                                currentVariable = str;
                                isVar = true;
                            }
                        }
                        if (!isVar){
                            if (checkIfReturn(str)){
                                x = checker[7][14];
                            }else if (checkIfCloseCurly(str)){
                                x = checker[7][16];
                                closedCurly = true;
                            }else{
                                x = 17;
                            }
                        }
                        isVar = false;
                    }
                }
            } else if (x == 8){
                //check body name
                if (currentP){
                    if (checkVar(str)) {
                        Variables tempV = new Variables();
                        tempV.setType(currentParameter);
                        tempV.setVariable(str);
                        vars.add(tempV);
                        x = checker[8][8];
                        currentVariable = str;
                    }else {
                        x = 17;
                    }
                }else{
                    x = 17;
                }
            } else if (x == 9){
                if (dec.checkIfEquals(str)){
                    x = checker[9][9];
                } else if (dec.checkIfComma(str)){
                    x = checker[9][12];
                } else if (dec.checkIfSemiColon(str)){
                    x = checker[9][13];
                    lineIsADeclaration = false;
                } else {
                    x = 17;
                }
            } else if (x == 10){
                //cost
                //check if current variable will equals to cost
                //check if current variable's cost is a variable and check whether the variable is the same type
                if (checkIfCost(str, currentVariable)){
                    x = checker[10][10];
                }
            }else if (x == 11){
                if (dec.checkIfOperand(str)){
                    x = checker[11][11];
                } else if (lineIsADeclaration && dec.checkIfComma(str)){
                    x = checker[11][12];
                } else if (dec.checkIfSemiColon(str)){
                    x = checker[11][13];
                    lineIsADeclaration = false;
                }else {
                    x = 17;
                }
            }else if (x == 12){
                if (lineIsADeclaration && dec.checkIfComma(str)){
                    x = checker[12][12];
                } else if (dec.checkIfSemiColon(str)){
                    x = checker[12][13];
                    lineIsADeclaration = false;
                }else {
                    x = 17;
                }
            } else if (x == 13){
                if (dec.checkIfSemiColon(str)){
                    x = checker[13][13];
                }
            } else if (x == 14){
                if (checkIfReturn(str)){
                    x = checker[14][14];
                }else if (checkIfCloseCurly(str)){
                    closedCurly = true;
                    x = checker[14][16];
                }
            } else if (x == 15){
                //return cost
            //    System.out.println("return type: "+returnType);
                if (checkIfCorrectReturnType(str,returnType)){
                    x = checker[15][15];
                }else if (checkIfCloseCurly(str)){
                    closedCurly = true;
                    x = checker[15][16];
                }else{
                    x = 17;
                }
            } else if (x == 16){
                if (checkIfCloseCurly(str) && !closedCurly){
                    x = 16;
                    closedCurly = true;
                } else if (dec.checkIfOperand(str)){
                    x = checker[16][11];
                }else if (dec.checkIfSemiColon(str)){
                    x = checker[16][13];
                }else {
                    x = 17;
                }
            }
            pattern.add(x);
        }

        if (pattern.contains(17)){
            //System.out.println("INVALID FUNCTION DEFINITION");
            return "INVALID FUNCTION DEFINITION";
        }else{
            //System.out.println("VALID FUNCTION DEFINITION");
            return "VALID FUNCTION DEFINITION";
        }
    }

    public String checkReturnType(String s){
        if (s.equals("int")){
            return "integer";
        }else if (s.equals("float")){
            return "float";
        }else if (s.equals("double")){
            return "double";
        }else if (s.equals("char")){
            return "character";
        }else if (s.equals("String")){
            return "string";
        }else if (s.equals("void")){
            return "void";
        }
        return "";
    }
}