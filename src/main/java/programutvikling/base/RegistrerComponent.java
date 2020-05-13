package programutvikling.base;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import programutvikling.base.Component;

public class RegistrerComponent {

    @FXML
    private TextField txtKomponent,txtNavn,txtProdusent,txtVekt,txtPris,txtLansert;

    /*
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
    */


    private Component createComponent() {

        return new Component(txtKomponent.getText(), txtNavn.getText(), txtProdusent.getText(), Integer.parseInt(txtVekt.getText()), null,Integer.parseInt(txtPris.getText()));
    }


    public void resetFields() {
        txtKomponent.setText("");
        txtNavn.setText("");
        txtProdusent.setText("");
        txtVekt.setText("");
        txtLansert.setText("");
        txtPris.setText("");
    }
}
