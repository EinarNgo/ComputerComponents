package programutvikling.base;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataRegister implements Serializable {

    private static final long serialVersionUID = 1;

    private transient ObservableList<Data> dRegister = FXCollections.observableArrayList();

    public DataRegister() {
        addData(new Data("Tower","B450-F","I7 7900K","16GB","1TB SSD","80W Gold", 30000));

    }

    /**
     * Metode for å hente liste
     * @return
     */
    public List<Data> getRegister() {
        return dRegister;
    }

    /**
     * Metode for å fjerne alt data fra listen
     */
    public void removeAll() {
        dRegister.clear();
    }

    /**
     * Metode for å slette valgte verdi i liste
     */
    public void removeInded(int i) {
        dRegister.remove(i);
    }

    /**
     * Metode for å legge til komponenter i registeret
     * @param d
     */
    public void addData(Data d) {
        dRegister.add(d);
    }

    public void attachTableView(TableView tv) {
        tv.setItems(dRegister);
    }

    /**
     * Metoder til å skrive og lese fil
     * ToString metoden forklarer hvordan stringen skal være når den skrives til fil
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Data d : dRegister) {
            sb.append(d.toString());
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeObject(new ArrayList<>(dRegister));
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        List<Data> list = (List<Data>) s.readObject();
        dRegister = FXCollections.observableArrayList();
        dRegister.addAll(list);
    }
}