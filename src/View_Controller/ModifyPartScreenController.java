package View_Controller;

import Model.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import javax.xml.bind.ValidationException;


public class ModifyPartScreenController {

    /**
     * title to pass when switching screens
     */
    static final String MOD_PART_SCREEN_TITLE = "Modify Part(s)";

    MainScreenController mainScreenController = new MainScreenController();

    /**
     * initialize label and text fields for modify product screen
     */
    @FXML private TextField modPartIDTextField;
    @FXML private TextField modPartNameTextField;
    @FXML private TextField modPartInvTextField;
    @FXML private TextField modPartPriceTextField;
    @FXML private TextField modPartInvMaxTextField;
    @FXML private TextField modPartInvMinTextField;

    @FXML private TextField modPartChangedTextField;
    @FXML private Label modPartChangedLabel;

    /**
     * used to set a default selection for the radio buttons
     * depending on the type of object
     */
    @FXML private RadioButton inHouseRadio;
    @FXML private RadioButton outsourcedRadio;
    @FXML private ToggleGroup modPartToggleGroup;

    /**
     * takes passed object and instantiates text fields with their appropriate values
     * as well as sets the radio button to the appropriate selection according to the part selected
     */
    public void setTextFields(Part part){
        modPartIDTextField.setText(Integer.toString(part.getPartID()));
        modPartNameTextField.setText(part.getPartName());
        modPartInvTextField.setText(Integer.toString((part.getPartStock())));
        modPartPriceTextField.setText(Double.toString(part.getPartPrice()));
        modPartInvMaxTextField.setText(Integer.toString(part.getPartStockMax()));
        modPartInvMinTextField.setText(Integer.toString(part.getPartStockMin()));

        if (part instanceof InHousePart){ // checks if part in in house
            modPartChangedTextField.setText(Integer.toString(((InHousePart) part).getPartMachineID()));
            inHouseRadio.setSelected(true); // sets radio button to in house
            setInHouseLabel();
            setModifyRemainingTextFields();
        }
        if (part instanceof OutsourcedPart){ // checks if part is outsourced
            modPartChangedTextField.setText(((OutsourcedPart) part).getCompanyName());
            outsourcedRadio.setSelected(true); // sets radio button to outsourced
            setOutsourcedLabel();
            setModifyRemainingTextFields();
        }
    }

    /**
     * save button handler
     */
    public void setModPartSaveButton(ActionEvent event) {
        /**
         * differentiates between in house and outsourced
         * creates the appropriate object, updates the infor and then
         * modifies the existing object with the matching part ID
         */
        try {
            if (inHouseRadio.isSelected()) {
                InHousePart inHouseMod = new InHousePart();

                inHouseMod.setPartID(Integer.parseInt(modPartIDTextField.getText()));
                inHouseMod.setPartName(modPartNameTextField.getText());
                inHouseMod.setPartStock(Integer.parseInt(modPartInvTextField.getText()));
                inHouseMod.setPartPrice(Double.parseDouble(modPartPriceTextField.getText()));
                inHouseMod.setPartStockMax(Integer.parseInt(modPartInvMaxTextField.getText()));
                inHouseMod.setPartStockMin(Integer.parseInt(modPartInvMinTextField.getText()));
                inHouseMod.setPartMachineID(Integer.parseInt(modPartChangedTextField.getText()));

                inHouseMod.partValidation();
                Inventory.modifyPart(inHouseMod);
                mainScreenController.windowManager(event, "MainScreen.fxml", mainScreenController.MAIN_SCREEN_TITLE);

            } else if (outsourcedRadio.isSelected()) {
                OutsourcedPart outsourceMod = new OutsourcedPart();

                outsourceMod.setPartID(Integer.parseInt(modPartIDTextField.getText()));
                outsourceMod.setPartName(modPartNameTextField.getText());
                outsourceMod.setPartStock(Integer.parseInt(modPartInvTextField.getText()));
                outsourceMod.setPartPrice(Double.parseDouble(modPartPriceTextField.getText()));
                outsourceMod.setPartStockMax(Integer.parseInt(modPartInvMaxTextField.getText()));
                outsourceMod.setPartStockMin(Integer.parseInt(modPartInvMinTextField.getText()));
                outsourceMod.setCompanyName(modPartChangedTextField.getText());

                outsourceMod.partValidation();
                Inventory.modifyPart(outsourceMod);
                mainScreenController.windowManager(event, "MainScreen.fxml", mainScreenController.MAIN_SCREEN_TITLE);
            }
        } catch (ValidationException e){
                ErrorHandling.errorAlert(2, e.getMessage());
        } catch (NumberFormatException e){
                ErrorHandling.errorAlert(2, "Please enter valid input");
                e.getStackTrace();
        }
    }

    /**
     * cancel button handler
     * returns user to main screen without any changes
     */
    public void setModPartCancelButton(ActionEvent event) {
        if (ErrorHandling.confirmationAlert("cancel all changes and return to the main screen")){
            mainScreenController.windowManager(event, "MainScreen.fxml", MainScreenController.MAIN_SCREEN_TITLE);
        }
    }

    /**
     * the next 2 methods set the text label to in house or outsourced
     * based on the current radio button selection
     */
    public void setInHouseLabel() {
        System.out.println("In house radio button selected");
        System.out.println(modPartToggleGroup.getSelectedToggle().toString());
        setRadioButtonLabels("Machine ID", "Enter Machine ID");
    }
    public void setOutsourcedLabel() {
        System.out.println("Outsourced radio button selected");
        System.out.println(modPartToggleGroup.getSelectedToggle().toString());
        setRadioButtonLabels("Company", "Enter Company Name");
    }

    /**
     * changes the label to either "company name" or "machine id"
     * depending on radio button selection
     * selection will start on the appropriate selection for the
     * current part being modified
     */
    public void setRadioButtonLabels(String labelID, String textField) {
        modPartChangedLabel.setText(labelID);
        modPartChangedTextField.setPromptText(textField);
    }

    /**
     * makes remaining text fields editable
     */
    public void setModifyRemainingTextFields() {
        modPartNameTextField.setDisable(false);
        modPartInvTextField.setDisable(false);
        modPartPriceTextField.setDisable(false);
        modPartInvMaxTextField.setDisable(false);
        modPartInvMinTextField.setDisable(false);
        modPartChangedTextField.setDisable(false);
    }
}