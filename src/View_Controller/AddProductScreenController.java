package View_Controller;

import static Model.Inventory.*;
import static Model.Inventory.searchByPartName;

import Model.ErrorHandling;
import Model.Part;
import Model.Product;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class AddProductScreenController implements Initializable {

        static final String ADD_PRODUCT_SCREEN_TITLE = "Add Product(s)";
        private String userInput;
        private Product newProduct;
        private ObservableList<Part> filteredSearchList = FXCollections.observableArrayList();

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
         * initialize all text fields
         */
        @FXML TextField prodIDField;
        @FXML TextField prodNameField;
        @FXML TextField prodInvField;
        @FXML TextField prodPriceField;
        @FXML TextField prodInvMaxField;
        @FXML TextField prodInvMinField;
        @FXML TextField searchField;

        /**
         * save product button handler
         */
        public void setSaveButton(ActionEvent event){
                String productName = prodNameField.getText();
                int productInv = Integer.parseInt(prodInvField.getText());
                double productPrice = Double.parseDouble(prodPriceField.getText());
                int productInvMin = Integer.parseInt(prodInvMaxField.getText());
                int productInvMax = Integer.parseInt(prodInvMinField.getText());

                newProduct = new Product(idGenerator(), productName, productPrice, productInv, productInvMin, productInvMax);
                addProduct(newProduct);

                for (Part p : newProduct.getAllAssociatedParts()){
                        System.out.println(p.getPartName());
                }

                mainScreenController.windowManager(event, "MainScreen.fxml", MainScreenController.MAIN_SCREEN_TITLE);
        }

        /**
         * search button handler
         */
        public void setSearchButton(){
                userInput = searchField.getText();

                /**
                 * tries to parse to int
                 * if successful it will search by part ID
                 * if an error is thrown then the catch will run and search by part name
                 */
                try {
                        int userInputAsInt = Integer.parseInt(userInput); // testing to see if it will throw an error
                        partTableView.setItems(searchByPartID(userInputAsInt));

                } catch (NumberFormatException e) {
                        /**
                         * error thrown when attempting to parse input aas an int
                         * searching via name with string as input instead
                         */
                        partTableView.setItems(searchByPartName(userInput));
                }
        }

        /**
         * add selected part from parts list to associated parts list
         * populate associated parts list as items are added
         */
        public void setAddButton(){
                try {
                        Part selection = partTableView.getSelectionModel().getSelectedItem();

                        newProduct.addAssociatedPart(selection);
                        assocPartTableView.setItems(newProduct.getAllAssociatedParts());
                        filteredSearchList.remove(selection);

                        /**
                         * clears selections to prevent multiple simultaneous deletes
                         */
                        assocPartTableView.getSelectionModel().clearSelection();
                        partTableView.getSelectionModel().clearSelection();
                } catch (NullPointerException e) {
                        /**
                         * throws exception if nothing is selected
                         * will alert user with popup
                         */
                        ErrorHandling.errorAlert(1);
                }
        }

        /**
         * remove selected part from associated parts list
         */
        public void setDeleteButton(){

                try {
                        Part selection = assocPartTableView.getSelectionModel().getSelectedItem(); // select from associated parts list

                        newProduct.deleteAssociatedPart(selection);

                        assocPartTableView.setItems(newProduct.getAllAssociatedParts());
                        filteredSearchList.add(selection);
                        partTableView.setItems(filteredSearchList);

                        assocPartTableView.getSelectionModel().clearSelection();
                        partTableView.getSelectionModel().clearSelection();
                }catch (NullPointerException e) {
                        ErrorHandling.errorAlert(1);
                }
        }

        public void setCancelButton(ActionEvent event) {
                mainScreenController.windowManager(event, "MainScreen.fxml", MainScreenController.MAIN_SCREEN_TITLE);
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

                filteredSearchList.setAll(getAllParts());
                partTableView.setItems(filteredSearchList);
        }

}
