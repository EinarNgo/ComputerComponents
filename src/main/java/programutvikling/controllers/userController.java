package programutvikling.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import programutvikling.base.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class userController {

    @FXML
    ChoiceBox<String> choiceCase, choiceMotherboard, choiceProsessor, choiceRam, choiceHarddisk, choicePower;
    private ComponentRegister cRegister = new ComponentRegister();

    public userController() {
        System.out.println(cRegister.getRegister().size());
    }
    @FXML
    private void initialize() {
        fillChoicebox("Case",choiceCase);
        fillChoicebox("Prosessor",choiceProsessor);
        fillChoicebox("Motherboard",choiceMotherboard);
        fillChoicebox("Ram",choiceRam);
        fillChoicebox("Harddisk",choiceHarddisk);
        fillChoicebox("Power",choicePower);
    }

    private void fillChoicebox(String item, ChoiceBox cBox) {
        cBox.setValue("Empty");
        List<Component> test = cRegister.searchRegisterByName(item);
        ObservableList<String> temp = FXCollections.observableArrayList();
        temp.add("Components");
        for (Component i : test) {
            System.out.println(i.getNavn());
            temp.add(i.getNavn());
        }
        cBox.setItems(temp);
        cBox.setValue("Components");
    }

    private void filter() {
        ObservableList<Component> result = null;
        result = cRegister.filterByComponenttest("Kabinet");

    }
    
}
