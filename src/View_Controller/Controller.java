package View_Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller {

    @FXML private Button exitButton;

    // set all main menu parts buttons
    @FXML private Button addPartButton;
    @FXML private Button modifyPartButton;
    @FXML private Button deletePartButton;
    @FXML private Button searchByPartButton;

    //set all main menu products buttons
    @FXML private Button addProductButton;
    @FXML private Button modifyProductButton;
    @FXML private Button deleteProductButton;
    @FXML private Button searchByProductButton;

    public void setExitButtonClicked() {
        exitButton.setOnAction(event -> {
            System.out.println("Exit button clicked");
            System.exit(69);
        });
    }

    public void setAddPartClicked(ActionEvent event) {
        System.out.println("Add part button clicked");
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("AddPartScreen.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setTitle("New Window");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    System.out.println("Failed to load new window. Goodbye.");
                    System.exit(5355);
                }
    }


    public void setModifyPartClicked(ActionEvent event) {
        System.out.println("Modify part button clicked");
    }

    public void setDeletePartClicked(ActionEvent event) {
        System.out.println("Delete part button clicked");
    }

    public void setSearchByPartButton(ActionEvent event) {
        System.out.println("Search by part button clicked");
    }

    public void setAddProductButton(ActionEvent event) {
        System.out.println("Add product button clicked");
    }

    public void setModifyProductButton(ActionEvent event) {
        System.out.println("Modify product button clicked");
    }

    public void setDeleteProductButton(ActionEvent event) {
        System.out.println("Delete product button clicked");
    }

    public void setSearchByProductButton() {
        System.out.println("Search by product button clicked");
    }
}
