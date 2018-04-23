import java.util.ArrayList;

public class Declaration {

    ArrayList<String> tokenized;
    ArrayList<Integer> pattern;
    String dataType;
    ArrayList<String> variables;
    int x;
    int y;

    int[][] checker = new int[][]{

    //      type, var, ',', ';', '=',    cost,   operation, fail

            { 1,   7,   7,   7,   7,      7,       7,        7}, //type
            { 7,   2,   7,   7,   7,      7,       7,        7}, //var
            { 7,   7,   1,   3,   4,      7,       5,        7}, //','
            { 7,   7,   7,   3,   7,      7,       7,        7}, //';'
            { 7,   7,   7,   7,   5,      7,       7,        7}, //'='
            { 0,   0,   0,   0,   7,      2,       6,        7}, //cost
            { 7,   7,   7,   7,   7,      7,       5,        7}, //operation
            { 7,   7,   7,   7,   7,      7,       7,        7}  //fail
    };

    int [][] checkerFunction = new int[][]{

          //output   //name of     //(     //inside     //inside     //,    //)     //;     //fail
          //type     //function            //type       //var
            {1,         8,          8,         8,          8,         8,      8,      8,        8}, //output type       0
            {8,         2,          8,         8,          8,         8,      8,      8,        8}, //name of function  1
            {8,         8,          3,         8,          8,         8,      8,      8,        8}, //(                 2
            {8,         8,          8,         4,          8,         8,      8,      8,        8}, //inside type       3
            {8,         8,          8,         8,          5,         3,      7,      8,        8}, // inside var       4
            {8,         8,          8,         8,          8,         3,      7,      8,        8}, //,                 5
            {8,         8,          8,         8,          8,         8,      7,      8,        8}, //)                 6
            {8,         8,          8,         8,          8,         8,      8,      7,        8}, //;                 7
            {8,         8,          8,         8,          8,         8,      8,      8,        8}, //fail              8
    };
//          0           1           2          3           4          5       6       7         8

    public Declaration(ArrayList<String> line){
       tokenized = line;
       x = 0;
       y = 0;
       dataType = "integer";
       pattern = new ArrayList<Integer>();
       variables = new ArrayList<>();
    }

    public String checkType(String checkThis){

            if (checkThis.equals("int")){
                dataType = "integer";
            }else if (checkThis.equals("char")){
                dataType = "character";
            }else if (checkThis.equals("float")){
                dataType = "float";
            }else if (checkThis.equals("double")){
                dataType = "double";
            }else if (checkThis.equals("String")){
                dataType = "string";
            }else{
                dataType = null;
            }

        return dataType;
    }

    public boolean checkIfVariable(String checkThis){
       for (int i = 0; i < checkThis.length(); i++){
           if (i == 0){
               if (checkThis.charAt(i) >= '0' && checkThis.charAt(i) <= '9'){
                   return false;
               }
           }
           if (checkThis.charAt(i) <= 'A' && checkThis.charAt(i) >= 0){
               return false;
           }
           if (checkThis.charAt(i) > 'Z' && checkThis.charAt(i) < 'a'){
               return false;
           }
           if (checkThis.charAt(i) > 'z'){
               return false;
           }
       }
       if (variables.contains(checkThis)){
           return false;
       }
       variables.add(checkThis);
       return true;
    }

    public boolean checkIfComma(String s){
        if (s.equals(",")){
            return true;
        }
        return false;
    }

    public boolean checkIfSemiColon(String s){
        if (s.equals(";")){
            return true;
        }
        return false;
    }

    public boolean checkIfOperand(String s){
        if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")){
            return true;
        }
        return false;
    }

    public boolean checkIfEquals(String s){
        if (s.equals("=")){
            return true;
        }
        return false;
    }

    public boolean ifNumber(String s){
        try {
            Double.parseDouble(s);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean ifCharacter(String s){
        if (s.length() == 3){
            if (s.charAt(0) == 39 && s.charAt(2) == 39){
                return true;
            }else{
                try{
                 // System.out.println("not correct sa katong ' '");
                    Integer.parseInt(s);
                }catch (Exception e){
                    return false;
                }
            }
        }else{
            try{
                Integer.parseInt(s);
            } catch (Exception e){
                return false;
            }
        }
        return true;
    }

    public boolean ifString(String s){
        try{
            Double.parseDouble(s);
        }catch (Exception e){
            if (s.charAt(0) == '"' && s.charAt((s.length()-1)) == '"'){
                return true;
            }
        }
        return false;
    }

    public String process(){

        int counter = 0;
        boolean varDec = true;

        for (String i: tokenized){
            if (counter == 2){
                if (i.equals("(")){
                    varDec = false;
                }
            }

            counter++;
        }

        if (varDec){
            return variableDeclarationProcess();
        }else{
            return functionDeclarationProcess();
        }
    }

    boolean checkIfNOF(String checkThis){

        for (int i = 0; i < checkThis.length(); i++){
            if (i == 0){
                if (checkThis.charAt(i) >= '0' && checkThis.charAt(i) <= '9'){
                    return false;
                }
            }
            if (checkThis.charAt(i) <= 'A' && checkThis.charAt(i) >= 0){
                return false;
            }
            if (checkThis.charAt(i) > 'Z' && checkThis.charAt(i) < 'a'){
                return false;
            }
            if (checkThis.charAt(i) > 'z'){
                return false;
            }
        }
        return true;
    }

    boolean checkIfOpenParenthesis(String s){
        if (s.equals("(")){
            return true;
        }
        return false;
    }

    boolean checkIfCloseParenthesis(String s){
        if (s.equals(")")){
            return true;
        }
        return false;
    }

    public String functionDeclarationProcess(){

        variables.removeAll(variables);
        variables = new ArrayList<>();
        pattern.removeAll(pattern);
        pattern = new ArrayList<>();
        x = 0;
        String returnDatatype = "";
        String currentDataType = "";
        for (String str: tokenized){
            if (str.equals("")){

            }else if (x == 0){
                x = checkerFunction[0][0];
                returnDatatype = checkType(str);
                if (str.equals("void")){
                    returnDatatype = "void";
                } else if (returnDatatype == null || returnDatatype.equals("")){
                    x = 8;
                }
            } else if (x == 1){
                //name of function
                if (checkIfNOF(str)){
                    x = checkerFunction[1][1];
                }else{
                    x = 8;
                }
            } else if (x == 2){
                //check if open parenthesis
                if (checkIfOpenParenthesis(str)){
                    x = checkerFunction[2][2];
                }else{
                    x = 8;
                }
            } else if (x == 3){
                    currentDataType = checkType(str);
                    if (returnDatatype == null || returnDatatype.equals("")){
                        x = 8;
                    }else{
                        x = checkerFunction[3][3];
                    }

            } else if (x == 4){
                if (checkIfCloseParenthesis(str)){
                    x = checkerFunction[4][6];
                }else{
                    if (checkIfComma(str)){
                        x = checkerFunction[4][5];
                    }else{
                        if (checkIfVariable(str)){
                            x = checkerFunction[4][4];
                        }else{
                            x = 8;
                        }
                    }
                }
            } else if (x == 5){
                if (checkIfComma(str)){
                    x = checkerFunction[5][5];
                } else if (checkIfCloseParenthesis(str)){
                    x = checkerFunction[5][6];
                } else {
                    x = 8;
                }
            } else if (x == 6){
                if (checkIfCloseParenthesis(str)){
                    x = checkerFunction[6][6];
                }else {
                    x = 8;
                }
            } else if (x == 7){
                if (checkIfSemiColon(str)){
                    x = checkerFunction[7][7];
                } else {
                    x = 8;
                }
            } else {
                x = 8;
            }
            pattern.add(x);
        }

        if (pattern.contains(8)){
            //System.out.println("INVALID FUNCTION DECLARATION");
            return "INVALID FUNCTION DECLARATION";
        } else{
            //System.out.println("VALID FUNCTION DECLARATION");
            return "VALID FUNCTION DECLARATION";
        }
    }


    public String variableDeclarationProcess(){
        variables.removeAll(variables);
        variables = new ArrayList<>();
        pattern.removeAll(pattern);
        pattern = new ArrayList<>();
        x = 0;
        for (String s: tokenized){
            //first step: check if type
            if (x == 0){

                if (checkType(s) == null){
                    x = 7;
                }else{
                    x = checker[0][0];
                }

            } else if (x == 1){

                //check variable
                if (checkIfVariable(s)){
                    x = checker[1][1];
                }else {
                    x = 7;
                }

            } else if (x == 2){

                //check if comma, ;, operand, or =
                if (checkIfComma(s)){
                    x = checker[2][2];
                }else if (checkIfSemiColon(s)){
                    x = checker[2][3];
                    break;
                }else if (checkIfOperand(s)){
                    x = checker[2][6];
                }else if (checkIfEquals(s)){
                    x = checker[6][6];
                }else{
                    x = 7;
                }
            } else if (x == 3){

                if (!s.equals(null) && !s.equals("") && !s.equals(" ")){
                    x = 7;
                }else{
                    x = 3;
                }
            } else if (x == 4){


                if (checkIfEquals(s)){
                    x = checker[5][4];
                }else {
                    x = 7;
                }
            } else if (x == 5){


                if (dataType.equals("integer") || dataType.equals("float") || dataType.equals("double")){

                    if (ifNumber(s)){
                        x = checker[5][5];
                    } else{
                        x = 7;
                    }

                } else if (dataType.equals("character")){
                    if (ifCharacter(s)){
                        x = checker[5][5];
                    } else {
                        x = 7;
                    }

                } else if (dataType.equals("string")){
                    if (ifString(s)){
                        x = checker[5][5];
                    } else {
                        x = 7;
                    }
                } else {
                    x = 7;
                }
            } else if (x == 6){

                if (dataType.equals("integer") || dataType.equals("float") || dataType.equals("double")){
                    x = checker[6][6];
                }else{
                    x = 7;
                }

            } else if (x == 7){
                break;
            }
            pattern.add(x);
        }

        if (pattern.contains(7)){
            //System.out.println("INVALID VARIABLE DECLARATION");
            return "INVALID VARIABLE DECLARATION";
        }else{
            //System.out.println("VALID VARIABLE DECLARATION");
            return "VALID VARIABLE DECLARATION";
        }
    }
}
