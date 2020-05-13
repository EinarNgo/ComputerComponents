package programutvikling.base;

import javafx.beans.property.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Component implements Serializable {

    private static final long serialVersionUID = 1;

    private transient StringProperty komponent;
    private transient StringProperty navn;
    private transient StringProperty produsent;
    private transient IntegerProperty vekt;
    private transient StringProperty lansert;
    private transient IntegerProperty pris;

    public Component(String komponent, String navn,String produsent, int vekt, String lanser, int pris){

        ComponentValidator.navnInput(navn);
        ComponentValidator.komponentInput(komponent);
        ComponentValidator.produsentInput(produsent);
        ComponentValidator.vektInput(Integer.toString(vekt));
        ComponentValidator.prisInput(Integer.toString(pris));

        this.komponent = new SimpleStringProperty(komponent);
        this.navn = new SimpleStringProperty(navn);
        this.produsent = new SimpleStringProperty(produsent);
        this.vekt = new SimpleIntegerProperty(vekt);
        this.lansert = new SimpleStringProperty(lanser);
        this.pris = new SimpleIntegerProperty(pris);
    }

    public String getKomponent() {
        return komponent.get();
    }

    public StringProperty komponentProperty() {
        return komponent;
    }

    public void setKomponent(String komponent) {
        ComponentValidator.komponentInput(komponent);
        this.komponent.set(komponent);
    }

    public String getNavn() {
        return navn.get();
    }

    public StringProperty navnProperty() {
        return navn;
    }

    public void setNavn(String navn) {
        ComponentValidator.navnInput(navn);
        this.navn.set(navn);
    }

    public String getProdusent() {
        return produsent.get();
    }

    public StringProperty produsentProperty() {
        return produsent;
    }

    public void setProdusent(String produsent) {
        ComponentValidator.produsentInput(produsent);
        this.produsent.set(produsent);
    }

    public int getVekt() {
        return vekt.get();
    }

    public IntegerProperty vektProperty() {
        return vekt;
    }

    public void setVekt(int vekt) {
        ComponentValidator.vektInput(Integer.toString(vekt));
        this.vekt.set(vekt);
    }

    public String getLanser() {
        return lansert.get();
    }

    public StringProperty lansertProperty() {
        return lansert;
    }

    public void setLansert(String lansert) {
        this.lansert.set(lansert);
    }

    public int getPris() {
        return pris.get();
    }

    public IntegerProperty prisProperty() {
        return pris;
    }

    public void setPris(int pris) {
        ComponentValidator.prisInput(Integer.toString(pris));
        this.pris.set(pris);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeUTF(getKomponent());
        s.writeUTF(getNavn());
        s.writeUTF(getProdusent());
        s.writeInt(getVekt());
        s.writeUTF(getLanser());
        s.writeInt(getPris());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        String komponent = s.readUTF();
        String navn = s.readUTF();
        String produsent = s.readUTF();
        String lansert = s.readUTF();
        int vekt = s.readInt();
        int pris = s.readInt();

        this.komponent = new SimpleStringProperty(komponent);
        this.navn = new SimpleStringProperty(navn);
        this.produsent = new SimpleStringProperty(produsent);
        this.vekt = new SimpleIntegerProperty(vekt);
        this.lansert = new SimpleStringProperty(lansert);
        this.pris = new SimpleIntegerProperty(pris);

        setKomponent(komponent);
        setNavn(navn);
        setProdusent(produsent);
        setVekt(vekt);
        setLansert(lansert);
        setPris(pris);
    }
}
