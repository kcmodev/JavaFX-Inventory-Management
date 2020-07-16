package View_Controller;

import Model.Part;
import Model.Product;
import static Model.Inventory.getAllParts;
import static Model.Inventory.getAllProducts;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class MainScreenController implements Initializable {

    static final String MAIN_SCREEN_TITLE = "Christensen Software 1 Performance Assessment";

    // defines structure for main screen part table
    @FXML private TableView<Part> mainPartTableView;
    @FXML private TableColumn<Part, Integer> mainPartIDTableColumn;
    @FXML private TableColumn<Part, SimpleStringProperty> mainPartNameTableColumn;
    @FXML private TableColumn<Part, Integer> mainPartInventoryTableColumn;
    @FXML private TableColumn<Part, Double> mainPartPriceTableColumn;

    // defintes structure for main screen product table
    @FXML private TableView<Product> mainScreenProductTableView;
    @FXML private TableColumn<Product, Integer> mainScreenProductIDTableColumn;
    @FXML private TableColumn<Product, SimpleStringProperty> mainScreenProductNameTableColumn;
    @FXML private TableColumn<Product, Integer> mainScreenProductInvLvlTableColumn;
    @FXML private TableColumn<Product, Double> mainScreenProductPriceTableColumn;

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

        // instantiate alert popup for exiting the program
        Alert exiting = new Alert(Alert.AlertType.CONFIRMATION);
        exiting.setTitle("Close program");
        exiting.setContentText("Are you sure you would like to close the program?");

        // set method for user to choose to quit by waiting on button press
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
    }

    public void setSearchByPartButton() {
        System.out.println("Search by part button clicked");
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
    }

    public void setSearchByProductButton() {
        System.out.println("Search by product button clicked");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPartIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        mainPartIDTableColumn.setStyle("-fx-alignment: CENTER;");
        mainPartIDTableColumn.setResizable(false);
        mainPartNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        mainPartNameTableColumn.setStyle("-fx-alignment: CENTER;");
        mainPartNameTableColumn.setResizable(false);
        mainPartInventoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        mainPartInventoryTableColumn.setStyle("-fx-alignment: CENTER;");
        mainPartInventoryTableColumn.setResizable(false);
        mainPartPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        mainPartPriceTableColumn.setStyle("-fx-alignment: CENTER;");
        mainPartPriceTableColumn.setResizable(false);

        populateMainScreenPartTable();

        mainScreenProductIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        mainScreenProductIDTableColumn.setStyle("-fx-alignment: CENTER;");
        mainScreenProductIDTableColumn.setResizable(false);
        mainScreenProductNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        mainScreenProductNameTableColumn.setStyle("-fx-alignment: CENTER;");
        mainScreenProductNameTableColumn.setResizable(false);
        mainScreenProductInvLvlTableColumn.setCellValueFactory(new PropertyValueFactory<>("productInvLevel"));
        mainScreenProductInvLvlTableColumn.setStyle("-fx-alignment: CENTER;");
        mainScreenProductInvLvlTableColumn.setResizable(false);
        mainScreenProductPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        mainScreenProductPriceTableColumn.setStyle("-fx-alignment: CENTER;");
        mainScreenProductPriceTableColumn.setResizable(false);

        populateMainScreenProductTable();
    }

    public void populateMainScreenPartTable(){
        mainPartTableView.setItems(getAllParts());
    }

    public void populateMainScreenProductTable() { mainScreenProductTableView.setItems(getAllProducts()); }
}
