package programutvikling.controllers.controllersHelper;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import programutvikling.base.Component;
import programutvikling.base.ComponentRegister;

public class registerController {

    /**
     * Kontroller for registrerings Viewen
     */
    private ComponentRegister cRegister;

    @FXML
    private TextField txtKomponent,txtNavn,txtProdusent,txtVekt,txtPris;

    @FXML
    private TextField txtLansert;

    /**
     * Setter denne listen lik listen i ComponentRegister
     * @param cRegister
     */
    public void setKomponent(ComponentRegister cRegister) {
        this.cRegister = cRegister;
    }

    /**
     * Metode der den prøver å opprette ny komponent og kaster exception ved mislykket forsøk
     */
    @FXML
    private void registerKomponent() {
        boolean temp = true;

        try {
            Component c = createComponent();
            Dialogs.showSuccessDialog(c.getNavn()+" er lagd til");
            cRegister.addComponent(c);
        } catch (NumberFormatException e) {
            Dialogs.showErrorDialog( "Pris og vekt kan ikke være tom eller inneholde bokstaver");
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
     * Oppretter ny komponent
     * @return
     */
    private Component createComponent() {

        return new Component(txtKomponent.getText(), txtNavn.getText() ,txtProdusent.getText(),Integer.parseInt(txtVekt.getText()), txtLansert.getText(),Integer.parseInt(txtPris.getText()));
    }

    private void closeWindow() {
        Stage myStage = (Stage) txtKomponent.getScene().getWindow();
        myStage.close();
    }
}
