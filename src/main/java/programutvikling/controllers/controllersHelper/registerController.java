package programutvikling.controllers.controllersHelper;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import programutvikling.base.Component;
import programutvikling.controllers.ComponentRegister;

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

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(txtKomponent.getScene().getWindow());
        alert.setTitle("Wrong input");
        alert.setHeaderText("Please put right information");
        try {
            Component c = createComponent();
            //resetFields();
            //Dialogs.showSuccessDialog(p.getName());
            cRegister.addComponent(c);
        } catch (NumberFormatException nfe) {

            alert.setContentText("Please only number in Vekt and Pris");

            temp = false;
            alert.showAndWait();
        } catch (IllegalArgumentException iae) {

            alert.setContentText("Please select a component in the table.");

            temp = false;
            alert.showAndWait();
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
