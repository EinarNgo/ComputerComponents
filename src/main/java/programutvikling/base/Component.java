package programutvikling.base;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Component {
    public final StringProperty komponent;
    public final StringProperty produsent;
    public final StringProperty vekt;
    public final StringProperty versjon;
    public final StringProperty lansert;
    public final IntegerProperty pris;

    public Component() {
        this(null,null,null,null,null,0);
    }

    public Component(String komponent, String produsent, String vekt, String versjon, String lanser , int pris){
        this.komponent = new SimpleStringProperty(komponent);
        this.produsent = new SimpleStringProperty(produsent);
        this.vekt = new SimpleStringProperty(vekt);
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

    public String getVekt() {
        return vekt.get();
    }

    public StringProperty vektProperty() {
        return vekt;
    }

    public void setVekt(String vekt) {
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
