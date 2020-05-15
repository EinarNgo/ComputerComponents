package programutvikling.controllers.controllersHelper;

import javafx.scene.control.Alert;

public class Dialogs {

    /**
     * Dialog for error
     * @param msg
     */
    public static void showErrorDialog(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Feil!");
        alert.setHeaderText("Ugyldig data!");
        alert.setContentText(msg);

        alert.showAndWait();
    }

    /**
     * Dialog for success
     * @param msg
     */
    public static void showSuccessDialog(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Komponentregister");
        alert.setHeaderText("Operasjon vellykket");
        alert.setContentText(msg);

        alert.showAndWait();
    }

    /**
     * Dialog for ikke valgt element
     * @param msg
     */
    public static void showNoSelectDialog(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText("No component Selected");
        alert.setContentText(msg);

        alert.showAndWait();
    }


}
