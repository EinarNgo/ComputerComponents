package programutvikling.base;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Data implements Serializable {
    private static final long serialVersionUID = 1;
    private transient SimpleStringProperty aCase;
    private transient SimpleStringProperty motherboard;
    private transient SimpleStringProperty prosessor;
    private transient SimpleStringProperty ram;
    private transient SimpleStringProperty harddisk;
    private transient SimpleStringProperty power;
    private transient SimpleIntegerProperty pris;

    /**
     *  Klasse til å håndtere data deler som kommer inn systemet
     */
    public Data(String aCase, String motherboard, String prosessor, String ram, String harddisk, String power, int pris) {
        this.aCase = new SimpleStringProperty(aCase);
        this.motherboard = new SimpleStringProperty(motherboard);
        this.prosessor = new SimpleStringProperty(prosessor);
        this.ram = new SimpleStringProperty(ram);
        this.harddisk = new SimpleStringProperty(harddisk);
        this.power = new SimpleStringProperty(power);
        this.pris = new SimpleIntegerProperty(pris);
    }

    /**
     * Getter og setter metoder for Komponent
     * Setter metodene har også validering for komponentene som blir satt
     */
    public String getaCase() {
        return aCase.getValue();
    }

    public SimpleStringProperty aCaseProperty() {
        return aCase;
    }

    public final void setaCase(String aCase) {
        this.aCase.set(aCase);
    }

    public String getMotherboard() {
        return motherboard.getValue();
    }

    public SimpleStringProperty motherboardProperty() {
        return motherboard;
    }

    public final void setMotherboard(String motherboard) {
        this.motherboard.set(motherboard);
    }

    public String getProsessor() {
        return prosessor.getValue();
    }

    public SimpleStringProperty prosessorProperty() {
        return prosessor;
    }

    public final void setProsessor(String prosessor) {
        this.prosessor.set(prosessor);
    }

    public String getRam() {
        return ram.getValue();
    }

    public SimpleStringProperty ramProperty() {
        return ram;
    }

    public final void setRam(String ram) {
        this.ram.set(ram);
    }

    public String getHarddisk() {
        return harddisk.getValue();
    }

    public SimpleStringProperty harddiskProperty() {
        return harddisk;
    }

    public final void setHarddisk(String harddisk) {
        this.harddisk.set(harddisk);
    }

    public String getPower() {
        return power.getValue();
    }

    public SimpleStringProperty powerProperty() {
        return power;
    }

    public final void setPower(String power) {
        this.power.set(power);
    }

    public int getPris() {
        return pris.getValue();
    }

    public SimpleIntegerProperty prisProperty() {
        return pris;
    }

    public final void setPris(int pris) {
        this.pris.set(pris);
    }


    /**
     * Metoder til å skrive og lese fil
     * ToString metoden forklarer hvordan stringen skal være når den skrives til fil
     */
    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s;%s;%s;%s",
                aCase.getValue(), motherboard.getValue(), prosessor.getValue(),
                ram.getValue(), harddisk.getValue(), power.getValue(),pris.getValue());
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeUTF(getaCase());
        s.writeUTF(getMotherboard());
        s.writeUTF(getProsessor());
        s.writeUTF(getRam());
        s.writeUTF(getHarddisk());
        s.writeUTF(getPower());
        s.writeInt(getPris());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        String aCase = s.readUTF();
        String motherboard = s.readUTF();
        String prosessor = s.readUTF();
        String ram = s.readUTF();
        String harddisk = s.readUTF();
        String power = s.readUTF();
        int pris = s.readInt();

        this.aCase = new SimpleStringProperty(aCase);
        this.motherboard = new SimpleStringProperty(motherboard);
        this.prosessor = new SimpleStringProperty(prosessor);
        this.ram = new SimpleStringProperty(ram);
        this.harddisk = new SimpleStringProperty(harddisk);
        this.power = new SimpleStringProperty(power);
        this.pris = new SimpleIntegerProperty(pris);

        setaCase(aCase);
        setMotherboard(motherboard);
        setProsessor(prosessor);
        setRam(ram);
        setHarddisk(harddisk);
        setPower(power);
        setPris(pris);
    }
}
