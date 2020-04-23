package programutvikling.controllers.controllersHelper;

import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import programutvikling.base.Component;

public class editController {

    private Component Komponent;

    @FXML
    private TextField txtKomponent,txtProdusent,txtVekt,txtVersjon,txtPris;

    @FXML
    private DatePicker txtLansert;


    public void setKomponent(Component Komponent) {
        this.Komponent = Komponent;

        txtKomponent.setText(Komponent.getKomponent());
        txtProdusent.setText(Komponent.getProdusent());
        //txtVekt.setText(Integer.parseInt(Komponent.getVekt()));
        txtVersjon.setText(Komponent.getVersjon());
        //txtPris.setText(Komponent.getPris());
    }


    @FXML
    private void editKomponent() {
        Komponent.setKomponent(txtKomponent.getText());
        Komponent.setProdusent(txtProdusent.getText());
        Komponent.setVekt(Integer.parseInt(txtVekt.getText()));
        Komponent.setVersjon(txtVersjon.getText());
        Komponent.setPris(Integer.parseInt(txtPris.getText()));

        closeWindow();
    }


    private void closeWindow() {
        Stage myStage = (Stage) txtKomponent.getScene().getWindow();
        myStage.close();
    }
}
