package programutvikling.base;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Component implements Serializable {

    private static final long serialVersionUID = 1;

    public final StringProperty komponent;
    public final StringProperty produsent;
    public final IntegerProperty vekt;
    public final StringProperty versjon;
    public final StringProperty lansert;
    public final IntegerProperty pris;



    public Component(String komponent, String produsent, int vekt, String versjon, String lanser , int pris){
        this.komponent = new SimpleStringProperty(komponent);
        this.produsent = new SimpleStringProperty(produsent);
        this.vekt = new SimpleIntegerProperty(vekt);
        this.versjon = new SimpleStringProperty(versjon);
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
        this.komponent.set(komponent);
    }

    public String getProdusent() {
        return produsent.get();
    }

    public StringProperty produsentProperty() {
        return produsent;
    }

    public void setProdusent(String produsent) {
        this.produsent.set(produsent);
    }

    public int getVekt() {
        return vekt.get();
    }

    public IntegerProperty vektProperty() {
        return vekt;
    }

    public void setVekt(int vekt) {
        this.vekt.set(vekt);
    }

    public String getVersjon() {
        return versjon.get();
    }

    public StringProperty versjonProperty() {
        return versjon;
    }

    public void setVersjon(String versjon) {
        this.versjon.set(versjon);
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
        this.pris.set(pris);
    }
}
