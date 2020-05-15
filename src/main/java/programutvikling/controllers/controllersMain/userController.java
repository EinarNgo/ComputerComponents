package programutvikling.controllers.controllersMain;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import programutvikling.base.Component;
import programutvikling.base.ComponentRegister;
import programutvikling.base.Data;
import programutvikling.base.DataRegister;
import programutvikling.controllers.controllersHelper.Dialogs;
import programutvikling.controllers.controllersHelper.FileHandler;
import java.util.ArrayList;
import java.util.List;

public class userController {

    /**
     * Kontroller for userView
     */
    @FXML
    ChoiceBox<String> choiceCase, choiceMotherboard, choiceProsessor, choiceRam, choiceHarddisk, choicePower;
    private ComponentRegister cRegister = startController.getcRegister();
    private DataRegister dRegister = startController.getdRegister();

    @FXML
    private TableView<Data> tblData;
    @FXML
    private TableColumn<Data, String> ColumnaCase, ColumnMotherboard, ColumnProsessor, ColumnRam, ColumnHarddisk, ColumnPower, ColumnPris;

    private String summaryText = "", logText="";
    private int pris = 0;

    @FXML
    TextArea txtLog, txtSammendrag;
    List<Component> textArea = new ArrayList<>();
    private Stage stage;
    private Dialogs dialogs;

    public userController() {

    }


    @FXML
    private void initialize() {
        update();

    }

    /**
     * Metode for å oppdatere verdier i Choiceboks og lytter etter forandringer der
     */
    private void update() {
        updateDataList();

        summaryText = "";

        fillChoicebox("Kabinett",choiceCase);
        fillChoicebox("Prosessor",choiceProsessor);
        fillChoicebox("Hovedkort",choiceMotherboard);
        fillChoicebox("Ram",choiceRam);
        fillChoicebox("Harddisk",choiceHarddisk);
        fillChoicebox("Strømforsyning",choicePower);

        listedChange(choiceCase, "Kabinett");
        listedChange(choiceProsessor, "Prosessor");
        listedChange(choiceMotherboard, "Hovedkort");
        listedChange(choiceRam, "Ram");
        listedChange(choiceHarddisk, "Harddisk");
        listedChange(choicePower, "Strømforsyning");


        ColumnaCase.setCellValueFactory(cellData -> cellData.getValue().aCaseProperty());
        ColumnProsessor.setCellValueFactory(cellData -> cellData.getValue().prosessorProperty());
        ColumnMotherboard.setCellValueFactory(cellData -> cellData.getValue().motherboardProperty());
        ColumnRam.setCellValueFactory(cellData -> cellData.getValue().ramProperty());
        ColumnHarddisk.setCellValueFactory(cellData -> cellData.getValue().harddiskProperty());
        ColumnPower.setCellValueFactory(cellData -> cellData.getValue().powerProperty());
        ColumnPris.setCellValueFactory(cellData -> cellData.getValue().prisProperty().asString());

    }

    private void updateDataList() {
        dRegister.attachTableView(tblData);
    }


    private Data createData() {

        return new Data(choiceCase.getValue(), choiceMotherboard.getValue() ,choiceProsessor.getValue(), choiceRam.getValue(), choiceHarddisk.getValue(),choicePower.getValue(), pris);
    }

    /**
     * Metode for åpne fil og setter alle Choiceboxs til Ikke valgt
     */
    @FXML
    private void openFile() {
        FileHandler.openFileData(stage, dRegister);
        updateDataList();
        choiceCase.setValue("Ikke valgt");
        choiceProsessor.setValue("Ikke valgt");
        choiceMotherboard.setValue("Ikke valgt");
        choiceRam.setValue("Ikke valgt");
        choiceHarddisk.setValue("Ikke valgt");
        choicePower.setValue("Ikke valgt");
    }

    /**
     * Lagrer DataRegister listen til fil
     */
    @FXML
    private void saveFile() {
        FileHandler.saveFileData(stage, dRegister);
    }

    /**
     * Metode for å rende tilbake til start View
     */
    @FXML
    private void backToMenu(ActionEvent event) throws Exception {
        Parent newparent = (Parent) FXMLLoader.load(this.getClass().getResource("/programutvikling/startView.fxml"));
        Scene newscene = new Scene(newparent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Startmenu");
        window.setScene(newscene);
        window.show();
    }

    /**
     * Knapp som validerer input og legger verdiene i dRegister listen
     */
    @FXML
    private void btnAdd() {
        System.out.println(choiceCase.getValue().equals("Ikke valgt"));
        if (choiceCase.getValue().equals("Ikke valgt") || choiceProsessor.getValue().equals("Ikke valgt")
                || choiceMotherboard.getValue().equals("Ikke valgt") || choiceRam.getValue().equals("Ikke valgt")
                || choiceHarddisk.getValue().equals("Ikke valgt") || choicePower.getValue().equals("Ikke valgt")) {
            dialogs.showErrorDialog("Vennligst velg alle komponentene.");
        } else {
            Data d = createData();
            dRegister.addData(d);
            System.out.println(choiceCase.getValue());
            choiceCase.setValue("Ikke valgt");
            choiceProsessor.setValue("Ikke valgt");
            choiceMotherboard.setValue("Ikke valgt");
            choiceRam.setValue("Ikke valgt");
            choiceHarddisk.setValue("Ikke valgt");
            choicePower.setValue("Ikke valgt");
        }
    }

    /**
     * Sletter den valgte verdien i tabellen og i listen
     */
    @FXML
    private void btnSlett() {
        int valgtIndex = tblData.getSelectionModel().getSelectedIndex();
        if (valgtIndex >=0) {
            System.out.println(tblData.getSelectionModel().getSelectedIndex());
            dRegister.removeInded(valgtIndex);
        } else {
            dialogs.showNoSelectDialog("Vennligst velg en komponent i tabellen");
        }
    }

    /**
     * Metode for å lytte etter forandring i Choiceboxes
     * @param cBox
     * @param text
     */
    private void listedChange(ChoiceBox<String> cBox, String text) {
        cBox.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                                 public void changed(ObservableValue ov, Number value, Number new_value) {
                                     String temp = cBox.getItems().get(new_value.intValue());

                                     List<Component> test = cRegister.filterByKomponentEksakt(text);
                                     for (Component i : test) {
                                         if (i.getNavn().equals(temp)) {
                                             textArea.add(i);
                                         } else {
                                             textArea.remove(i);
                                         }
                                     }

                                     summaryText = "Din pc: ";
                                     logText = "";
                                     pris = 0;
                                     for (Component i : textArea) {
                                         logText +=
                                                 "Valgt komponent: " + i.getNavn() +
                                                         "\nProdusert: " + i.getProdusent() +
                                                         "\nVekt: " + i.getVekt() +
                                                         "\nLansert: " + i.getLanser() +
                                                         "\nPris: " + i.getPris() + "\n\n";
                                         summaryText += "\n"+i.getKomponent() + ": " + i.getNavn();
                                         pris += i.getPris();
                                     }

                                     summaryText += "\nTotalpris: " + pris + " kr";

                                     txtSammendrag.setText(summaryText);
                                     txtLog.setText(logText);
                                 }
                             }
                );
    }


    /**
     * Metode som henter verdier fra Componentregister og legger den sortert i choiceboxes
     * @param item
     * @param cBox
     */
    private void fillChoicebox(String item, ChoiceBox cBox) {
        List<Component> test = cRegister.searchRegisterByName(item);
        ObservableList<String> temp = FXCollections.observableArrayList();
        temp.add("Ikke valgt");
        for (Component i : test) {
            temp.add(i.getNavn());
        }

        cBox.setItems(temp);
        cBox.setValue("Ikke valgt");
    }


}
