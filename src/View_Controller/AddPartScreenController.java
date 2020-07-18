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

    /**
     * title for add part screen to pass when switching screens
     */
    static final String ADD_PART_SCREEN_TITLE = "Add Part(s)";
    MainScreenController mainScreenController = new MainScreenController();

    /**
     * initialize label and input fields on add part screen
     */
    @FXML private Label radioButtonLabelChanger;
    @FXML private TextField addPartIDTextField;
    @FXML private TextField addPartNameTextField;
    @FXML private TextField addPartInventoryTextField;
    @FXML private TextField addPartPriceTextField;
    @FXML private TextField addPartInvMaxTextField;
    @FXML private TextField addPartInvMinTextField;
    @FXML private TextField changedLabelTextField;


    /**
     * add part save button handler
     */
    public void setAddPartScreenSaveButton(ActionEvent event) {
        System.out.println("Add part save button clicked");
        /**
         * assign and parse all test in text fields to the current part
         * and to the correct data type
         */
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

    /**
     * handles cancel button click
     * send user back to main screen
     */
    public void setAddPartScreenCancelButton(ActionEvent event) {
        System.out.println("Add part screen cancel button clicked");
        mainScreenController.windowManager(event, "MainScreen.fxml", MainScreenController.MAIN_SCREEN_TITLE);
    }

    /**
     * the next 2 methods set the label to in house or outsourced
     * based on the current radio button selection and enable
     * text fields to be available for user input
     */
    public void setInHouseRadioButton() {
        setRadioButtonLabelChanger("Machine ID", "Enter Machine ID");
        enableTextFields();
    }
    public void setOutsourcedRadioButton() {
        setRadioButtonLabelChanger("Company ID", "Enter Company ID");
        enableTextFields();
    }

    /**
     * changes label for company name or machine ID based on
     * current radio button selection
     */
    private void setRadioButtonLabelChanger(String labelID, String textField) {
        radioButtonLabelChanger.setText(labelID);
        changedLabelTextField.setPromptText(textField);
    }

    private void enableTextFields() {
        addPartIDTextField.setDisable(false);
        addPartNameTextField.setDisable(false);
        addPartInventoryTextField.setDisable(false);
        addPartPriceTextField.setDisable(false);
        addPartInvMaxTextField.setDisable(false);
        addPartInvMinTextField.setDisable(false);
        changedLabelTextField.setDisable(false);
    }

}
