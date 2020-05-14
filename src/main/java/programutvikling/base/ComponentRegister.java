package programutvikling.base;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import programutvikling.base.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

        addComponent(new Component("Case", "en", "Asus",50,"20-10-3008",8000));
        addComponent(new Component("Motherboard", "to","Kingston",400,"22-02-2010",2000));
        addComponent(new Component("Prosessor", "tre","Rex",100,"03-03-2003",500));
        addComponent(new Component("Ram", "fire", "Asus",50,"20-10-2018",8000));
        addComponent(new Component("Harddisk", "fem","Kingston",400,"02-02-2010",2000));
        addComponent(new Component("Power", "seks","Rex",100,"30-03-2003",500));
        addComponent(new Component("Case", "syv", "Asus",50,"20-10-2018",8000));
        addComponent(new Component("Motherboard", "åtte","Kingston",400,"20-02-2010",2000));
        addComponent(new Component("Prosessor", "ni","Rex",100,"30-03-2003",500));
        addComponent(new Component("Ram", "ti", "Asus",50,"20-10-2018",8000));
        addComponent(new Component("Harddisk", "elleve","Kingston",400,"20-02-2010",2000));
        addComponent(new Component("Power", "tolv","Rex",100,"20-02-2003",500));


    }

    public List<Component> getRegister() {
        return cRegister;
    }

    public void removeAll() {
        cRegister.clear();
    }

    public void removeInded(int i) {
        cRegister.remove(i);
    }

    public void addComponent(Component c) {
        cRegister.add(c);
    }

    public void updateComponentList(TableView<Component> tblKomponent) {
        attachTableView(tblKomponent);
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

    public List<Component> filterByKomponentEksakt(String name) {
        List<Component> components = new ArrayList<>();
        Pattern pattern = Pattern.compile(name, Pattern.CASE_INSENSITIVE);

        for(Component p : cRegister) {
            Matcher matcher = pattern.matcher(p.getKomponent());

            if(matcher.find()) {
                components.add(p);
            }
        }

        return components;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Component c : cRegister) {
            sb.append(c.toString());
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeObject(new ArrayList<>(cRegister));
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        List<Component> list = (List<Component>) s.readObject();
        cRegister = FXCollections.observableArrayList();
        cRegister.addAll(list);
    }

}
