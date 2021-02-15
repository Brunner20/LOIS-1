package sample.parser;

import sample.parser.exception.BracketNumberException;
import sample.parser.exception.SymbolNotCorrectException;
import sample.parser.validation.Validator;

public class ExpressionParser {

    private String expression;
    private boolean isNDF = true;

    private static final char OPENED = '(';
    private static final char CLOSED = ')';
    private static final char CLASH = '/'; // AND /\
    private static final char BACKSLASH = '\\'; // OR \/
    private static final char NOT = '!';




    public ExpressionParser(String expression) {
        this.expression = expression;
    }

    public boolean isDNF() throws BracketNumberException, SymbolNotCorrectException {

        if(!Validator.isBracketsCountCorrect(expression))
            isNDF=false;
            //throw new BracketNumberException("не совпадает количество открывающих и закрывающих скобок");
        if(!Validator.isSymbolsCorrect(expression))
            isNDF= false;
            //throw new SymbolNotCorrectException("некорректные символы");

        if(expression.contains("->")||expression.contains("~"))
             isNDF = false;

        if(isNDF)
        {
            replaceInversion();
            if(expression.contains("!")||!replaceConjunct())
             isNDF = false;
            else isNDF = replaceDisjunction();
        }
        return isNDF;
    }

    private void replaceInversion (){
        int openedBracket = 0;
        int closedBracket = 0;
        String subInver;
        for(int i=0;i<expression.length();i++){
            Character character = expression.charAt(i);
            if(character.compareTo(NOT)==0){
                openedBracket=i-1;
                closedBracket = expression.indexOf(CLOSED,i)+1;
                subInver=expression.substring(openedBracket,closedBracket);
                if(Validator.isBracketsCountCorrect(subInver)) {
                    expression = expression.replace(subInver, "In");
                    i = -1;
                }
            }
        }
    }

    private boolean replaceConjunct(){
        int openedBracket=0;
        int closedBracket;
        boolean isCorrectConj = true;

        for(int i=0;i<expression.length();i++){
           Character character = expression.charAt(i);

           if(character.compareTo(OPENED)==0){
               openedBracket=i;
           } else if(character.compareTo(CLASH)==0&&expression.charAt(i+1)==BACKSLASH&&expression.charAt(i+2)!=OPENED){

                closedBracket = expression.indexOf(CLOSED,i)+1;
                String simpleConjunct = expression.substring(openedBracket,closedBracket);
                if(isBinary(simpleConjunct, CLASH)) {              //проверка на A/\B/\C
                    expression = expression.replace(simpleConjunct, "Co");
                    i=-1;
                } else {
                    isCorrectConj = false;
                    break;
                }
            }
        }
        return isCorrectConj;
    }

    private boolean isBinary(String substring, char operat){
        boolean isBin = true;
        for(int i=1;i<substring.length()-1;i++){
            Character character = substring.charAt(i);
            if (character.compareTo(operat) == 0 && substring.indexOf(operat, i + 1) > -1) {
                isBin = false;
                break;
            }
        }
        return isBin;
    }



    private boolean replaceDisjunction(){

        int openedBracket=0;
        int closedBracket;
        boolean isDNF = true;

        for(int i=0;i<expression.length();i++){
            Character character = expression.charAt(i);
            if(character.compareTo(OPENED)==0){
                openedBracket=i;
            } else if(character.compareTo(BACKSLASH)==0&&expression.charAt(i+1)==CLASH&&expression.charAt(i+2)!=OPENED){
                closedBracket = expression.indexOf(CLOSED,i)+1;
                String simpleConjunct = expression.substring(openedBracket,closedBracket);
                if(isBinary(simpleConjunct, BACKSLASH)) {
                    expression = expression.replace(simpleConjunct, "Dn");
                    i=-1;
                } else {
                    isDNF = false;
                    break;
                }

            }
        }
        if(isDNF)
        return expression.contains("Dn");
        else return false;
    }



    public static void main(String[] args) {
        try {
            System.out.println( new ExpressionParser("S").isDNF());
        } catch (BracketNumberException e) {
            e.printStackTrace();
        } catch (SymbolNotCorrectException e) {
            e.printStackTrace();
        }
    }

}
