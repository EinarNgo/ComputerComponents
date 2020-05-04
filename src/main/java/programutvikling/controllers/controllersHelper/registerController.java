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
    private TextField txtKomponent,txtProdusent,txtVekt,txtVersjon,txtPris;

    @FXML
    private TextField txtLansert;

    public void setKomponent(ComponentRegister cRegister) {
        this.cRegister = cRegister;

        /*
        txtKomponent.setText(Komponent.getKomponent());
        txtProdusent.setText(Komponent.getProdusent());
        txtVekt.setText(Integer.toString(Komponent.getVekt()));
        txtVersjon.setText(Komponent.getVersjon());
        txtLansert.setText(Komponent.getLanser());
        txtPris.setText(Integer.toString(Komponent.getPris()));

         */
    }

    @FXML
    private void registerKomponent() {
        boolean temp = true;
        /*
        Component Komponent = new Component(null,null,0,null,null,0);

        Komponent.setKomponent(txtKomponent.getText());
        Komponent.setProdusent(txtProdusent.getText());
        Komponent.setVekt(Integer.parseInt(txtVekt.getText()));
        Komponent.setVersjon(txtVersjon.getText());
        Komponent.setLansert(txtLansert.getText());
        Komponent.setPris(Integer.parseInt(txtPris.getText()));

         */
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

        return new Component(txtKomponent.getText(), txtProdusent.getText(), Integer.parseInt(txtVekt.getText()), txtVersjon.getText(), null,Integer.parseInt(txtPris.getText()));
    }

    private void closeWindow() {
        Stage myStage = (Stage) txtKomponent.getScene().getWindow();
        myStage.close();
    }
}
