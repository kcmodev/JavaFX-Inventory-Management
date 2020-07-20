package View_Controller;

import Model.InHousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Part;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;


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
        System.out.println("previous class object type: " + part.getClass());

        modPartIDTextField.setText(Integer.toString(part.getPartID()));
        modPartNameTextField.setText(part.getPartName());
        modPartInventoryTextField.setText(Integer.toString((part.getPartStock())));
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
     * save button click handler
     */
    public void setModPartSaveButton(ActionEvent event) {
        System.out.println("mod part save button clicked");

        int moddedPartID = Integer.parseInt(modPartIDTextField.getText());
        String moddedPartName = modPartNameTextField.getText();
        int moddedPartInv = Integer.parseInt(modPartInventoryTextField.getText());
        double moddedPartPrice = Double.parseDouble(modPartPriceTextField.getText());
        int moddedPartInvMax = Integer.parseInt(modPartInvMaxTextField.getText());
        int moddedPartInvMin = Integer.parseInt(modPartInvMinTextField.getText());

        System.out.println("selected toggle: " + modPartToggleGroup.getSelectedToggle().toString());

        if (modPartToggleGroup.getSelectedToggle().toString().contains("In-House")){
            int moddedMachineID = Integer.parseInt(modPartChangedTextField.getText());
            Part modifiedPart = new InHousePart(moddedPartID, moddedPartName, moddedPartPrice, moddedPartInv,
                    moddedPartInvMin, moddedPartInvMax, moddedMachineID);
            Inventory.modifyPart(modifiedPart);
        } else if (modPartToggleGroup.getSelectedToggle().toString().contains("Outsourced")){
            String moddedCompID = modPartChangedTextField.getText();
            Part modifiedPart = new OutsourcedPart(moddedPartID, moddedPartName, moddedPartPrice, moddedPartInv,
                    moddedPartInvMin, moddedPartInvMax, moddedCompID);
            Inventory.modifyPart(modifiedPart);
        }
        System.out.println("makes it past the if statements");

        mainScreenController.windowManager(event, "MainScreen.fxml", mainScreenController.MAIN_SCREEN_TITLE);
    }

    /**
     * cancel button handler
     * returns user to main screen without any changes
     */
    public void setModPartCancelButton(ActionEvent event) {
        System.out.println("mod part screen cancel button clicked");
        mainScreenController.windowManager(event, "MainScreen.fxml", MainScreenController.MAIN_SCREEN_TITLE);
    }

    /**
     * the next 2 methods set the label to in house or outsourced
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
        setRadioButtonLabels("Company ID", "Enter Company ID");
    }

    /**
     * changes the label to either "company name" or "machine id"
     * depending on radio button selection
     * selection will start on the appropriate selection for the
     * current part being modified
     */
    public void setRadioButtonLabels(String labelID, String textField) {
        modPartLabelChanger.setText(labelID);
        modPartChangedTextField.setPromptText(textField);
    }

    /**
     * makes remaining text fields editable
     */
    public void setModifyRemainingTextFields() {
        modPartNameTextField.setDisable(false);
        modPartInventoryTextField.setDisable(false);
        modPartPriceTextField.setDisable(false);
        modPartInvMaxTextField.setDisable(false);
        modPartInvMinTextField.setDisable(false);
        modPartChangedTextField.setDisable(false);
    }
}