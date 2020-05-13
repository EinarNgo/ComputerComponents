package programutvikling.base;

import javafx.beans.property.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Component implements Serializable {

    private static final long serialVersionUID = 1;

    private transient SimpleStringProperty komponent;
    private transient SimpleStringProperty navn;
    private transient SimpleStringProperty produsent;
    private transient SimpleIntegerProperty vekt;
    private transient SimpleStringProperty lansert;
    private transient SimpleIntegerProperty pris;

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
        return komponent.getValue();
    }

    public StringProperty komponentProperty() {
        return komponent;
    }

    public final void setKomponent(String komponent) {
        ComponentValidator.komponentInput(komponent);
        this.komponent.set(komponent);
    }

    public String getNavn() {
        return navn.getValue();
    }

    public StringProperty navnProperty() {
        return navn;
    }

    public final void setNavn(String navn) {
        ComponentValidator.navnInput(navn);
        this.navn.set(navn);
    }

    public String getProdusent() {
        return produsent.getValue();
    }

    public StringProperty produsentProperty() {
        return produsent;
    }

    public final void setProdusent(String produsent) {
        ComponentValidator.produsentInput(produsent);
        this.produsent.set(produsent);
    }

    public int getVekt() {
        return vekt.getValue();
    }

    public IntegerProperty vektProperty() {
        return vekt;
    }

    public final void setVekt(int vekt) {
        ComponentValidator.vektInput(Integer.toString(vekt));
        this.vekt.set(vekt);
    }

    public String getLanser() {
        return lansert.getValue();
    }

    public StringProperty lansertProperty() {
        return lansert;
    }

    public final void setLansert(String lansert) {
        this.lansert.set(lansert);
    }

    public int getPris() {
        return pris.getValue();
    }

    public IntegerProperty prisProperty() {
        return pris;
    }

    public final void setPris(int pris) {
        ComponentValidator.prisInput(Integer.toString(pris));
        this.pris.set(pris);
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s;%s;%s",
                komponent.getValue(), navn.getValue(), produsent.getValue(),
                vekt.getValue(), lansert.getValue(),pris.getValue());
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeUTF(getKomponent());
        s.writeUTF(getNavn());
        s.writeUTF(getProdusent());
        s.writeUTF(getLanser());
        s.writeInt(getVekt());
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
        this.lansert = new SimpleStringProperty(lansert);
        this.vekt = new SimpleIntegerProperty(vekt);
        this.pris = new SimpleIntegerProperty(pris);

        setKomponent(komponent);
        setNavn(navn);
        setProdusent(produsent);
        setLansert(lansert);
        setVekt(vekt);
        setPris(pris);
    }
}
