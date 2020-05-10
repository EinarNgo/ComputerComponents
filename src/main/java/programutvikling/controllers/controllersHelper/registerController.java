package programutvikling.controllers.controllersHelper;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import programutvikling.base.Component;
import programutvikling.controllers.exception.Dialogs;
import programutvikling.base.ComponentRegister;

public class registerController {

    private ComponentRegister cRegister;

    @FXML
    private TextField txtKomponent,txtNavn,txtProdusent,txtVekt,txtPris;

    @FXML
    private TextField txtLansert;

    public void setKomponent(ComponentRegister cRegister) {
        this.cRegister = cRegister;
    }

    @FXML
    private void registerKomponent() {
        boolean temp = true;

        try {
            Component c = createComponent();
            Dialogs.showSuccessDialog(c.getNavn()+"er lagd til");
            cRegister.addComponent(c);
        } catch (NumberFormatException nfe) {
            Dialogs.showErrorDialog("Please only number in Vekt and Pris");
            temp = false;
        } catch (IllegalArgumentException e) {
            Dialogs.showErrorDialog( e.getMessage() +" eller være tom");
            temp = false;
        }

        if (temp == true) {
            closeWindow();
        }

    }

    private Component createComponent() {
        return new Component(txtKomponent.getText(), txtNavn.getText() ,txtProdusent.getText(),Integer.parseInt(txtVekt.getText()), txtLansert.getText(),Integer.parseInt(txtPris.getText()));
    }

    private void closeWindow() {
        Stage myStage = (Stage) txtKomponent.getScene().getWindow();
        myStage.close();
    }
}
