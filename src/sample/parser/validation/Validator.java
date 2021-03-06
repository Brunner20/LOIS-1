package sample.parser.validation;

public class Validator {

    private final static String SYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ01~\\/()!->";


    public static boolean isSymbolsCorrect(String expression){
        boolean isCorrect = true;
            for(Character character:expression.toCharArray()){
                if (!SYMBOLS.contains(Character.toString(character))) {
                    isCorrect = false;
                    break;
                }
            }
        return isCorrect;
    }

    public static boolean isBracketsCountCorrect(String expression){
        int openedBracket = 0;
        int closedBracket = 0;
        for(char symbol:expression.toCharArray()){
            if(symbol == '(') {
                openedBracket++;
                continue;
            }
            if(symbol == ')'){
                closedBracket++;
            }
        }
        return openedBracket==closedBracket;
    }
}
