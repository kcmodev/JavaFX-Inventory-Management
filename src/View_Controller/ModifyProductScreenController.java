package View_Controller;

import Model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ModifyProductScreenController {

    static final String MOD_PRODUCT_SCREEN_TITLE = "Modify Product(s)";
    MainScreenController mainScreenController = new MainScreenController();
    private Product modifiedProduct;

    /**
     * instantiate all necessary text fields to receive user input
     */
    @FXML private TextField modProductIDTextField;
    @FXML private TextField modProductNameTextField;
    @FXML private TextField modProductInventoryTextField;
    @FXML private TextField modProductPriceTextField;
    @FXML private TextField modProductInvMaxTextField;
    @FXML private TextField modProductInvMinTextField;

    public void setTextFields(Product product){
        modifiedProduct = product;

        modProductIDTextField.setText(Integer.toString(product.getProductID()));
        modProductNameTextField.setText(product.getProductName());
        modProductInventoryTextField.setText(Integer.toString((product.getProductInvLevel())));
        modProductPriceTextField.setText(Double.toString(product.getProductPrice()));
        modProductInvMaxTextField.setText(Integer.toString(product.getProductInvMax()));
        modProductInvMinTextField.setText(Integer.toString(product.getProductInvMin()));
    }
    public void setModProductSave(ActionEvent event) {
    }

    public void setModProductCancel() {
    }

    public void setModProductAdd() {
    }

    public void setModProductDelete() {
    }

    public void setModProductPartSearch() {
    }

    public void setModProductCancel(ActionEvent event) {
        System.out.println("Modify product cancel button clicked. Going back to main");
        mainScreenController.windowManager(event, "MainScreen.fxml", MainScreenController.MAIN_SCREEN_TITLE);
    }
}
