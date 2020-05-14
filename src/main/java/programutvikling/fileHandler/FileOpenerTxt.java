package programutvikling.fileHandler;

import programutvikling.base.Component;
import programutvikling.base.ComponentRegister;
import programutvikling.base.Data;
import programutvikling.base.DataRegister;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileOpenerTxt implements FileOpener {
    @Override
    public void openComponent(ComponentRegister register, Path filePath) throws IOException {
        register.removeAll();
        // try-with-resources lukker automatisk filen
        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                register.addComponent(parseComponent(line));
            }
        }
    }

    private Component parseComponent(String line) throws InvalidPersonFormatException {
        // split line string into three using the separator ";"
        String[] split = line.split(";");
        if(split.length != 6) {
            throw new programutvikling.fileHandler.InvalidPersonFormatException("Du må bruke ; for å separere datafeltene.");
        }

        // extract all datafields from the string

        String komponent = split[0];
        String navn = split[1];
        String produsent = split[2];
        String lansert = split[4];

        int vekt = parseNumber(split[3], "Vekt må være et tall");
        int pris = parseNumber(split[5], "Pris må være et tall");

        try {
            return new Component(komponent, navn, produsent, vekt, lansert,pris);
        } catch (IllegalArgumentException e) {
            throw new InvalidPersonFormatException(e.getMessage());
        }
    }

    @Override
    public void openData(DataRegister register, Path filePath) throws IOException {
        register.removeAll();
        // try-with-resources lukker automatisk filen
        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                register.addData(parseData(line));
            }
        }
    }

    private Data parseData(String line) throws InvalidPersonFormatException {
        // split line string into three using the separator ";"
        String[] split = line.split(";");
        if(split.length != 7) {
            throw new programutvikling.fileHandler.InvalidPersonFormatException("Du må bruke ; for å separere datafeltene.");
        }

        // extract all datafields from the string

        String aCase  = split[0];
        String motherboard = split[1];
        String prosessor = split[2];
        String ram = split[3];
        String harddisk = split[4];
        String power = split[5];

        int pris = parseNumber(split[6], "Pris må være et tall");

        try {
            return new Data(aCase, motherboard, prosessor, ram, harddisk,power,pris);
        } catch (IllegalArgumentException e) {
            throw new InvalidPersonFormatException(e.getMessage());
        }
    }

    private int parseNumber(String str, String errorMessage) throws IllegalArgumentException {
        int number;
        try {
            number = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }

        return number;
    }
}
