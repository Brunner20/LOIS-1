// Лабораторная работа №1 по дисциплине ЛОИС
// Вариант F: Проверить, является ли формула ДНФ
// Выполнена студентом грруппы 821701 БГУИР Бруннер Антоном Юрьевичем

package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.view.Form;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        
        Form form = new Form();
        primaryStage.setScene(new Scene(form.getGridPane(), 600, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
