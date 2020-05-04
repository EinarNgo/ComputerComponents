package programutvikling.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import programutvikling.base.Component;
import programutvikling.controllers.controllersHelper.editController;
import programutvikling.controllers.controllersHelper.registerController;

import java.io.IOException;

public class adminController {

    @FXML
    private TableView<Component> tblKomponent;
    @FXML
    private TableColumn<Component, String> ColumnKomponent;
    @FXML
    private TableColumn<Component, String> ColumnProdusent;
    @FXML
    private TableColumn<Component, String> ColumnVekt;
    @FXML
    private TableColumn<Component, String> ColumnVersjon;
    @FXML
    private TableColumn<Component, String> ColumnLansert;
    @FXML
    private TableColumn<Component, String> ColumnPris;
    @FXML
    private TextField txtKomponent,txtProdusent,txtVekt,txtVersjon,txtPris,txtSok;
    @FXML
    private TextField txtLansert;
    @FXML
    ChoiceBox<String> kategoriFilter;
    @FXML
    GridPane registrationBox;

    //private ObservableList<Component> cData = FXCollections.observableArrayList();

    private ComponentRegister cRegister = new ComponentRegister();
    private RegistrerComponent registrerComponent;


    public adminController() {
        // Add some sample data.


        cRegister.addComponent(new Component("Prossesor","Asus",50,"10.10","20.10.2018",8000));
        cRegister.addComponent(new Component("Harddisk","Kingston",400,"12.2","2.2.2010",2000));
        cRegister.addComponent(new Component("Ram","Rex",100,"12.23","3.3.2003",500));

    }

    @FXML
    private void initialize() {
        updateComponentList();
        kategoriFilter.setValue("Komponent");


        ColumnKomponent.setCellValueFactory(cellData -> cellData.getValue().komponentProperty());
        ColumnProdusent.setCellValueFactory(cellData -> cellData.getValue().produsentProperty());
        ColumnVekt.setCellValueFactory(cellData -> cellData.getValue().vektProperty().asString());
        ColumnVersjon.setCellValueFactory(cellData -> cellData.getValue().versjonProperty());
        ColumnLansert.setCellValueFactory(cellData -> cellData.getValue().lansertProperty());
        ColumnPris.setCellValueFactory(cellData -> cellData.getValue().prisProperty().asString());

        showKomponent(null);

        // Listen for selection changes and show the person details when changed.
        tblKomponent.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showKomponent(newValue));

        //tblKomponent.setItems(cRegister);
    }

    private void updateComponentList() {
        cRegister.attachTableView(tblKomponent);
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

    private void showKomponent(Component component) {
        if (component != null) {
            // Fill the labels with info from the person object.
            txtKomponent.setText(component.getKomponent());
            txtProdusent.setText(component.getProdusent());
            txtVekt.setText(Integer.toString(component.getVekt()));
            txtVersjon.setText(component.getVersjon());
            txtLansert.setText(component.getLanser());
            txtPris.setText(Integer.toString(component.getPris()));
        } else {
            txtKomponent.setText("");
            txtProdusent.setText("");
            txtVekt.setText("");
            txtVersjon.setText("");
            txtLansert.setText("");
            txtPris.setText("");
        }
    }

    @FXML
    private void registrerKomponent() {
        Parent root = null;
        Component tempKomponent;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            root = fxmlLoader.load(getClass().getResource("/programutvikling/controllers/registerKundeScene.fxml").openStream());

            registerController controller = fxmlLoader.getController();
            tempKomponent = new Component(null,null,0,null,null,0);

            controller.setKomponent(tempKomponent);

            if(tempKomponent != null) {
                cRegister.addComponent(tempKomponent);
            }

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

    @FXML
    private void filterChoiceChanged() {
        filter();
    }

    @FXML
    private void searchTxtEntered() {
        filter();
    }

    private void updatePersonList() {
        cRegister.attachTableView(tblKomponent);
    }

    private void filter() {
        if(txtSok.getText().isBlank()) {
            updatePersonList();
            return;
        }

        ObservableList<Component> result = null;
        switch (kategoriFilter.getValue().toLowerCase()) {
            case "komponent" : result = cRegister.filterByComponent(txtSok.getText()); break;
            case "produsent" : result = cRegister.filterByProdusent(txtSok.getText()); break;
            case "vekt" : try {
                result = cRegister.filterByVekt(Integer.parseInt(txtSok.getText()));
            } catch (NumberFormatException e) {} break; // suppress exception
            case "versjon" : result = cRegister.filterByVersjon(txtSok.getText()); break;
            case "lansert" : result = cRegister.filterByLansert(txtSok.getText()); break;
            case "pris" : try {
                result = cRegister.filterByPris(Integer.parseInt(txtSok.getText()));
            } catch (NumberFormatException e) {} break; // suppress exception


        }

        if(result == null) {
            tblKomponent.setItems(FXCollections.observableArrayList());
        } else {
            tblKomponent.setItems(result);
        }
    }
}