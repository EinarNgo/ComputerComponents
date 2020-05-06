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
        Komponent.setKomponent(txtKomponent.getText());
        Komponent.setNavn(txtNavn.getText());
        Komponent.setProdusent(txtProdusent.getText());
        Komponent.setVekt(Integer.parseInt(txtVekt.getText()));
        Komponent.setLansert(txtLansert.getText());
        Komponent.setPris(Integer.parseInt(txtPris.getText()));

        closeWindow();
    }


    private void closeWindow() {
        Stage myStage = (Stage) txtKomponent.getScene().getWindow();
        myStage.close();
    }
}
