package application.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("CalcScene.fxml"));
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(root, 381, 429));
        primaryStage.setMaxHeight(460);
        primaryStage.setMinHeight(460);
        primaryStage.setMinWidth(400);
        primaryStage.setMaxWidth(400);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
