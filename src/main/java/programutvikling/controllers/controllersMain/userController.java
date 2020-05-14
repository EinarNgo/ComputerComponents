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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import programutvikling.base.Component;
import programutvikling.base.ComponentRegister;
import programutvikling.base.Data;
import programutvikling.base.DataRegister;

import java.util.ArrayList;
import java.util.List;

public class userController {

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

    public userController() {

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize() {
        update();

    }

    private void update() {
        updateDataList();

        summaryText = "";

        fillChoicebox("Case",choiceCase);
        fillChoicebox("Prosessor",choiceProsessor);
        fillChoicebox("Motherboard",choiceMotherboard);
        fillChoicebox("Ram",choiceRam);
        fillChoicebox("Harddisk",choiceHarddisk);
        fillChoicebox("Power",choicePower);

        listedChange(choiceCase, "Case");
        listedChange(choiceProsessor, "Prosessor");
        listedChange(choiceMotherboard, "Motherboard");
        listedChange(choiceRam, "Ram");
        listedChange(choiceHarddisk, "Harddisk");
        listedChange(choicePower, "Power");


        ColumnaCase.setCellValueFactory(cellData -> cellData.getValue().aCaseProperty());
        ColumnProsessor.setCellValueFactory(cellData -> cellData.getValue().prosessorProperty());
        ColumnMotherboard.setCellValueFactory(cellData -> cellData.getValue().motherboardProperty());
        ColumnRam.setCellValueFactory(cellData -> cellData.getValue().ramProperty());
        ColumnHarddisk.setCellValueFactory(cellData -> cellData.getValue().harddiskProperty());
        ColumnPower.setCellValueFactory(cellData -> cellData.getValue().powerProperty());
        ColumnPris.setCellValueFactory(cellData -> cellData.getValue().prisProperty().asString());

        showData(null);

        tblData.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showData(newValue));

    }

    private void updateDataList() {
        dRegister.attachTableView(tblData);
    }


    private Data createData() {

        return new Data(choiceCase.getValue(), choiceMotherboard.getValue() ,choiceProsessor.getValue(), choiceRam.getValue(), choiceHarddisk.getValue(),choicePower.getValue(), pris);
    }



    private void showData(Data data) {
        if (data != null) {
            // Fill the labels with info from the person object.
            /*
            txtKomponent.setText(component.getKomponent());
            txtNavn.setText(component.getNavn());
            txtProdusent.setText(component.getProdusent());
            txtVekt.setText(Integer.toString(component.getVekt()));
            txtLansert.setText(component.getLanser());
            txtPris.setText(Integer.toString(component.getPris()));

             */
        } else {
            /*
            txtKomponent.setText("");
            txtNavn.setText("");
            txtProdusent.setText("");
            txtVekt.setText("");
            txtLansert.setText("");
            txtPris.setText("");

             */
        }
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
    private void btnAdd() {
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

                                     summaryText += "\nTotalpris: " + pris;

                                     txtSammendrag.setText(summaryText);
                                     txtLog.setText(logText);
                                 }
                             }
                );
    }


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
