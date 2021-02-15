package sample.view;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class Form {

    private ExpressionRowPanel expressionRowPanel;
    private OperButtonPanel operButtonPanel;

    private GridPane gridPane;

    public Form() {

        expressionRowPanel = new ExpressionRowPanel();
        operButtonPanel = new OperButtonPanel(expressionRowPanel);
        gridPane = new GridPane();
        configGridPane();
    }

    private void configGridPane() {
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(expressionRowPanel.getExpRowScrollPane(), 1, 0,2,1);
        gridPane.add(operButtonPanel.getGridPane(), 1, 1,2,1);

    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
