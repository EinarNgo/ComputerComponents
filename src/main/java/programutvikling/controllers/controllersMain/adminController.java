package programutvikling.controllers.controllersMain;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import programutvikling.base.Component;
import programutvikling.base.ComponentRegister;
import programutvikling.base.RegistrerComponent;
import programutvikling.controllers.controllersHelper.FileHandler;
import programutvikling.controllers.controllersHelper.editController;
import programutvikling.controllers.controllersHelper.registerController;
import programutvikling.controllers.controllersHelper.Dialogs;

import java.io.IOException;

public class adminController {

    @FXML
    private TableView<Component> tblKomponent;
    @FXML
    private TableColumn<Component, String> ColumnKomponent;
    @FXML
    private TableColumn<Component, String> ColumnNavn;
    @FXML
    private TableColumn<Component, String> ColumnProdusent;
    @FXML
    private TableColumn<Component, String> ColumnVekt;
    @FXML
    private TableColumn<Component, String> ColumnLansert;
    @FXML
    private TableColumn<Component, String> ColumnPris;
    @FXML
    private TextField txtKomponent,txtNavn,txtProdusent,txtVekt,txtPris,txtSok;
    @FXML
    private TextField txtLansert;
    @FXML
    ChoiceBox<String> kategoriFilter;
    @FXML
    GridPane registrationBox;

    private ComponentRegister cRegister = startController.getcRegister();



    private RegistrerComponent registrerComponent;
    private Dialogs dialogs;
    private Stage stage;

    public adminController() {
        // Add some sample data.

    }

    @FXML
    private void initialize() {
        updateComponentList();
        kategoriFilter.setValue("Komponent");


        ColumnKomponent.setCellValueFactory(cellData -> cellData.getValue().komponentProperty());
        ColumnNavn.setCellValueFactory(cellData -> cellData.getValue().navnProperty());
        ColumnProdusent.setCellValueFactory(cellData -> cellData.getValue().produsentProperty());
        ColumnVekt.setCellValueFactory(cellData -> cellData.getValue().vektProperty().asString());
        ColumnLansert.setCellValueFactory(cellData -> cellData.getValue().lansertProperty());
        ColumnPris.setCellValueFactory(cellData -> cellData.getValue().prisProperty().asString());

        showKomponent(null);

        tblKomponent.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showKomponent(newValue));
    }

    @FXML
    private void slettKomponent() {
        int valgtIndex = tblKomponent.getSelectionModel().getSelectedIndex();
        if (valgtIndex >=0) {
            System.out.println(tblKomponent.getSelectionModel().getSelectedIndex());
            cRegister.removeInded(valgtIndex);
            updateComponentList();
        } else {
            dialogs.showNoSelectDialog("Please select a component in the table.");
        }
    }

    private void showKomponent(Component component) {
        if (component != null) {
            // Fill the labels with info from the person object.
            txtKomponent.setText(component.getKomponent());
            txtNavn.setText(component.getNavn());
            txtProdusent.setText(component.getProdusent());
            txtVekt.setText(Integer.toString(component.getVekt()));
            txtLansert.setText(component.getLanser());
            txtPris.setText(Integer.toString(component.getPris()));
        } else {
            txtKomponent.setText("");
            txtNavn.setText("");
            txtProdusent.setText("");
            txtVekt.setText("");
            txtLansert.setText("");
            txtPris.setText("");
        }
    }

    @FXML
    private void registrerKomponent() {
        Parent root = null;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            root = fxmlLoader.load(getClass().getResource("/programutvikling/controllers/registerKundeView.fxml").openStream());

            registerController controller = fxmlLoader.getController();

            controller.setKomponent(cRegister);

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
    private void openFile() {
        FileHandler.openFileComponent(stage, cRegister);
        txtSok.setText("");
        updateComponentList();
    }

    @FXML
    private void saveFile() {
        FileHandler.saveFileComponent(stage, cRegister);
    }

    @FXML
    private void redigerKomponent() {
        int valgtIndex = tblKomponent.getSelectionModel().getSelectedIndex();
        if (valgtIndex >=0) {
            Parent root = null;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                root = fxmlLoader.load(getClass().getResource("/programutvikling/controllers/editKundeView.fxml").openStream());

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
            dialogs.showNoSelectDialog("Please select a component in the table.");
        }
    }

    @FXML
    private void filterChoiceChanged() {
        filter();
    }

    @FXML
    private void backToMenu(ActionEvent event) throws Exception {
        Parent newparent = (Parent) FXMLLoader.load(this.getClass().getResource("/programutvikling/startView.fxml"));
        Scene newscene = new Scene(newparent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Startmenu");
        window.setScene(newscene);
        window.show();

    }

    @FXML
    private void searchTxtEntered() {
        filter();
    }

    private void updateComponentList() {
        cRegister.attachTableView(tblKomponent);
    }


    private void filter() {
        if(txtSok.getText().isBlank()) {
            updateComponentList();
            return;
        }

        ObservableList<Component> result = null;
        switch (kategoriFilter.getValue().toLowerCase()) {
            case "komponent" : result = cRegister.filterByComponent(txtSok.getText()); break;
            case "navn" : result = cRegister.filterByNavn(txtSok.getText()); break;
            case "produsent" : result = cRegister.filterByProdusent(txtSok.getText()); break;
            case "vekt" : try {
                result = cRegister.filterByVekt(Integer.parseInt(txtSok.getText()));
            } catch (NumberFormatException e) {} break; // suppress exception
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