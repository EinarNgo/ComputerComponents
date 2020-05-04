package programutvikling.controllers.controllersHelper;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import programutvikling.base.Component;

public class registerController {

    private Component Komponent;

    @FXML
    private TextField txtKomponent,txtProdusent,txtVekt,txtVersjon,txtPris;

    @FXML
    private TextField txtLansert;

    public void setKomponent(Component Komponent) {
        this.Komponent = Komponent;

        txtKomponent.setText(Komponent.getKomponent());
        txtProdusent.setText(Komponent.getProdusent());
        txtVekt.setText(Integer.toString(Komponent.getVekt()));
        txtVersjon.setText(Komponent.getVersjon());
        txtLansert.setText(Komponent.getLanser());
        txtPris.setText(Integer.toString(Komponent.getPris()));
    }

    @FXML
    private void registerKomponent() {

        Komponent.setKomponent(txtKomponent.getText());
        Komponent.setProdusent(txtProdusent.getText());
        Komponent.setVekt(Integer.parseInt(txtVekt.getText()));
        Komponent.setVersjon(txtVersjon.getText());
        Komponent.setLansert(txtLansert.getText());
        Komponent.setPris(Integer.parseInt(txtPris.getText()));

        closeWindow();

    }

    private void closeWindow() {
        Stage myStage = (Stage) txtKomponent.getScene().getWindow();
        myStage.close();
    }
}
