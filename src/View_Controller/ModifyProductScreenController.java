package View_Controller;

import Model.ErrorHandling;
import Model.Inventory;
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

import javax.xml.bind.ValidationException;
import java.net.URL;
import java.util.ResourceBundle;

import static Model.Inventory.*;

public class ModifyProductScreenController implements Initializable {

    static final String MOD_PRODUCT_SCREEN_TITLE = "Modify Product(s)";
    MainScreenController mainScreenController = new MainScreenController();

    private Product modifiedProduct;
    private String userInput;
    private ObservableList<Part> filteredSearchList = FXCollections.observableArrayList();
    private ObservableList<Part> eligibleParts = FXCollections.observableArrayList();

    /**
     * extra list to keep track of changes dynamically in the event the user presses the cancel button
     */
    private ObservableList<Part> tempList = FXCollections.observableArrayList();

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

    @FXML private TextField modProductIDTextField;
    @FXML private TextField modProductNameTextField;
    @FXML private TextField modProductInventoryTextField;
    @FXML private TextField modProductPriceTextField;
    @FXML private TextField modProductInvMaxTextField;
    @FXML private TextField modProductInvMinTextField;
    @FXML private TextField searchField;

    /**
     * method takes a product object from MainScreenController and uses it to fill the text fields
     */
    public void setTextFields(Product product){
        modifiedProduct = product;
        filteredSearchList.clear(); // resets filtered list every time modify product is called
        eligibleParts.setAll(Inventory.getAllParts());

        /**
         * loop through all parts to find matches in associated parts
         * remove parts from eligibleParts to be filtered out from initial table view population
         * and subsequent searches
         */
        for (Part p : modifiedProduct.getAllAssociatedParts()){
            filteredSearchList.add(p);
            eligibleParts.remove(p);
        }

        /**
         * populate text field with imported product
         */
        modProductIDTextField.setText(Integer.toString(modifiedProduct.getProductID()));
        modProductNameTextField.setText(modifiedProduct.getProductName());
        modProductInventoryTextField.setText(Integer.toString((modifiedProduct.getProductInvLevel())));
        modProductPriceTextField.setText(Double.toString(modifiedProduct.getProductPrice()));
        modProductInvMaxTextField.setText(Integer.toString(modifiedProduct.getProductInvMax()));
        modProductInvMinTextField.setText(Integer.toString(modifiedProduct.getProductInvMin()));

        /**
         * populate associated parts table with imported product
         */
        assocPartTableView.setItems(modifiedProduct.getAllAssociatedParts());
        partTableView.setItems(eligibleParts);

    }

    public void setModProductSave(ActionEvent event) {

        try {
            /**
             * set attributes for modified product
             */
            modifiedProduct = new Product();

            modifiedProduct.setProductID(Integer.parseInt(modProductIDTextField.getText()));
            modifiedProduct.setProductName(modProductNameTextField.getText());
            modifiedProduct.setProductInvLevel(Integer.parseInt(modProductInventoryTextField.getText()));
            modifiedProduct.setProductPrice(Double.parseDouble(modProductPriceTextField.getText()));
            modifiedProduct.setProductInvMax(Integer.parseInt(modProductInvMaxTextField.getText()));
            modifiedProduct.setProductInvMin(Integer.parseInt(modProductInvMinTextField.getText()));

            /**
             * cycle through temp list and add the confirmed changes to the associated parts list
             */
            for (Part p : tempList) {
                modifiedProduct.addAssociatedPart(p);
            }

            /**
             * update unmodified product with newly modified product
             */
            modifiedProduct.productValidation();
            Inventory.modifyProduct(modifiedProduct);

            mainScreenController.windowManager(event, "MainScreen.fxml", mainScreenController.MAIN_SCREEN_TITLE);
        } catch (ValidationException e){
            ErrorHandling.errorAlert(2, e.getMessage());
        } catch (NumberFormatException e){
            ErrorHandling.errorAlert(2);
        }
    }

    /**
     * method adds parts to the associated parts table and list
     * also removes said part from the list of eligible parts
     */
    public void setModProductAdd() {
        try {
            Part selection = partTableView.getSelectionModel().getSelectedItem();

            tempList.add(selection);
            assocPartTableView.setItems(tempList);

            eligibleParts.remove(selection); // remove assoc part from eligible parts
            partTableView.setItems(eligibleParts); // set the new table view with only eligible parts

            /**
             * clear selections so the user doesn't accidentally delete multiples
             */
            assocPartTableView.getSelectionModel().clearSelection();
            partTableView.getSelectionModel().clearSelection();
        } catch (NullPointerException e){
            ErrorHandling.errorAlert(1);
        }
    }

    /**
     * method removes parts from the associated parts table and list
     * also adds said parts back to the eligible parts list
     */
    public void setModProductDelete() {
        try {
            Part selection = assocPartTableView.getSelectionModel().getSelectedItem(); // select from associated parts list

            if (ErrorHandling.confirmationAlert("remove " + selection.getPartName() + " from associated parts")) {

                tempList.remove(selection);
                assocPartTableView.setItems(tempList);
                eligibleParts.add(selection); // add removed part back to the filtered list
                partTableView.setItems(eligibleParts); // reset display for parts table with filtered list

                /**
                 * clear selections so the user doesn't accidentally delete multiples
                 */
                assocPartTableView.getSelectionModel().clearSelection();
                partTableView.getSelectionModel().clearSelection();
            }
        } catch (NullPointerException e){
            /**
             * throws exception if nothing is selected
             * will alert user with popup
             */
            ErrorHandling.errorAlert(1);
        }
    }

    /**
     * handles parts search button
     */
    public void setModProductPartSearch() {
        userInput = searchField.getText();

        /**
         * tries to parse to int
         * if successful it will search by part ID
         * if an error is thrown then the catch will run and search by part name
         */
        try {
            System.out.println("attempting to search by part ID, checking input");
            int userInputAsInt = Integer.parseInt(userInput); // testing to see if it will throw an error
            partTableView.setItems(searchByPartID(userInputAsInt));

        } catch (NumberFormatException e) {
            System.out.println("Not an int, searching by part name instead of ID");

            /**
             * error thrown when attempting to parse input aas an int
             * searching via name with string as input instead
             */
            partTableView.setItems(searchByPartName(userInput));
        }
    }

    /**
     * handles cancel button
     */
    public void setModProductCancel(ActionEvent event) {
        if (ErrorHandling.confirmationAlert("cancel all changes and return to the main screen")){
            mainScreenController.windowManager(event, "MainScreen.fxml", MainScreenController.MAIN_SCREEN_TITLE);
        }
    }

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
        tempList.setAll(modifiedProduct.getAllAssociatedParts());
    }


}
