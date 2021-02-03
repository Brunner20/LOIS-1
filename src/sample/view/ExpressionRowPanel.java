package sample.view;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class ExpressionRowPanel {

    private TextField expressionRowTextField;

    private ScrollPane expRowScrollPane;


    public ExpressionRowPanel() {
        expressionRowTextField = new TextField();
        configureExpressionRowTextField();
        expRowScrollPane = new ScrollPane();
        configureExpRowScrollPane();
    }

    public ScrollPane getExpRowScrollPane() {
        return expRowScrollPane;
    }

    public TextField getExpressionRowTextField() {
        return expressionRowTextField;
    }

    /*
     *      Configs
     */

    private void configureExpressionRowTextField() {
        expressionRowTextField.setEditable(true);
        expressionRowTextField.setFocusTraversable(false);
        expressionRowTextField.setFont(new Font("Segoe UI", 18));
        expressionRowTextField.setPrefHeight(70);
        expressionRowTextField.setPrefWidth(1000);

        expressionRowTextField.textProperty().addListener(e -> {
            expressionRowTextField.positionCaret(expressionRowTextField.getText().length());
        });
    }

    private void configureExpRowScrollPane() {
        expRowScrollPane.setContent(expressionRowTextField);
        expRowScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        expRowScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        expRowScrollPane.setPrefWidth(100);
    }
}
