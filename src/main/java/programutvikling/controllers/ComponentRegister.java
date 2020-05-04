package programutvikling.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import programutvikling.base.Component;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ComponentRegister implements Serializable {
    private static final long serialVersionUID = 1;

    private transient ObservableList<Component> cRegister = FXCollections.observableArrayList();

    public List<Component> getRegister() {
        return cRegister;
    }

    public void addComponent(Component c) {
        cRegister.add(c);
    }

    public void removeAll() {
        cRegister.clear();
    }

    public void attachTableView(TableView tv) {
        tv.setItems(cRegister);
    }


    public ObservableList<Component> filterByComponent(String component) {
        return cRegister.stream().
                filter(p -> p.getKomponent().toLowerCase().
                        matches(String.format("%s%s%s",".*", component.toLowerCase(), ".*"))).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    public ObservableList<Component> filterByProdusent(String produsent) {
        return cRegister.stream().
                filter(p -> p.getProdusent().
                        matches(String.format("%s%s%s",".*", produsent, ".*"))).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    public ObservableList<Component> filterByVekt(int vekt) {
        return cRegister.stream().
                filter(p -> p.getVekt() == vekt).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    public ObservableList<Component> filterByVersjon(String versjon) {
        return cRegister.stream().
                filter(p -> p.getVersjon().
                        matches(String.format("%s%s%s",".*", versjon, ".*"))).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    public ObservableList<Component> filterByLansert(String lansert) {
        return cRegister.stream().
                filter(p -> p.getLanser().
                        matches(String.format("%s%s%s",".*", lansert, ".*"))).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    public ObservableList<Component> filterByPris(int pris) {
        return cRegister.stream().
                filter(p -> p.getPris() == pris).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

}
