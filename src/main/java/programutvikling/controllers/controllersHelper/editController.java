package programutvikling.controllers.controllersHelper;

import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import programutvikling.base.Component;
import programutvikling.base.ComponentValidator;
import programutvikling.controllers.exception.Dialogs;


public class editController {

    private Component Komponent;

    @FXML
    private TextField txtKomponent,txtNavn,txtProdusent,txtVekt,txtPris;

    @FXML
    private TextField txtLansert;


    public void setKomponent(Component Komponent) {
        this.Komponent = Komponent;

        txtKomponent.setText(Komponent.getKomponent());
        txtNavn.setText(Komponent.getNavn());
        txtProdusent.setText(Komponent.getProdusent());
        txtVekt.setText(Integer.toString(Komponent.getVekt()));
        txtLansert.setText(Komponent.getLanser());
        txtPris.setText(Integer.toString(Komponent.getPris()));
    }


    @FXML
    private void editKomponent() {

        boolean temp = true;

        try {
            if(!ComponentValidator.stringInput(txtKomponent.getText())) {
                throw new IllegalArgumentException("Komponent er ugyldig, kan ikke inneholde tall");
            } else {
                Komponent.setNavn(txtKomponent.getText());
            }

            if(!ComponentValidator.stringInput(txtNavn.getText())) {
                throw new IllegalArgumentException("Navn er ugyldig, kan ikke inneholde tall");
            } else {
                Komponent.setNavn(txtNavn.getText());
            }

            if(!ComponentValidator.stringInput(txtProdusent.getText())) {
                throw new IllegalArgumentException("Produsent er ugyldig, kan ikke inneholde tall");
            } else {
                Komponent.setNavn(txtProdusent.getText());
            }

            if(!ComponentValidator.intInput(Integer.parseInt(txtVekt.getText()))) {
                throw new IllegalArgumentException("Vekt er ugyldig, kan ikke inneholde bokstaver");
            } else {
                Komponent.setNavn(txtVekt.getText());
            }

            if(!ComponentValidator.stringInput(txtLansert.getText())) {
                throw new IllegalArgumentException("Lansert er ugyldig, kan ikke inneholde tall");
            } else {
                Komponent.setNavn(txtLansert.getText());
            }

            if(!ComponentValidator.intInput(Integer.parseInt(txtPris.getText()))) {
                throw new IllegalArgumentException("Pris er ugyldig, kan ikke inneholde bokstaver");
            } else {
                Komponent.setNavn(txtPris.getText());
            }

            Dialogs.showSuccessDialog("Komponeneten er endret");
        } catch (NumberFormatException nfe) {
            Dialogs.showErrorDialog("Please only number in Vekt and Pris");
            temp = false;

        } catch (IllegalArgumentException e) {
            Dialogs.showErrorDialog( e.getMessage());
            temp = false;
        }

        if (temp == true) {
            closeWindow();
        }

    }


    private void closeWindow() {
        Stage myStage = (Stage) txtKomponent.getScene().getWindow();
        myStage.close();
    }
}
