package View_Controller;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {

//    @FXML private Button exitButton;
//
//    // set all main menu parts buttons
//    @FXML private Button addPartButton;
//    @FXML private Button modifyPartButton;
//    @FXML private Button deletePartButton;
//    @FXML private Button searchByPartButton;
//
//    //set all main menu products buttons
//    @FXML private Button addProductButton;
//    @FXML private Button modifyProductButton;
//    @FXML private Button deleteProductButton;
//    @FXML private Button searchByProductButton;

        public void setExitButtonClicked(ActionEvent event) {
            System.out.println("Exit button clicked");
            System.exit(1);
    }

    public void setAddPartClicked(ActionEvent event) {
        System.out.println("Add part button clicked");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AddPartScreen.fxml"));
            Scene addScene = new Scene(fxmlLoader.load());
            Stage addStage = new Stage();
            addStage.setTitle("Add Part");
            addStage.setScene(addScene);
            addStage.show();
        } catch (IOException e) {
            System.out.println("Failed to load new window. Goodbye.");
            System.exit(-1);
        }
    }


    public void setModifyPartClicked(ActionEvent event) {
        System.out.println("Modify part button clicked");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            System.out.println("gets into try");
            fxmlLoader.setLocation(getClass().getResource("ModifyPartScreen.fxml"));
            System.out.println("loads modify parts");
            Scene modScene = fxmlLoader.load();
            System.out.println("loads scene");
            Stage modStage = new Stage();
            System.out.println("loads stage");
            System.out.println("loads scene and stage");
            modStage.setTitle("Modify Part");
            modStage.setScene(modScene);
            modStage.show();
        } catch (IOException e) {
            System.out.println("Failed to load new window. Goodbye.");
            System.exit(-1);
        }
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

    public void setSearchByProductButton(ActionEvent event) {
        System.out.println("Search by product button clicked");
    }
}
