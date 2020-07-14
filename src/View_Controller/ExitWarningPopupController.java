package View_Controller;

import javafx.event.ActionEvent;

public class ExitWarningPopupController {

    static final String EXIT_POPUP_TITLE = "Confirm program termination";
    MainScreenController mainScreenController = new MainScreenController();

    public void setExitYesButton() {
        System.out.println("Quitting after confirmation");
        System.exit(1);
    }

    public void setExitNoButton(ActionEvent event) {
        mainScreenController.windowManager(event, "MainScreen.fxml", MainScreenController.MAIN_SCREEN_TITLE);
    }
}
