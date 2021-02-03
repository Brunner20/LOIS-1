package sample.parser.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private final static Pattern SYMBOLS = Pattern.compile("([A-Z01≡∧∨()¬]|→)+");


    public static boolean isSymbolsCorrect(String expression){
        Matcher matcher = SYMBOLS.matcher(expression);
        return matcher.find();
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
