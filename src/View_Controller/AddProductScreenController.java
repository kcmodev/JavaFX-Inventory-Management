package View_Controller;

import static Model.Inventory.addProduct;
import static Model.Inventory.getAllParts;
import static Model.Product.addAssociatedPart;
import static Model.Product.getAllAssociatedParts;
import static Model.Product.deleteAssociatedPart;

import Model.Part;
import Model.Product;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class AddProductScreenController implements Initializable {

        static final String ADD_PRODUCT_SCREEN_TITLE = "Add Product(s)";
        MainScreenController mainScreenController = new MainScreenController();

        /**
         * defines structure for add product screen product table
         */
        @FXML private TableView<Part> partTableView;
        @FXML private TableColumn<Part, Integer> partIDTableCol;
        @FXML private TableColumn<Part, SimpleStringProperty> partNameTableCol;
        @FXML private TableColumn<Part, Integer> partInvLvlTableCol;
        @FXML private TableColumn<Part, Double> partPriceTableCol;

        /**
         * defines structure for associated parts table
         */
        @FXML private TableView<Part> assocPartTableView;
        @FXML private TableColumn<Part, Integer> assocPartIDTableCol;
        @FXML private TableColumn<Part, SimpleStringProperty> assocPartNameTableCol;
        @FXML private TableColumn<Part, Integer> assocPartInvLvlTableCol;
        @FXML private TableColumn<Part, Double> assocPartPriceTableCol;

        /**
         * initialize all buttons
         */
        @FXML Button saveButton;
        @FXML Button cancelButton;
        @FXML Button addButton;
        @FXML Button deleteButton;
        @FXML Button searchButton;

        /**
         * initialize all text fields
         */
        @FXML TextField productIDTextField;
        @FXML TextField productNameTextField;
        @FXML TextField productInvTextField;
        @FXML TextField productPriceTextField;
        @FXML TextField productInvMaxTextField;
        @FXML TextField productInvMinTextField;


        public void setAddProductSaveButton(ActionEvent event){
                int productID = Integer.parseInt(productIDTextField.getText());
                String productName = productNameTextField.getText();
                int productInv = Integer.parseInt(productInvTextField.getText());
                double productPrice = Double.parseDouble(productPriceTextField.getText());
                int productInvMin = Integer.parseInt(productInvMaxTextField.getText());
                int productInvMax = Integer.parseInt(productInvMinTextField.getText());

                Product product = new Product(productID, productName, productPrice, productInv, productInvMin, productInvMax);
                addProduct(product);
                System.out.println("name of product added: \"" + product.getProductName() + "\"");
                mainScreenController.windowManager(event, "MainScreen.fxml", MainScreenController.MAIN_SCREEN_TITLE);
        }

        public void setAddProductCancelButton(ActionEvent event) {
                System.out.println("Add product cancel button clicked. Going back to main.");
                mainScreenController.windowManager(event, "MainScreen.fxml", MainScreenController.MAIN_SCREEN_TITLE);
        }

        /**
         * add selected part from parts list to associated parts list
         * populate associated parts list as items are added
         */
        public void setAddButton(){
                System.out.println("Add part to associated parts button clicked");
                Part selection = partTableView.getSelectionModel().getSelectedItem();
                Product.addAssociatedPart(selection);
                assocPartTableView.setItems(Product.getAllAssociatedParts());
        }

        /**
         * remove selected part from associated parts list
         */
        public void setDeleteButton(){
                System.out.println("Delete part from associated parts button clicked");
        }

        /**
         * set properties for parts table
         */
        public void setPartsTableProperties(){
                /**
                 * Set values for part id column
                 * set styling to center text for the column
                 * disable resizability by user
                 */
                partIDTableCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
                partIDTableCol.setStyle("-fx-alignment: CENTER;");
                partIDTableCol.setResizable(false);

                /**
                 * Set values for part name column
                 * set styling to center text for the column
                 * disable resizability by user
                 */
                partNameTableCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
                partNameTableCol.setStyle("-fx-alignment: CENTER;");
                partNameTableCol.setResizable(false);

                /**
                 * Set values for part inventory level column
                 * set styling to center text for the column
                 * disable resizability by user
                 */
                partInvLvlTableCol.setCellValueFactory(new PropertyValueFactory<>("partStock"));
                partInvLvlTableCol.setStyle("-fx-alignment: CENTER;");
                partInvLvlTableCol.setResizable(false);

                /**
                 * Set values for part price column
                 * set styling to center text for the column
                 * disable resizability by user
                 */
                partPriceTableCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
                partPriceTableCol.setStyle("-fx-alignment: CENTER;");
                partPriceTableCol.setResizable(false);
        }

        /**
         * set parts for associated parts table
         */
        public void setAssocPartsProperties(){
                /**
                 * Set values for part id column
                 * set styling to center text for the column
                 * disable resizability by user
                 */
                assocPartIDTableCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
                assocPartIDTableCol.setStyle("-fx-alignment: CENTER;");
                assocPartIDTableCol.setResizable(false);

                /**
                 * Set values for part name column
                 * set styling to center text for the column
                 * disable resizability by user
                 */
                assocPartNameTableCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
                assocPartNameTableCol.setStyle("-fx-alignment: CENTER;");
                assocPartNameTableCol.setResizable(false);

                /**
                 * Set values for part inventory level column
                 * set styling to center text for the column
                 * disable resizability by user
                 */
                assocPartInvLvlTableCol.setCellValueFactory(new PropertyValueFactory<>("partStock"));
                assocPartInvLvlTableCol.setStyle("-fx-alignment: CENTER;");
                assocPartInvLvlTableCol.setResizable(false);

                /**
                 * Set values for part price column
                 * set styling to center text for the column
                 * disable resizability by user
                 */
                assocPartPriceTableCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
                assocPartPriceTableCol.setStyle("-fx-alignment: CENTER;");
                assocPartPriceTableCol.setResizable(false);
        }

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                setPartsTableProperties();
                setAssocPartsProperties();
                partTableView.setItems(getAllParts());
                assocPartTableView.setItems(getAllAssociatedParts());
        }

}
