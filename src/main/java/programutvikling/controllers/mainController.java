package programutvikling.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import programutvikling.base.Component;
import programutvikling.controllers.controllersHelper.editController;
import programutvikling.controllers.controllersHelper.registerController;

import java.io.IOException;

public class mainController {

    @FXML
    private TableView<Component> tblKomponent;
    @FXML
    private TableColumn<Component, String> ColumnKomponent;
    @FXML
    private TableColumn<Component, String> ColumnProdusent;
    @FXML
    private TextField txtKomponent,txtProdusent,txtVekt,txtVersjon,txtPris;
    @FXML
    private DatePicker txtLansert;

    private ObservableList<Component> cData = FXCollections.observableArrayList();


    public mainController() {
        // Add some sample data.

        cData.add(new Component("Prossesor","Asus","50g","10.10","20.10.2018",8000));
        cData.add(new Component("Harddisk","Kingston","400g","12.2","2.2.2010",2000));
        cData.add(new Component("Ram","Rex","100g","12.23","3.3.2003",500));
    }

    @FXML
    private void initialize() {
        update();
    }

    private void showKomponent(Component component) {
        if (component != null) {
            // Fill the labels with info from the person object.
            txtKomponent.setText(component.getKomponent());
            txtProdusent.setText(component.getProdusent());
            txtVekt.setText(component.getVekt());
            txtVersjon.setText(component.getVersjon());
        } else {
            // Person is null, remove all the text.
            txtKomponent.setText("");
            txtProdusent.setText("");
            txtVekt.setText("");
            txtVersjon.setText("");
        }
    }

    @FXML
    private void slettKomponent() {
        int valgtIndex = tblKomponent.getSelectionModel().getSelectedIndex();
        if (valgtIndex >=0) {
            tblKomponent.getItems().remove(valgtIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(txtKomponent.getScene().getWindow());
            alert.setTitle("No Selection");
            alert.setHeaderText("No component Selected");
            alert.setContentText("Please select a component in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void registrerKomponent() {
        launchRegistrerKomponentScene();
    }

    private void launchRegistrerKomponentScene() {
        Parent root = null;
        Component tempKomponent = new Component();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            root = fxmlLoader.load(getClass().getResource("/programutvikling/controllers/registerKundeScene.fxml").openStream());


            registerController controller = fxmlLoader.getController();
            controller.setKomponent(tempKomponent);

            cData.add(tempKomponent);

        } catch (IOException e) {
            e.printStackTrace(); // FXML document should be available
            return;
        }

        Scene scene = new Scene(root);
        // add CSS etc. should be here
        Stage editKomponentStage = new Stage();
        editKomponentStage.setTitle("Registrer komponent");
        editKomponentStage.setScene(scene);

        editKomponentStage.initOwner(txtKomponent.getScene().getWindow());
        editKomponentStage.initModality(Modality.WINDOW_MODAL);
        editKomponentStage.show();
    }

    @FXML
    private void redigerKomponent() {
        launchRedigerKomponentScene();
    }

    private void launchRedigerKomponentScene() {
        int valgtIndex = tblKomponent.getSelectionModel().getSelectedIndex();
        if (valgtIndex >=0) {
            Parent root = null;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                root = fxmlLoader.load(getClass().getResource("/programutvikling/controllers/editKundeScene.fxml").openStream());

                // Får tak i controlleren og overfører referanse til person-objektet
                editController controller = fxmlLoader.getController();
                controller.setKomponent(tblKomponent.getSelectionModel().getSelectedItem());

            } catch (IOException e) {
                e.printStackTrace(); // FXML document should be available
                return;
            }

            Scene scene = new Scene(root);
            Stage editKomponentStage = new Stage();
            editKomponentStage.setTitle("Endre person");
            editKomponentStage.setScene(scene);

            editKomponentStage.initOwner(txtKomponent.getScene().getWindow());
            editKomponentStage.initModality(Modality.WINDOW_MODAL);
            editKomponentStage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(txtKomponent.getScene().getWindow());
            alert.setTitle("No Selection");
            alert.setHeaderText("No component Selected");
            alert.setContentText("Please select a component in the table.");

            alert.showAndWait();
        }
    }

    public void update() {

        ColumnKomponent.setCellValueFactory(cellData -> cellData.getValue().komponentProperty());
        ColumnProdusent.setCellValueFactory(cellData -> cellData.getValue().produsentProperty());
        // Clear person details.
        showKomponent(null);

        // Listen for selection changes and show the person details when changed.
        tblKomponent.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showKomponent(newValue));

        tblKomponent.setItems(cData);
    }
}
