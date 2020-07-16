package View_Controller;

import static Model.Inventory.addProduct;
import static Model.Inventory.getAllProducts;
import static Model.Inventory.getAllParts;

import Model.Product;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class AddProductScreenController {

        @FXML TableView populateAddProductScreenAllPartsTable;
        @FXML TableView populateAddProductAssociatedPartsTable;

        @FXML Button addProductScreenSaveButton;
        @FXML Button addProductScreenCancelButton;
        @FXML Button addProductScreenAddButton;
        @FXML Button addProductScreenDeleteButton;

        @FXML TextField addProductScreenIDTextField;
        @FXML TextField addProductScreenNameTextField;
        @FXML TextField addProductScreenInvTextField;
        @FXML TextField addProductScreenPriceTextField;
        @FXML TextField addProductScreenInvMaxTextField;
        @FXML TextField addProductScreenInvMinTextField;


        static final String ADD_PRODUCT_SCREEN_TITLE = "Add Product(s)";
        MainScreenController mainScreenController = new MainScreenController();

        public void setAddProductSaveButton(ActionEvent event){
                int productID = Integer.parseInt(addProductScreenIDTextField.getText());
                String productName = addProductScreenNameTextField.getText();
                int productInv = Integer.parseInt(addProductScreenInvTextField.getText());
                double productPrice = Double.parseDouble(addProductScreenPriceTextField.getText());
                int productInvMin = Integer.parseInt(addProductScreenInvMinTextField.getText());
                int productInvMax = Integer.parseInt(addProductScreenInvMaxTextField.getText());

                System.out.println("product before: " + getAllProducts());
                Product product = new Product(productID, productName, productPrice, productInv, productInvMin, productInvMax);
                addProduct(product);
                System.out.println("product after: " + getAllProducts());
                mainScreenController.windowManager(event, "MainScreen.fxml", MainScreenController.MAIN_SCREEN_TITLE);
        }

        public void setAddProductCancelButton(ActionEvent event) {
                System.out.println("Add product cancel button clicked. Going back to main.");
                mainScreenController.windowManager(event, "MainScreen.fxml", MainScreenController.MAIN_SCREEN_TITLE);
        }

        public void setAddProductAddButton(){

        }

        public void setAddProductScreenDeleteButton(){

        }

        public void setPopulateAddProductScreenAllPartsTable(){

        }

        public void setPopulateAddProductAssociatedPartsTable(){

        }

}
