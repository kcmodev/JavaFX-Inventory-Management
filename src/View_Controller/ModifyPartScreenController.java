package View_Controller;

import Model.InHousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Part;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;



public class ModifyPartScreenController {

    /**
     * title for modify part screen to pass when switching screens
     */
    static final String MOD_PART_SCREEN_TITLE = "Modify Part(s)";
    private Part modifiedPart;

    MainScreenController mainScreenController = new MainScreenController();

    /**
     * initialize label and text fields for modify product screen
     */
    @FXML private Label modPartLabelChanger;
    @FXML private TextField modPartIDTextField;
    @FXML private TextField modPartNameTextField;
    @FXML private TextField modPartInventoryTextField;
    @FXML private TextField modPartPriceTextField;
    @FXML private TextField modPartInvMaxTextField;
    @FXML private TextField modPartInvMinTextField;
    @FXML private TextField modPartChangedTextField;

    @FXML private Button modPartSaveButton;
    @FXML private Button modPartCancelButton;
    @FXML private RadioButton inHouseRadio;
    @FXML private RadioButton outsourcedRadio;


    public void setTextFields(Part part){
        modifiedPart = part;

        modPartIDTextField.setText(Integer.toString(part.getPartID()));
        modPartNameTextField.setText(part.getPartName());
        modPartInventoryTextField.setText(Integer.toString((part.getPartStock())));
        modPartPriceTextField.setText(Double.toString(part.getPartPrice()));
        modPartInvMaxTextField.setText(Integer.toString(part.getPartStockMax()));
        modPartInvMinTextField.setText(Integer.toString(part.getPartStockMin()));

        if (part instanceof InHousePart){
            setRadioButtonLabels("Machine ID", "Enter Machine ID");
            modPartChangedTextField.setText(Integer.toString(((InHousePart) part).getPartMachineID()));
            inHouseRadio.setSelected(true);
            setModifyRemainingTextFields();
        }
        if (part instanceof OutsourcedPart){
            setRadioButtonLabels("Company ID", "Enter Company ID");
            modPartChangedTextField.setText(((OutsourcedPart) part).getCompanyName());
            outsourcedRadio.setSelected(true);
            setModifyRemainingTextFields();
        }

        Inventory.deletePart(part);
    }

    public void setModPartSaveButton(ActionEvent event) {
        System.out.println("mod part save button clicked");

        modifiedPart.setPartID(Integer.parseInt(modPartIDTextField.getText()));
        modifiedPart.setPartName(modPartNameTextField.getText());
        modifiedPart.setPartStock(Integer.parseInt(modPartInventoryTextField.getText()));
        modifiedPart.setPartPrice(Double.parseDouble(modPartPriceTextField.getText()));
        modifiedPart.setPartStockMax(Integer.parseInt(modPartInvMaxTextField.getText()));
        modifiedPart.setPartStockMin(Integer.parseInt(modPartInvMinTextField.getText()));

        Inventory.addPart(modifiedPart);
        mainScreenController.windowManager(event, "MainScreen.fxml", mainScreenController.MAIN_SCREEN_TITLE);
    }

    public void setModPartCancelButton(ActionEvent event) {
        System.out.println("mod part screen cancel button clicked");
        mainScreenController.windowManager(event, "MainScreen.fxml", MainScreenController.MAIN_SCREEN_TITLE);
    }

    public void setInHouseModifyRadioButton() { System.out.println("In house radio button selected");}

    public void setOutsourcedModifyRadioButton() {
        System.out.println("Outsourced radio button selected");
    }

    public void setRadioButtonLabels(String labelID, String textField) {
        modPartLabelChanger.setText(labelID);
        modPartChangedTextField.setPromptText(textField);
    }

    public void setModifyRemainingTextFields() {
        modPartNameTextField.setDisable(false);
        modPartInventoryTextField.setDisable(false);
        modPartPriceTextField.setDisable(false);
        modPartInvMaxTextField.setDisable(false);
        modPartInvMinTextField.setDisable(false);
        modPartChangedTextField.setDisable(false);
    }
}