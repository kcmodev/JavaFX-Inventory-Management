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

    /**
     * takes passed object and instantiates text fields with their appropriate values
     * as well as sets the radio button to the appropriate selection according to the part selected
     */
    public void setTextFields(Part part){
        modifiedPart = part;

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

        modifiedPart.setPartID(Integer.parseInt(modPartIDTextField.getText()));
        modifiedPart.setPartName(modPartNameTextField.getText());
        modifiedPart.setPartStock(Integer.parseInt(modPartInventoryTextField.getText()));
        modifiedPart.setPartPrice(Double.parseDouble(modPartPriceTextField.getText()));
        modifiedPart.setPartStockMax(Integer.parseInt(modPartInvMaxTextField.getText()));
        modifiedPart.setPartStockMin(Integer.parseInt(modPartInvMinTextField.getText()));

        /**
         * cycles through list of all parts
         * compares part ID and class type to make sure they are the same object
         * deletes said part to make a space for the modified part with the same ID
         * breaks when condition is met to save time by not parsing the rest of the list
         */
        for (Part part : Inventory.getAllParts()){
            if (part.getPartID() == modifiedPart.getPartID() && part.getClass().equals(modifiedPart.getClass())){
                Inventory.deletePart(part);
                break;
            }
        }

        /**
         * add newly modified part and return to the main screen
         */
        Inventory.addPart(modifiedPart); // adds newly modified part
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
        setRadioButtonLabels("Machine ID", "Enter Machine ID");
    }
    public void setOutsourcedLabel() {
        System.out.println("Outsourced radio button selected");
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