package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class ModifyPartScreenController {

    static final String MOD_PART_SCREEN_TITLE = "Modify Part(s)";
    MainScreenController mainScreenController = new MainScreenController();

    @FXML private Label modifyRadioButtonLabelChanger;
    @FXML private TextField modPartIDTextField;
    @FXML private TextField modPartNameTextField;
    @FXML private TextField modPartInventoryTextField;
    @FXML private TextField modPartPriceTextField;
    @FXML private TextField modPartInvMaxTextField;
    @FXML private TextField modPartInvMinTextField;
    @FXML private TextField modPartChangedLabelTextField;


    public void setModPartScreenSaveButton() {
        System.out.println("mod part save button clicked");
    }

    public void setModPartScreenCancelButton(ActionEvent event) {
        System.out.println("mod part screen cancel button clicked");
        mainScreenController.windowManager(event, "MainScreen.fxml", MainScreenController.MAIN_SCREEN_TITLE);
    }

    public void setInHouseModifyRadioButton() {
        setModifyRadioButtonLabelChanger("Machine ID", "Enter Machine ID");
        setModifyRemainingTextFields();
    }

    public void setOutsourcedModifyRadioButton() {
        setModifyRadioButtonLabelChanger("Company ID", "Enter Company ID");
        setModifyRemainingTextFields();
    }

    public void setModifyRadioButtonLabelChanger(String labelID, String textField) {
        modifyRadioButtonLabelChanger.setText(labelID);
        setModifyChangedLabelTextField(textField);

    }

    public void setModifyChangedLabelTextField(String textField) {
        modPartChangedLabelTextField.setDisable(false);
        modPartChangedLabelTextField.setPromptText(textField);
    }

    public void setModifyRemainingTextFields() {
        modPartIDTextField.setDisable(false);
        modPartNameTextField.setDisable(false);
        modPartInventoryTextField.setDisable(false);
        modPartPriceTextField.setDisable(false);
        modPartInvMaxTextField.setDisable(false);
        modPartInvMinTextField.setDisable(false);
    }

}