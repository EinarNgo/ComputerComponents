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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import programutvikling.base.Component;
import programutvikling.base.ComponentRegister;

import java.util.ArrayList;
import java.util.List;

public class userController {

    @FXML
    ChoiceBox<String> choiceCase, choiceMotherboard, choiceProsessor, choiceRam, choiceHarddisk, choicePower;
    private ComponentRegister cRegister = new ComponentRegister();


    private String summaryText = "", logText="";

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
    }

    @FXML
    private void backToMenu(ActionEvent event) throws Exception {
        Parent newparent = (Parent) FXMLLoader.load(this.getClass().getResource("/programutvikling/startView.fxml"));
        Scene newscene = new Scene(newparent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Adminpanel");
        window.setScene(newscene);
        window.show();
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
                                     int pris = 0;
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
