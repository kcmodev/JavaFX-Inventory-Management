package View_Controller;

import Model.Part;
import Model.Product;
import Model.ErrorHandling;

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
import java.util.ResourceBundle;


public class MainScreenController implements Initializable {

    /**
     * title to pass when switching screens
     */
    static final String MAIN_SCREEN_TITLE = "Christensen Software 1 Performance Assessment";

    private String userInput; // used to take input in search fields
    private int userInputAsInt; // used to convert userInput to check for errors and pass correct data

    /**
     * defines structure for main screen part table
     */
    @FXML private TableView<Part> partTableView;
    @FXML private TableColumn<Part, Integer> partIDTableCol;
    @FXML private TableColumn<Part, SimpleStringProperty> partNameTableCol;
    @FXML private TableColumn<Part, Integer> partInvLvlTableCol;
    @FXML private TableColumn<Part, Double> partPriceTableCol;

    /**
     * defines structure for main screen product table
     */
    @FXML private TableView<Product> productTableView;
    @FXML private TableColumn<Product, Integer> productIDTableCol;
    @FXML private TableColumn<Product, SimpleStringProperty> productNameTableCol;
    @FXML private TableColumn<Product, Integer> productInvLvlTableCol;
    @FXML private TableColumn<Product, Double> productPriceTableCol;

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
            System.out.println("Failed to load new window");
            System.exit(-1);
        }
    }

    /**
     * main screen exit button handler
     */
    public void setExitButtonClicked() {
        if (ErrorHandling.confirmationAlert("close the program")){ // continues to exit if user presses OK
            System.exit(1);
        }
    }

    /**
     * main screen add part button handler
     */
    public void setAddPartClicked(ActionEvent event) {
        windowManager(event, "AddPartScreen.fxml", AddPartScreenController.ADD_PART_SCREEN_TITLE);
    }

    /**
     * main screen add product button handler
     */
    public void setAddProductButtonClicked(ActionEvent event) {
        windowManager(event, "AddProductScreen.fxml", AddProductScreenController.ADD_PRODUCT_SCREEN_TITLE);
    }

    /**
     * main screen modify part button handler
     */
    public void setModifyPartClicked(ActionEvent event) throws IOException {
        if (partTableView.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ModifyPartScreen.fxml"));
            Parent parent = loader.load();
            Scene modPartScene = new Scene(parent);

            ModifyPartScreenController controller = loader.getController();
            controller.setTextFields(partTableView.getSelectionModel().getSelectedItem());

            Stage newWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            newWindow.setScene(modPartScene);
            newWindow.setResizable(false);
            newWindow.setTitle(ModifyPartScreenController.MOD_PART_SCREEN_TITLE);
            newWindow.show();
        } else {
            ErrorHandling.errorAlert(1);
        }
    }

    /**
     * main screen modify product button handler
     */
    public void setModifyProductButton(ActionEvent event) throws IOException {
        if (productTableView.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ModifyProductScreen.fxml"));
            Parent parent = loader.load();
            Scene modProductScene = new Scene(parent);

            ModifyProductScreenController controller = loader.getController();
            controller.setTextFields(productTableView.getSelectionModel().getSelectedItem());

            Stage newWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            newWindow.setScene(modProductScene);
            newWindow.setResizable(false);
            newWindow.setTitle(ModifyProductScreenController.MOD_PRODUCT_SCREEN_TITLE);
            newWindow.show();
        } else {
            ErrorHandling.errorAlert(1);
        }
    }

    /**
     * main screen delete part button handler
     */
    public void setDeletePartClicked() {
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();
        if (selectedPart == null){
            ErrorHandling.errorAlert(1);
        } else {
            if (ErrorHandling.confirmationAlert("delete the selected part")) {
                deletePart(selectedPart);
            }
        }
        partTableView.getSelectionModel().clearSelection();
    }

    /**
     * main screen delete product button handler
     */
    public void setDeleteProductButton() {
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
        if (selectedProduct == null){
            ErrorHandling.errorAlert(1);
        } else {
            if (ErrorHandling.confirmationAlert("delete the selected product")) {
                deleteProduct(selectedProduct);
            }
        }
        productTableView.getSelectionModel().clearSelection();
    }

    /**
     * main screen search by part button handler
     */
    public void setSearchByPartButton() {
        userInput = mainScreenSearchByPart.getText();

        /**
         * tries to parse to int
         * if successful it will search by part ID
         * if an error is thrown then the catch will run and search by part name
         */
        if (userInput.matches("^[a-zA-Z0-9]*$") && !userInput.isEmpty()) {
            try {
                userInputAsInt = Integer.parseInt(userInput); // testing to see if it will throw an error
                partTableView.setItems(searchByPartID(userInputAsInt));
            } catch (NumberFormatException e) {
                /**
                 * error thrown when attempting to parse input aas an int
                 * searching via name with string as input instead
                 */
                partTableView.setItems(searchByPartName(userInput));
            }
        } else {
            ErrorHandling.errorAlert(2, "Alphanumeric entries only");
        }
    }

    /**
     * main screen search by product button handler
     */
    public void setSearchByProductButton() {
        userInput = mainScreenSearchByProduct.getText();

        /**
         * tries to parse to int
         * if successful it will search by part ID
         * if an error is thrown then the catch will run and search by part name
         */
        if (userInput.matches("^[a-zA-Z0-9]*$") && !userInput.isEmpty()) {
            try {
                userInputAsInt = Integer.parseInt(userInput); // testing to see if it will throw an error
                productTableView.setItems(searchByProductID(userInputAsInt));
            } catch (NumberFormatException e) {
                /**
                 * error thrown when attempting to parse input aas an int
                 * searching via name with string as input instead
                 */
                productTableView.setItems(searchByProductName(userInput));
            }
        } else {
            ErrorHandling.errorAlert(2, "Alphanumeric entries only");
        }
    }

    /**
     * method for selling table attributes for every scene
     */
    public void setPartTableAttributes() {
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

    public void setProductTableAttributes() {
        /**
         * Set values for product ID column
         * set styling to center text for the column
         * disable resizability by user
         */
        productIDTableCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productIDTableCol.setStyle("-fx-alignment: CENTER;");
        productIDTableCol.setResizable(false);

        /**
         * Set values for product name column
         * set styling to center text for the column
         * disable resizability by user
         */
        productNameTableCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productNameTableCol.setStyle("-fx-alignment: CENTER;");
        productNameTableCol.setResizable(false);

        /**
         * Set values for product inventory level column
         * set styling to center text for the column
         * disable resizability by user
         */
        productInvLvlTableCol.setCellValueFactory(new PropertyValueFactory<>("productInvLevel"));
        productInvLvlTableCol.setStyle("-fx-alignment: CENTER;");
        productInvLvlTableCol.setResizable(false);

        /**
         * Set values for product price column
         * set styling to center text for the column
         * disable resizability by user
         */
        productPriceTableCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        productPriceTableCol.setStyle("-fx-alignment: CENTER;");
        productPriceTableCol.setResizable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /**
         * sets attributes for both tables on main screen
         */
        setPartTableAttributes();
        setProductTableAttributes();

        /**
         * populates main screen part and product tables
         */
        partTableView.setItems(getAllParts());
        productTableView.setItems(getAllProducts());
    }
}
