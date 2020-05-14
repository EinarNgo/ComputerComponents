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
        addData(new Data("Ram","en","to","tre","fire","fem", 1));

    }

    public List<Data> getRegister() {
        return dRegister;
    }

    public void removeAll() {
        dRegister.clear();
    }

    public void removeInded(int i) {
        dRegister.remove(i);
    }

    public void addData(Data d) {
        dRegister.add(d);
    }

    public void updateDataList(TableView<Data> tblKomponent) {
        attachTableView(tblKomponent);
    }

    public void attachTableView(TableView tv) {
        tv.setItems(dRegister);
    }

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