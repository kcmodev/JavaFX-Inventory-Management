package View_Controller;

import Model.InHousePart;
import Model.OutsourcedPart;
import Model.Part;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class ModifyPartScreenController implements Initializable {

    /**
     * title for modify part screen to pass when switching screens
     */
    static final String MOD_PART_SCREEN_TITLE = "Modify Part(s)";
    private Part modifyingPart;
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
        System.out.println("hits text fields method");
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
    }

    public void setModPartSaveButton() { System.out.println("mod part save button clicked"); }

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
        modPartIDTextField.setDisable(false);
        modPartNameTextField.setDisable(false);
        modPartInventoryTextField.setDisable(false);
        modPartPriceTextField.setDisable(false);
        modPartInvMaxTextField.setDisable(false);
        modPartInvMinTextField.setDisable(false);
        modPartChangedTextField.setDisable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}