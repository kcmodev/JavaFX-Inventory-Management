package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;

import static Model.Inventory.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class MainScreenController implements Initializable {

    static final String MAIN_SCREEN_TITLE = "Christensen Software 1 Performance Assessment";

    private String userInput; // used to take input in search fields
    private int userInputAsInt; // used to convert userInput to check for errors and pass correct data

    /**
     * defines structure for main screen part table
     */
    @FXML private TableView<Part> mainPartTableView;
    @FXML private TableColumn<Part, Integer> mainPartIDTableColumn;
    @FXML private TableColumn<Part, SimpleStringProperty> mainPartNameTableColumn;
    @FXML private TableColumn<Part, Integer> mainPartInventoryTableColumn;
    @FXML private TableColumn<Part, Double> mainPartPriceTableColumn;

    /**
     * defines structure for main screen product table
     */
    @FXML private TableView<Product> mainScreenProductTableView;
    @FXML private TableColumn<Product, Integer> mainScreenProductIDTableColumn;
    @FXML private TableColumn<Product, SimpleStringProperty> mainScreenProductNameTableColumn;
    @FXML private TableColumn<Product, Integer> mainScreenProductInvLvlTableColumn;
    @FXML private TableColumn<Product, Double> mainScreenProductPriceTableColumn;

    /**
     * defines search fields for main screen
     */
    @FXML private TextField mainScreenSearchByPart;
    @FXML private TextField mainScreenSearchByProduct;

    /**
     * creates a method for managing changing windows
     * called by any class and passes input as parameters to decide which window to direct the user to
     * uses static variables for the title info of each window
     */
    public void windowManager(ActionEvent event, String fileName, String windowTitle) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(fileName));
            Scene scene = new Scene(parent);
            Stage newWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();

            newWindow.setScene(scene);
            newWindow.setResizable(false);
            newWindow.setTitle(windowTitle);
            newWindow.show();
        } catch (IOException e){
            System.out.println("Failed to load new window. Goodbye.");
            System.exit(-1);
        }
    }
    public void setExitButtonClicked() {
        System.out.println("Exit button clicked");

        /**
         * instantiate alert popup for exiting the program
         */
        Alert exiting = new Alert(Alert.AlertType.CONFIRMATION);
        exiting.setTitle("Close program");
        exiting.setContentText("Are you sure you would like to close the program?");

        /**
         * set method for user to choose to quit by waiting on button press
         */
        Optional<ButtonType> choice = exiting.showAndWait();


        if (choice.get() == ButtonType.OK) { // user clicks OK
            System.out.println("Closed through alert popup");
            System.exit(1);

        } else if (choice.get() == ButtonType.CANCEL){ // user clicks CANCEL
            System.out.println("Cancel selected, returning to main");
            exiting.close();
        }
    }

    public void setAddPartClicked(ActionEvent event) {
        System.out.println("Add part button clicked");
        windowManager(event, "AddPartScreen.fxml", AddPartScreenController.ADD_PART_SCREEN_TITLE);
    }


    public void setModifyPartClicked(ActionEvent event) {
        System.out.println("Modify part button clicked");
        windowManager(event, "ModifyPartScreen.fxml", ModifyPartScreenController.MOD_PART_SCREEN_TITLE);
    }

    public void setDeletePartClicked() {
        System.out.println("Delete part button clicked");

        Part selectedPart = mainPartTableView.getSelectionModel().getSelectedItem();
        deletePart(selectedPart);
    }

    public void setSearchByPartButton() {
        System.out.println("Search by part button clicked");

        userInput = mainScreenSearchByPart.getText();

        /**
         * tries to parse to int
         * if successful it will search by part ID
         * if an error is thrown then the catch will run and search by part name
         */
        try {
            System.out.println("attempting to search by part ID, checking input");
            userInputAsInt = Integer.parseInt(userInput); // testing to see if it will throw an error
            mainPartTableView.getSelectionModel().select(searchByPartID(userInputAsInt));

        } catch (NumberFormatException e) {
            System.out.println("Not an int, searching by part name instead of ID");

            /**
             * error thrown when attempting to parse input aas an int
             * searching via name with string as input instead
             */
            mainPartTableView.getSelectionModel().select(searchByPartName(userInput));

        }
    }

    public void setAddProductButtonClicked(ActionEvent event) {
        System.out.println("Add product button clicked");
        windowManager(event, "AddProductScreen.fxml", AddProductScreenController.ADD_PRODUCT_SCREEN_TITLE);
    }

    public void setModifyProductButton(ActionEvent event) {
        System.out.println("Modify product button clicked");
        windowManager(event, "ModifyProductScreen.fxml", ModifyProductScreenController.MOD_PRODUCT_SCREEN_TITLE);
    }

    public void setDeleteProductButton() {
        System.out.println("Delete product button clicked");

        Product selectedProduct = mainScreenProductTableView.getSelectionModel().getSelectedItem();
        Inventory.deleteProduct(selectedProduct);
    }


    public void setSearchByProductButton() {
        System.out.println("Search by product button clicked");

        userInput = mainScreenSearchByProduct.getText();

        /**
         * tries to parse to int
         * if successful it will search by part ID
         * if an error is thrown then the catch will run and search by part name
         */
        try {
            System.out.println("attempting to search by part ID, checking input");
            userInputAsInt = Integer.parseInt(userInput); // testing to see if it will throw an error
            mainScreenProductTableView.getSelectionModel().select(searchByProductID(userInputAsInt));


        } catch (NumberFormatException e) {
            System.out.println("Not an int, searching by part name instead of ID");

            /**
             * error thrown when attempting to parse input aas an int
             * searching via name with string as input instead
             */
                mainScreenProductTableView.getSelectionModel().select(searchByProductName(userInput));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /**
         * Set values for part id column
         * set styling to center text for the column
         * disable resizability by user
         */
        mainPartIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        mainPartIDTableColumn.setStyle("-fx-alignment: CENTER;");
        mainPartIDTableColumn.setResizable(false);

        /**
         * Set values for part name column
         * set styling to center text for the column
         * disable resizability by user
         */
        mainPartNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        mainPartNameTableColumn.setStyle("-fx-alignment: CENTER;");
        mainPartNameTableColumn.setResizable(false);

        /**
         * Set values for part inventory level column
         * set styling to center text for the column
         * disable resizability by user
         */
        mainPartInventoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        mainPartInventoryTableColumn.setStyle("-fx-alignment: CENTER;");
        mainPartInventoryTableColumn.setResizable(false);

        /**
         * Set values for part price column
         * set styling to center text for the column
         * disable resizability by user
         */
        mainPartPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        mainPartPriceTableColumn.setStyle("-fx-alignment: CENTER;");
        mainPartPriceTableColumn.setResizable(false);

        /**
         * Set values for product ID column
         * set styling to center text for the column
         * disable resizability by user
         */
        mainScreenProductIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        mainScreenProductIDTableColumn.setStyle("-fx-alignment: CENTER;");
        mainScreenProductIDTableColumn.setResizable(false);

        /**
         * Set values for product name column
         * set styling to center text for the column
         * disable resizability by user
         */
        mainScreenProductNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        mainScreenProductNameTableColumn.setStyle("-fx-alignment: CENTER;");
        mainScreenProductNameTableColumn.setResizable(false);

        /**
         * Set values for product inventory level column
         * set styling to center text for the column
         * disable resizability by user
         */
        mainScreenProductInvLvlTableColumn.setCellValueFactory(new PropertyValueFactory<>("productInvLevel"));
        mainScreenProductInvLvlTableColumn.setStyle("-fx-alignment: CENTER;");
        mainScreenProductInvLvlTableColumn.setResizable(false);

        /**
         * Set values for product price column
         * set styling to center text for the column
         * disable resizability by user
         */
        mainScreenProductPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        mainScreenProductPriceTableColumn.setStyle("-fx-alignment: CENTER;");
        mainScreenProductPriceTableColumn.setResizable(false);

        /**
         * populates main screen part and product tables
         */
        populateMainScreenProductTable();
        populateMainScreenPartTable();
    }

    public void populateMainScreenPartTable(){
        mainPartTableView.setItems(getAllParts());
    }

    public void populateMainScreenProductTable() { mainScreenProductTableView.setItems(getAllProducts()); }
}
