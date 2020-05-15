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
    /**
     * Klasse for å håndtere alle komponentene som kommer inn og legger den i liste
     */

    private static final long serialVersionUID = 1;

    /**
     * Oppretter en List med Komponenter som klasse
     */
    private transient ObservableList<Component> cRegister = FXCollections.observableArrayList();

    public ComponentRegister() {

        /**
         * Oppretter eksempel data
         */
        addComponent(new Component("Kabinett", "NXT", "Asus",50,"20-10-2008",8000));
        addComponent(new Component("Hovedkort", "MH42EX","Kingston",400,"22-02-2010",2000));
        addComponent(new Component("Prosessor", "I7 7700K","Intel",100,"03-03-2003",500));
        addComponent(new Component("Ram", "16GB DDR5", "Corsair",50,"20-10-2018",8000));
        addComponent(new Component("Harddisk", "SSD 512GB","Samsung",400,"02-02-2010",2000));
        addComponent(new Component("Strømforsyning", "Bronze 80W","EVGA",100,"30-03-2003",500));
        addComponent(new Component("Kabinett", "Tower", "Corsair",50,"20-10-2018",8000));
        addComponent(new Component("Hovedkort", "B450-F","Asus",400,"20-02-2010",2000));
        addComponent(new Component("Prosessor", "Ryzen 3600X","AMD",100,"30-03-2003",500));
        addComponent(new Component("Ram", "4GB DDR3", "Corsair",50,"20-10-2018",8000));
        addComponent(new Component("Harddisk", "1TB HDD","Seagate",400,"20-02-2010",2000));
        addComponent(new Component("Strømforsyning", "120W Gold","Corsair",100,"20-02-2003",500));


    }

    /**
     * Metode for å hente liste
     * @return
     */
    public List<Component> getRegister() {
        return cRegister;
    }

    /**
     * Metode for å fjerne alt data fra listen
     */
    public void removeAll() {
        cRegister.clear();
    }

    /**
     * Metode for å slette valgte verdi i liste
     * @param i
     */
    public void removeInded(int i) {
        cRegister.remove(i);
    }

    /**
     * Metode for å legge til komponenter i registeret
     * @param c
     */
    public void addComponent(Component c) {
        cRegister.add(c);
    }

    public void updateComponentList(TableView<Component> tblKomponent) {
        attachTableView(tblKomponent);
    }

    /**
     * Metode for å finne den valgt komponent i listen med navn
     * @param name
     * @return
     */
    public List<Component> searchRegisterByName(String name) {
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

    /**
     * Filtrering for de ulike kolonene
     */
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

    /**
     * Metoder til å skrive og lese fil
     * ToString metoden forklarer hvordan stringen skal være når den skrives til fil
     */
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
