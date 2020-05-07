package programutvikling.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import programutvikling.base.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ComponentRegister implements Serializable {
    private static final long serialVersionUID = 1;

    private transient ObservableList<Component> cRegister = FXCollections.observableArrayList();

    public ComponentRegister() {
        addComponent(new Component("Case", "1", "Asus",50,"20.10.2018",8000));
        addComponent(new Component("Motherboard", "2","Kingston",400,"2.2.2010",2000));
        addComponent(new Component("Prosessor", "3","Rex",100,"3.3.2003",500));
        addComponent(new Component("Ram", "4", "Asus",50,"20.10.2018",8000));
        addComponent(new Component("Harddisk", "5","Kingston",400,"2.2.2010",2000));
        addComponent(new Component("Power", "6","Rex",100,"3.3.2003",500));
        addComponent(new Component("Case", "7", "Asus",50,"20.10.2018",8000));
        addComponent(new Component("Motherboard", "8","Kingston",400,"2.2.2010",2000));
        addComponent(new Component("Prosessor", "9","Rex",100,"3.3.2003",500));
        addComponent(new Component("Ram", "10", "Asus",50,"20.10.2018",8000));
        addComponent(new Component("Harddisk", "11","Kingston",400,"2.2.2010",2000));
        addComponent(new Component("Power", "12","Rex",100,"3.3.2003",500));

    }

    public List<Component> getRegister() {
        return cRegister;
    }

    public void addComponent(Component c) {
        cRegister.add(c);
    }

    public List<Component> searchRegisterByName(String name) {
        List<Component> components = new ArrayList<>();
        Pattern pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);

        for(Component p : cRegister) {
            Matcher matcher = pattern.matcher(p.getKomponent());

            if(matcher.find()) {
                components.add(p);
            }
        }

        // Hvis vi kommer hit betyr det at vi ikke fant noen personer
        return components;
    }

    public ObservableList<Component> filterByNavnEksakt(String navn) {
        return cRegister.stream().
                filter(p -> p.getNavn().
                        matches(String.format(navn))).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public void attachTableView(TableView tv) {
        tv.setItems(cRegister);
    }

    public ObservableList<Component> filterByComponenttest(String component) {
        return cRegister.stream().
                filter(p -> p.getKomponent().toLowerCase().
                        matches(String.format("%s%s%s",".*", component.toLowerCase(), ".*"))).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Component> filterByComponent(String component) {
        return cRegister.stream().
                filter(p -> p.getKomponent().toLowerCase().
                        matches(String.format("%s%s%s",".*", component.toLowerCase(), ".*"))).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    public ObservableList<Component> filterByNavn(String navn) {
        return cRegister.stream().
                filter(p -> p.getNavn().
                        matches(String.format("%s%s%s",".*", navn, ".*"))).
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
