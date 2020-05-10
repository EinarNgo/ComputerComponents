package programutvikling.controllers.controllersMain;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import programutvikling.base.Component;
import programutvikling.base.ComponentRegister;

import java.util.List;

public class userController {

    @FXML
    ChoiceBox<String> choiceCase, choiceMotherboard, choiceProsessor, choiceRam, choiceHarddisk, choicePower;
    private ComponentRegister cRegister = new ComponentRegister();

    private String summaryText = "Componentlist:";

    @FXML
    TextArea txtText;

    public userController() {

    }


    @FXML
    private void initialize() {
        fillChoicebox("Case",choiceCase);
        fillChoicebox("Prosessor",choiceProsessor);
        fillChoicebox("Motherboard",choiceMotherboard);
        fillChoicebox("Ram",choiceRam);
        fillChoicebox("Harddisk",choiceHarddisk);
        fillChoicebox("Power",choicePower);

        choiceCase.getSelectionModel().selectedIndexProperty()
                .addListener(new ChangeListener<Number>() {
                    public void changed(ObservableValue ov, Number value, Number new_value) {
                        txtText.setText(choiceCase.getItems().get(new_value.intValue()));
                        summaryText(choiceCase.getItems().get(new_value.intValue()));
                    }
                });
    }


    private void fillChoicebox(String item, ChoiceBox cBox) {
        List<Component> test = cRegister.searchRegisterByName(item);
        ObservableList<String> temp = FXCollections.observableArrayList();
        temp.add("Components");
        for (Component i : test) {
            temp.add(i.getNavn());
        }

        cBox.setItems(temp);
        cBox.setValue("Components");
    }

    private void summaryText(String text) {
        List<Component> test = cRegister.filterByNavnEksakt(text);
        for (Component i : test) {
            System.out.println(i.getNavn());
        }
    }
}
