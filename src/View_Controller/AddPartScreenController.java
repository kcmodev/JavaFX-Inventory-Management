package View_Controller;

import static Model.Inventory.addPart;

import Model.InHousePart;
import Model.OutsourcedPart;
import View_Controller.MainScreenController;
import static Model.Inventory.getAllParts;

import Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddPartScreenController {

    static final String ADD_PART_SCREEN_TITLE = "Add Part(s)";
    MainScreenController mainScreenController = new MainScreenController();

    @FXML private Label radioButtonLabelChanger;
    @FXML private TextField addPartIDTextField;
    @FXML private TextField addPartNameTextField;
    @FXML private TextField addPartInventoryTextField;
    @FXML private TextField addPartPriceTextField;
    @FXML private TextField addPartInvMaxTextField;
    @FXML private TextField addPartInvMinTextField;
    @FXML private TextField changedLabelTextField;


    public void setAddPartScreenSaveButton(ActionEvent event) {
        System.out.println("Add part save button clicked");

        // checks corresponding label to determine radio button selection
        // "Machine ID" = in house
        if (radioButtonLabelChanger.getText() == "Machine ID"){
            System.out.println("Adding with Machine ID w/ in house part");
            int partID = Integer.parseInt(addPartIDTextField.getText());
            String partName = addPartNameTextField.getText();
            int partInv = Integer.parseInt(addPartInventoryTextField.getText());
            double partPrice = Double.parseDouble(addPartPriceTextField.getText());
            int partInvMin = Integer.parseInt(addPartInvMinTextField.getText());
            int partInvMax = Integer.parseInt(addPartInvMaxTextField.getText());

            InHousePart inHousePart = new InHousePart(partID, partName, partPrice, partInv, partInvMin, partInvMax);
            inHousePart.setPartMachineID(Integer.parseInt(changedLabelTextField.getText()));
            addPart(inHousePart);
            System.out.println("Current in house parts list: " + getAllParts());
        }

        // checks corresponding label to determine radio button selection
        // "Company ID" = outsourced
        if (radioButtonLabelChanger.getText() == "Company ID"){
            System.out.println("Adding with company ID to outsourced");
        }

        // returns user to main screen after saving the part
        mainScreenController.windowManager(event, "MainScreen.fxml", mainScreenController.MAIN_SCREEN_TITLE);
    }

    public void setAddPartScreenCancelButton(ActionEvent event) {
        System.out.println("Add part screen cancel button clicked");
        mainScreenController.windowManager(event, "MainScreen.fxml", MainScreenController.MAIN_SCREEN_TITLE);
    }

    public void setInHouseRadioButton() {
        setRadioButtonLabelChanger("Machine ID", "Enter Machine ID");
        setRemainingTextFields();
    }

    public void setOutsourcedRadioButton() {
        setRadioButtonLabelChanger("Company ID", "Enter Company ID");
        setRemainingTextFields();
    }

    public void setRadioButtonLabelChanger(String labelID, String textField) {
        radioButtonLabelChanger.setText(labelID);
        setChangedLabelTextField(textField);

    }

    public void setChangedLabelTextField(String textField) {
        changedLabelTextField.setDisable(false);
        changedLabelTextField.setPromptText(textField);
    }

    public void setRemainingTextFields() {
        addPartIDTextField.setDisable(false);
        addPartNameTextField.setDisable(false);
        addPartInventoryTextField.setDisable(false);
        addPartPriceTextField.setDisable(false);
        addPartInvMaxTextField.setDisable(false);
        addPartInvMinTextField.setDisable(false);
    }

}
