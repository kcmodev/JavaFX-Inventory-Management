package View_Controller;

import Model.InHousePart;
import Model.Inventory;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainScreenController implements Initializable {

    static final String MAIN_SCREEN_TITLE = "Christensen Software 1 Performance Assessment";

    @FXML private TableView<InHousePart> mainPartTableView;
    @FXML private TableColumn<Object, Integer> mainPartIDTableColumn;
    @FXML private TableColumn<Object, SimpleStringProperty> mainPartNameTableColumn;
    @FXML private TableColumn<Object, Integer> mainPartInventoryTableColumn;
    @FXML private TableColumn<Object, Double> mainPartPriceTableColumn;

    public void windowManager(ActionEvent event, String fileName, String windowTitle) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(fileName));
            Scene scene = new Scene(parent);
            Stage newWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();

            newWindow.setScene(scene);
            newWindow.setTitle(windowTitle);
            newWindow.show();
        } catch (IOException e){
            System.out.println("Failed to load new window. Goodbye.");
            System.exit(-1);
        }
    }
    public void setExitButtonClicked(ActionEvent event) {
        System.out.println("Exit button clicked");
        windowManager(event, "ExitWarningPopup.fxml", ExitWarningPopupController.EXIT_POPUP_TITLE);
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

    public void setAddProductButton(ActionEvent event) {
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
        mainPartIDTableColumn.setResizable(false);
        mainPartNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        mainPartNameTableColumn.setResizable(false);
        mainPartInventoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        mainPartInventoryTableColumn.setResizable(false);
        mainPartPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        mainPartPriceTableColumn.setResizable(false);

//        mainPartTableView.setItems(InHousePart.getAllParts());
    }


}
