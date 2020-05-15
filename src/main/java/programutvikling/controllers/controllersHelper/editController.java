package programutvikling.controllers.controllersHelper;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import programutvikling.base.Component;


public class editController {

    /**
     * Kontroller for Edit view der du har mulighet til å endre komponenter du har i ComponentRegister
     */
    private Component Komponent;

    @FXML
    private TextField txtKomponent,txtNavn,txtProdusent,txtVekt,txtPris;

    @FXML
    private TextField txtLansert;


    /**
     * Metode for å sette verdier i tekstboksene
     */
    public void setKomponent(Component Komponent) {
        this.Komponent = Komponent;

        txtKomponent.setText(Komponent.getKomponent());
        txtNavn.setText(Komponent.getNavn());
        txtProdusent.setText(Komponent.getProdusent());
        txtVekt.setText(Integer.toString(Komponent.getVekt()));
        txtLansert.setText(Komponent.getLanser());
        txtPris.setText(Integer.toString(Komponent.getPris()));
    }


    /**
     * Metode for å endre verdier i registre
     */
    @FXML
    private void editKomponent() {

        boolean temp = true;

        try {
            Komponent.setKomponent(txtKomponent.getText());
            Komponent.setNavn(txtNavn.getText());
            Komponent.setProdusent(txtProdusent.getText());
            Komponent.setVekt(Integer.parseInt(txtVekt.getText()));
            Komponent.setLansert(txtLansert.getText());
            Komponent.setPris(Integer.parseInt(txtPris.getText()));

            Dialogs.showSuccessDialog("Komponeneten er endret");
        } catch (NumberFormatException nfe) {
            Dialogs.showErrorDialog("Kun tall i Vekt and Pris");
            temp = false;

        } catch (IllegalArgumentException e) {
            Dialogs.showErrorDialog( e.getMessage());
            temp = false;
        }

        if (temp == true) {
            closeWindow();
        }

    }


    /**
     * Metode for å logge vinduet som åpner
     */
    private void closeWindow() {
        Stage myStage = (Stage) txtKomponent.getScene().getWindow();
        myStage.close();
    }
}
