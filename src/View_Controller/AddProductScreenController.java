package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AddProductScreenController {


        static final String ADD_PRODUCT_SCREEN_TITLE = "Add Product(s)";
        MainScreenController mainScreenController = new MainScreenController();

        public void setAddProductCancelButton(ActionEvent event) {
                System.out.println("Add product cancel button clicked. Going back to main.");
                mainScreenController.windowManager(event, "MainScreen.fxml", MainScreenController.MAIN_SCREEN_TITLE);
        }

}
