/**
 * Author: kcmodev
 * Class: C482 Software 1
 * Email: ****@wgu.edu
 * Date Submitted: 7/21/2020
 */

package controller;

import error_handling.PopupHandler;
import model.InHousePart;
import model.OutsourcedPart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.xml.bind.ValidationException;
import java.net.URL;
import java.util.ResourceBundle;

import static model.Inventory.*;


public class AddPartController implements Initializable {

    /**
     * title to pass when switching screens
     */
    static final String ADD_PART_SCREEN_TITLE = "Add Part(s)";

    private static final MainScreenController mainScreenController = new MainScreenController();

    /**
     * initialize label and input fields on add part screen
     */
    @FXML
    private Label textFieldLabel;
    @FXML
    private TextField partIDTextField;
    @FXML
    private TextField partNameTextField;
    @FXML
    private TextField partInvTextField;
    @FXML
    private TextField partPriceTextField;
    @FXML
    private TextField partInvMaxTextField;
    @FXML
    private TextField partInvMinTextField;
    @FXML
    private TextField changedLabelTextField;

    /**
     * used to set a default selection for the radio buttons
     * depending on the type of object
     */
    @FXML
    private RadioButton inHouseRadio;
    @FXML
    private RadioButton outsourcedRadio;
    @FXML
    private Button addPartScreenSaveButton;

    /**
     * add part save button handler
     */
    public void partScreenSaveButton(ActionEvent event) {
        /**
         * assign and parse all test in text fields to the current part
         * and to the correct data type
         */
        try {
            String partName = partNameTextField.getText();
            int partInvMin = Integer.parseInt(partInvMinTextField.getText());
            int partInv = Integer.parseInt(partInvTextField.getText());
            double partPrice = Double.parseDouble(partPriceTextField.getText());
            int partInvMax = Integer.parseInt(partInvMaxTextField.getText());

            /**
             * checks corresponding label to determine radio button selection
             * "Machine ID" = in house
             */
            if (inHouseRadio.isSelected()) {
                int machineID = Integer.parseInt(changedLabelTextField.getText());
                InHousePart inHousePart = new InHousePart(99, partName, partPrice, partInv, partInvMin, partInvMax, machineID);
                inHousePart.partValidation();
                inHousePart.setPartID(idGenerator());
                addPart(inHousePart);
                mainScreenController.windowManager(event, "/gui/MainScreen.fxml", mainScreenController.MAIN_SCREEN_TITLE);
            }

            /**
             * checks corresponding label to determine radio button selection
             * "Company ID" = outsourced
             */
            if (outsourcedRadio.isSelected()) {
                String companyName = changedLabelTextField.getText();
                OutsourcedPart outsourcedPart = new OutsourcedPart(99, partName, partPrice, partInv, partInvMin, partInvMax, companyName);
                outsourcedPart.partValidation();
                outsourcedPart.setPartID(idGenerator());
                addPart(outsourcedPart);
                mainScreenController.windowManager(event, "/gui/MainScreen.fxml", mainScreenController.MAIN_SCREEN_TITLE);
            }
        } catch (ValidationException e) {
            PopupHandler.errorAlert(2, e.getMessage());

        } catch (NumberFormatException e) {
            PopupHandler.errorAlert(2);

        }
    }

    /**
     * handles cancel button click
     * send user back to main screen
     */
    public void setAddPartScreenCancelButton(ActionEvent event) {
        System.out.println("Add part screen cancel button clicked");

        if (PopupHandler.confirmationAlert("cancel all changes and return to the main screen")){
            mainScreenController.windowManager(event, "/gui/MainScreen.fxml", MainScreenController.MAIN_SCREEN_TITLE);
        }
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
        setRadioButtonLabelChanger("Company", "Enter Company Name");
        enableTextFields();
    }

    /**
     * changes label for company name or machine ID based on
     * current radio button selection
     */
    private void setRadioButtonLabelChanger(String labelID, String textField) {
        textFieldLabel.setText(labelID);
        changedLabelTextField.setPromptText(textField);
    }

    private void enableTextFields() {
        addPartScreenSaveButton.setDisable(false);
        partNameTextField.setDisable(false);
        partInvTextField.setDisable(false);
        partPriceTextField.setDisable(false);
        partInvMaxTextField.setDisable(false);
        partInvMinTextField.setDisable(false);
        changedLabelTextField.setDisable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partIDTextField.setPromptText("Auto Gen ID");
    }
}
