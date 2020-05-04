package programutvikling.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import programutvikling.base.Component;

public class RegistrerComponent {

    @FXML
    private TextField txtKomponent,txtProdusent,txtVekt,txtVersjon,txtPris;


    Component createPersonfromGUIandResetFields() {
        try {
            Component c = createComponent();
            resetFields();
            //Dialogs.showSuccessDialog(p.getName());
            return c;
        } catch (NumberFormatException nfe) {
            //Dialogs.showErrorDialog("Tast inn heltall for alder og fødselsdato");
        } catch (IllegalArgumentException iae) {
            //Dialogs.showErrorDialog(iae.getMessage());
        }

        return null;
    }

    private Component createComponent() {

        return new Component(txtKomponent.getText(), txtProdusent.getText(), Integer.parseInt(txtVekt.getText()), txtVersjon.getText(), null,Integer.parseInt(txtPris.getText()));
    }

    private String getString(TextField field) {
        return field.getText();
    }

    private int getInt(TextField field) {
        return Integer.parseInt(getString(field));
    }

    private void resetFields() {
        txtKomponent.setText("");
        txtProdusent.setText("");
        txtVekt.setText("");
        txtVersjon.setText("");
        //txtLansert.setText("");
        txtPris.setText("");
    }
}
