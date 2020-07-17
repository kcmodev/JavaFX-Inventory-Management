package View_Controller;

import static Model.Inventory.addPart;
import static Model.Inventory.getAllParts;

import Model.InHousePart;
import Model.OutsourcedPart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


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
        int partID = Integer.parseInt(addPartIDTextField.getText());
        String partName = addPartNameTextField.getText();
        int partInv = Integer.parseInt(addPartInventoryTextField.getText());
        double partPrice = Double.parseDouble(addPartPriceTextField.getText());
        int partInvMin = Integer.parseInt(addPartInvMinTextField.getText());
        int partInvMax = Integer.parseInt(addPartInvMaxTextField.getText());

        /**
         * checks corresponding label to determine radio button selection
         * "Machine ID" = in house
         */
        if (radioButtonLabelChanger.getText() == "Machine ID"){
            System.out.println("Adding with Machine ID w/ in house part");

            InHousePart inHousePart = new InHousePart(partID, partName, partPrice, partInv, partInvMin, partInvMax);
            inHousePart.setPartMachineID(Integer.parseInt(changedLabelTextField.getText()));
            addPart(inHousePart);
            System.out.println("Current in house parts list: " + getAllParts());
        }

        /**
         * checks corresponding label to determine radio button selection
         * "Company ID" = outsourced
         */
        if (radioButtonLabelChanger.getText() == "Company ID"){
            System.out.println("Adding with company ID to outsourced");

            OutsourcedPart outsourcedPart = new OutsourcedPart(partID, partName, partPrice, partInv, partInvMin, partInvMax);
            outsourcedPart.setCompanyName(changedLabelTextField.getText());
            addPart(outsourcedPart);
            System.out.println("Current in house parts list: " + getAllParts());
        }

        /**
         * returns user to main screen after saving the part
         */
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

    private void setRadioButtonLabelChanger(String labelID, String textField) {
        radioButtonLabelChanger.setText(labelID);
        setChangedLabelTextField(textField);

    }

    private void setChangedLabelTextField(String textField) {
        changedLabelTextField.setDisable(false);
        changedLabelTextField.setPromptText(textField);
    }

    private void setRemainingTextFields() {
        addPartIDTextField.setDisable(false);
        addPartNameTextField.setDisable(false);
        addPartInventoryTextField.setDisable(false);
        addPartPriceTextField.setDisable(false);
        addPartInvMaxTextField.setDisable(false);
        addPartInvMinTextField.setDisable(false);
    }

}
