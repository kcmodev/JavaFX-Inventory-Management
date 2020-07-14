package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ModifyProductScreenController {

    static final String MOD_PRODUCT_SCREEN_TITLE = "Modify Product(s)";
    MainScreenController mainScreenController = new MainScreenController();

    public void setModProductCancelButton(ActionEvent event) {
        System.out.println("Modify product cancel button clicked. Going back to main");
        mainScreenController.windowManager(event, "MainScreen.fxml", MainScreenController.MAIN_SCREEN_TITLE);
    }
}
