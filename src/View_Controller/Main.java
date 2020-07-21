package View_Controller;

import Model.Inventory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));

        primaryStage.setTitle(MainScreenController.MAIN_SCREEN_TITLE);
        primaryStage.setScene(new Scene(root));
        Inventory initData = new Inventory(); // call inventory constructor to initialize data to table doesn't start blank
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
