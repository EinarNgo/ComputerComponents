package programutvikling.controllers.helpers;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class inputChecker {

    public boolean isInputTrue(String navn, String adresse, String postkode, String forsikring) {
        String errorMsg = "";

        if (navn == null || navn.length() < 2) {
            errorMsg += "Navnet er ugyldig \n";
        }

        if (adresse == null || adresse.length() < 2) {
            errorMsg += "Adressen er ugyldig \n";
        }

        if (postkode == null || postkode.length() != 4 ) {
            errorMsg += "Postkode er ugyldig \n";
        }

        if (forsikring == null || forsikring.length() !=8) {
            errorMsg += "Forsikringskode er ugyldig eller feil \n";
        }

        //dato

        //ubetalt erstaning i integer

        //forsikring - vent

        if (errorMsg.length() == 0) {
            return true;

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMsg);

            alert.showAndWait();

            return false;
        }

    }
}
