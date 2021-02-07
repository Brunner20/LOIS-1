package sample.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sample.parser.ExpressionParser;
import sample.parser.exception.BracketNumberException;
import sample.parser.exception.SymbolNotCorrectException;


public class OperButtonPanel {

    private static final String AND = "∧";
    private static final String OR = "∨";
    private static final String NOT = "¬";

    private static final String TRUE = "⊤";
    private static final String FALSE = "⊥";
    private static final String FIND = "определить";

    private static final String BUTTON_STYLE =
            "-fx-pref-width: 50; " +
                    "-fx-pref-height: 50; " +
                    "-fx-font-size: 15";

    private TextField expRowTextField;
    private GridPane gridPane;

    public OperButtonPanel(ExpressionRowPanel expressionRowPanel) {
        expRowTextField = expressionRowPanel.getExpressionRowTextField();
        gridPane = new GridPane();
        configGridPane();
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    private void configGridPane() {

        Button and = new Button(AND);
        Button or = new Button(OR);
        Button not  = new Button(NOT);
        Button tru = new Button(TRUE);
        Button fal = new Button(FALSE);
        Button find = new Button(FIND);


        and.setOnAction(andEventHandler);
        or.setOnAction(orEventHandler);
        not.setOnAction(notEventHandler);
        tru.setOnAction(trueEventHandler);
        fal.setOnAction(falseEventHandler);
        find.setOnAction(findEventHandler);

        and.setStyle(BUTTON_STYLE);
        or.setStyle(BUTTON_STYLE);
        not.setStyle(BUTTON_STYLE);
        tru.setStyle(BUTTON_STYLE);
        fal.setStyle(BUTTON_STYLE);
        find.setStyle( "-fx-pref-width: 150; " +
                "-fx-pref-height: 50; " +
                "-fx-font-size: 15");

        gridPane.add(and,0,0,1,1);
        gridPane.add(or,1,0,1,1);
        gridPane.add(not,2,0,1,1);
        gridPane.add(find,0,1,2,1);
        GridPane.setMargin(or, new Insets(15));
        GridPane.setMargin(tru, new Insets(15));

    }

    private EventHandler<ActionEvent> andEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + AND);
    };
    private EventHandler<ActionEvent> orEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + OR);
    };
    private EventHandler<ActionEvent> notEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + NOT);
    };
    private EventHandler<ActionEvent> trueEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + TRUE);
    };
    private EventHandler<ActionEvent> falseEventHandler = e -> {
        expRowTextField.setText(expRowTextField.getText() + FALSE);
    };
    private EventHandler<ActionEvent> findEventHandler = e -> {
        try {
            if(!new ExpressionParser(expRowTextField.getText()).isDNF()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("формула не является ДНФ");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("формула является ДНФ");
                alert.showAndWait();
            }
        } catch (BracketNumberException | SymbolNotCorrectException bracketNumberException) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(bracketNumberException.getMessage());
            alert.showAndWait();
            bracketNumberException.printStackTrace();
        }
    };
}
