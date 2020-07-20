package Model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class ErrorHandling {
    private static String headerText, titleText, errorText;

    /**
     * confirmation alert popup handler
     * passes in string variable to define type of event in the text box
     */
    public static final boolean confirmationAlert(String action){
        /**
         * instantiate alert popup for exiting the program
         */
        Alert exiting = new Alert(Alert.AlertType.CONFIRMATION);
        exiting.setTitle("Confirmation");
        exiting.setHeaderText("Please confirm.");
        exiting.setContentText("Are you sure you would like to " + action + "?");

        /**
         * set method for user to choose to quit by waiting on button press
         */
        Optional<ButtonType> choice = exiting.showAndWait();

        if (choice.get() == ButtonType.OK) { // user clicks yes
            System.out.println("ok clicked in alert popup");
            return true;

        } else if (choice.get() == ButtonType.CANCEL){ // user clicks no
            System.out.println("no selected, closing alert popup");
            exiting.close();
        }
        return false;
    }

    /**
     * error alert popup handler
     */
    public static final void errorAlert(int errorCode){

        if (errorCode == 1){ // no selection made
            headerText = "Error";
            titleText = "No selection made";
            errorText = "You must make a selection";
        }
        if (errorCode == 2){ // invalid input
            headerText = "Error";
            titleText = "Invalid input";
            errorText = "You must enter valid input";
        }

        Alert invalidChoice = new Alert(Alert.AlertType.ERROR);
        invalidChoice.setHeaderText(headerText);
        invalidChoice.setTitle(titleText);
        invalidChoice.setContentText(errorText);
        invalidChoice.showAndWait();
    }
}
