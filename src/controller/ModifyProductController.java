/**
 * @Author kcmodev
 * Class: C482 Software 1
 * Email: *****@wgu.edu
 * Date Submitted: 7/21/2020
 */

package controller;

import error_handling.PopupHandler;
import model.Inventory;
import model.Part;
import model.Product;

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

import static model.Inventory.*;

public class ModifyProductController implements Initializable {

    public static final String MOD_PRODUCT_SCREEN_TITLE = "Modify Product(s)";
    private static MainScreenController mainScreenController = new MainScreenController();
    private static String userInput;

    // eligible parts used to filter search results
    private ObservableList<Part> eligibleParts = FXCollections.observableArrayList();

    // extra list to keep track of changes dynamically in the event the user presses the cancel button
    private ObservableList<Part> tempList = FXCollections.observableArrayList();

    // defines structure for add product screen product table
    @FXML
    private TableView<Part> partTableView;
    @FXML
    private TableColumn<Part, Integer> partIDTableCol;
    @FXML
    private TableColumn<Part, SimpleStringProperty> partNameTableCol;
    @FXML
    private TableColumn<Part, Integer> partInvLvlTableCol;
    @FXML
    private TableColumn<Part, Double> partPriceTableCol;

    // defines structure for associated parts table
    @FXML
    private TableView<Part> assocPartTableView;
    @FXML
    private TableColumn<Part, Integer> assocPartIDTableCol;
    @FXML
    private TableColumn<Part, SimpleStringProperty> assocPartNameTableCol;
    @FXML
    private TableColumn<Part, Integer> assocPartInvLvlTableCol;
    @FXML
    private TableColumn<Part, Double> assocPartPriceTableCol;

    // defines text fields
    @FXML
    private TextField modProductIDTextField;
    @FXML
    private TextField modProductNameTextField;
    @FXML
    private TextField modProductInventoryTextField;
    @FXML
    private TextField modProductPriceTextField;
    @FXML
    private TextField modProductInvMaxTextField;
    @FXML
    private TextField modProductInvMinTextField;
    @FXML
    private TextField searchField;

    /**
     * method takes a product object from MainScreenController and uses it to fill the text fields
     */
    public void setTextFields(Product product){
        eligibleParts.setAll(Inventory.getAllParts());
        tempList.setAll(product.getAllAssociatedParts());

        // loop through all parts to find matches in associated parts
        // remove parts from eligibleParts to be filtered out from initial table view population
        // and subsequent searches
        for (Part p : tempList){
            eligibleParts.remove(p);
        }

        // populate text field with imported product
        modProductIDTextField.setText(Integer.toString(product.getProductID()));
        modProductNameTextField.setText(product.getProductName());
        modProductInventoryTextField.setText(Integer.toString((product.getProductInvLevel())));
        modProductPriceTextField.setText(Double.toString(product.getProductPrice()));
        modProductInvMaxTextField.setText(Integer.toString(product.getProductInvMax()));
        modProductInvMinTextField.setText(Integer.toString(product.getProductInvMin()));

        // populate associated parts table with imported product
        assocPartTableView.setItems(product.getAllAssociatedParts());
        partTableView.setItems(eligibleParts);

    }

    /**
     * method handles save button clicks and returns user to main screen
     * @param event
     */
    public void setModProductSave(ActionEvent event) {

        try {
            // set attributes for modified product
            Product modifiedProduct = new Product();

            modifiedProduct.setProductID(Integer.parseInt(modProductIDTextField.getText()));
            modifiedProduct.setProductName(modProductNameTextField.getText());
            modifiedProduct.setProductInvLevel(Integer.parseInt(modProductInventoryTextField.getText()));
            modifiedProduct.setProductPrice(Double.parseDouble(modProductPriceTextField.getText()));
            modifiedProduct.setProductInvMax(Integer.parseInt(modProductInvMaxTextField.getText()));
            modifiedProduct.setProductInvMin(Integer.parseInt(modProductInvMinTextField.getText()));

            // cycle through temp list and add the confirmed changes to the associated parts list
            for (Part p : tempList) {
                modifiedProduct.addAssociatedPart(p);
            }

            // update unmodified product with newly modified product then exit
            modifiedProduct.productValidation();
            Inventory.modifyProduct(modifiedProduct);
            mainScreenController.windowManager(event, "/gui/MainScreen.fxml", mainScreenController.MAIN_SCREEN_TITLE);

        } catch (ValidationException e){
            PopupHandler.errorAlert(2, e.getMessage());
        } catch (NumberFormatException e){
            PopupHandler.errorAlert(2);
        }
    }

    /**
     * method adds parts to the associated parts table and list
     * also removes said part from the list of eligible parts
     */
    public void setModProductAdd() {
        try {
            Part selection = partTableView.getSelectionModel().getSelectedItem();

            if (selection != null) {
                tempList.add(selection);
                assocPartTableView.setItems(tempList);

                eligibleParts.remove(selection); // remove assoc part from eligible parts
                partTableView.setItems(eligibleParts); // set the new table view with only eligible parts

                // clear selections so the user doesn't accidentally delete multiples
                assocPartTableView.getSelectionModel().clearSelection();
                partTableView.getSelectionModel().clearSelection();
            } else {
                PopupHandler.errorAlert(1);
            }

        } catch (NullPointerException e){
            PopupHandler.errorAlert(1);
        }
    }

    /**
     * method removes parts from the associated parts table and list
     * also adds said parts back to the eligible parts list
     */
    public void setModProductDelete() {
        try {
            Part selection = assocPartTableView.getSelectionModel().getSelectedItem(); // select from associated parts list

            if (PopupHandler.confirmationAlert("remove " + selection.getPartName() + " from associated parts")) {

                tempList.remove(selection);
                assocPartTableView.setItems(tempList);
                eligibleParts.add(selection); // add removed part back to the filtered list
                partTableView.setItems(eligibleParts); // reset display for parts table with filtered list

                // clear selections so the user doesn't accidentally delete multiples
                assocPartTableView.getSelectionModel().clearSelection();
                partTableView.getSelectionModel().clearSelection();
            }
        } catch (NullPointerException e){
            // throws exception if nothing is selected
            PopupHandler.errorAlert(1);
        }
    }

    /**
     * method handles parts search button click
     * tries to parse to int
     * if successful it will search by part ID
     * if an error is thrown then the catch will run and search by part name
     */
    public void setModProductPartSearch() {
        userInput = searchField.getText();

        // checks that input is alphanumeric using regex
        if (userInput.matches("^[a-zA-Z0-9]*$")) {
            try {
                System.out.println("attempting to search by part ID, checking input");
                int userInputAsInt = Integer.parseInt(userInput); // testing to see if it will throw an error
                partTableView.setItems(searchByPartID(userInputAsInt));

            } catch (NumberFormatException e) {
                System.out.println("Not an int, searching by part name instead of ID");

                // runs when error thrown attempting to parse input aas an int searches via name with string as input instead
                partTableView.setItems(searchByPartName(userInput));
            }
        } else {
            PopupHandler.errorAlert(2, "Alphanumeric entries only");
        }
    }

    /**
     * handles cancel button, returns user to main screen after confirmation
     */
    public void setModProductCancel(ActionEvent event) {
        if (PopupHandler.confirmationAlert("cancel all changes and return to the main screen")){
            mainScreenController.windowManager(event, "/gui/MainScreen.fxml", MainScreenController.MAIN_SCREEN_TITLE);
        }
    }

    public void setPartsTableProperties(){
//        /**
//         * Set values for part id column
//         * set styling to center text for the column
//         * disable resizability by user
//         */
        partIDTableCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
//        partIDTableCol.setResizable(false);
//
//        /**
//         * Set values for part name column
//         * set styling to center text for the column
//         * disable resizability by user
//         */
        partNameTableCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
//        partNameTableCol.setResizable(false);
//
//        /**
//         * Set values for part inventory level column
//         * set styling to center text for the column
//         * disable resizability by user
//         */
        partInvLvlTableCol.setCellValueFactory(new PropertyValueFactory<>("partStock"));
//        partInvLvlTableCol.setResizable(false);
//
//        /**
//         * Set values for part price column
//         * set styling to center text for the column
//         * disable resizability by user
//         */
        partPriceTableCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
//        partPriceTableCol.setResizable(false);
    }

    /**
     * set parts for associated parts table
     */
    public void setAssocPartsProperties(){
//        /**
//         * Set values for part id column
//         * set styling to center text for the column
//         * disable resizability by user
//         */
        assocPartIDTableCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
//        assocPartIDTableCol.setStyle("-fx-alignment: CENTER;");
//        assocPartIDTableCol.setResizable(false);
//
//        /**
//         * Set values for part name column
//         * set styling to center text for the column
//         * disable resizability by user
//         */
        assocPartNameTableCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
//        assocPartNameTableCol.setStyle("-fx-alignment: CENTER;");
//        assocPartNameTableCol.setResizable(false);
//
//        /**
//         * Set values for part inventory level column
//         * set styling to center text for the column
//         * disable resizability by user
//         */
        assocPartInvLvlTableCol.setCellValueFactory(new PropertyValueFactory<>("partStock"));
//        assocPartInvLvlTableCol.setStyle("-fx-alignment: CENTER;");
//        assocPartInvLvlTableCol.setResizable(false);
//
//        /**
//         * Set values for part price column
//         * set styling to center text for the column
//         * disable resizability by user
//         */
        assocPartPriceTableCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
//        assocPartPriceTableCol.setStyle("-fx-alignment: CENTER;");
//        assocPartPriceTableCol.setResizable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPartsTableProperties();
        setAssocPartsProperties();
    }


}
