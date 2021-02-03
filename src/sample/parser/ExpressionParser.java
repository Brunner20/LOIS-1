package sample.parser;

import sample.parser.exception.BracketNumberException;
import sample.parser.exception.SymbolNotCorrectException;
import sample.parser.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionParser {

    private String expression;

    private static final String OPENED = "(";
    private static final String CLOSED = ")";
    private static final String AND = "∧";
    private static final String OR = "∨";
    private static final String NOT = "¬";
    private static final String IMPLICATION ="→";
    private static final String EQUIVALENCE = "≡";
    private static final Pattern CONJUNCTION = Pattern.compile("\\([A-Z]∧[A-Z]\\)");
    private static final Pattern INVERSE = Pattern.compile("\\(¬[A-Z]\\)");
    private static final Pattern SIMPLE = Pattern.compile("[RI()∨]+");


    public ExpressionParser(String expression) {
        this.expression = expression;
    }

    public boolean isDNF() throws BracketNumberException, SymbolNotCorrectException {

        if(!Validator.isBracketsCountCorrect(expression))
            throw new BracketNumberException("не совпадает количество открывающих и закрывающих скобок");
        if(!Validator.isSymbolsCorrect(expression))
            throw new SymbolNotCorrectException("некорректные символы");

        if(expression.contains("→")||expression.contains("≡"))
            return false;

        Matcher invMatcher = INVERSE.matcher(expression);
        if (invMatcher.find()){
            expression = expression.replaceAll(INVERSE.pattern(),"I");
            System.out.println(expression);
        }

        replaceConjunct();

        Matcher simpleMatcher = SIMPLE.matcher(expression);
        return simpleMatcher.find();


    }

    private void replaceConjunct(){
        Matcher conjunctionMatcher = CONJUNCTION.matcher(expression);
        while (conjunctionMatcher.find()){
            expression = expression.replaceAll(CONJUNCTION.pattern(),"R");
            System.out.println(expression);
        }
    }

}
