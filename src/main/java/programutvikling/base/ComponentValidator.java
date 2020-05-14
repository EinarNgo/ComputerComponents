package programutvikling.base;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ComponentValidator {

    public static void komponentInput(String komponent) {

        if (!komponent.equals("Kabinett") && !komponent.equals("Hovedkort") && !komponent.equals("Prosessor") && !komponent.equals("Ram") && !komponent.equals("Harddisk") && !komponent.equals("Strømforsyning")) {
            throw new IllegalArgumentException("Komponent må være Kabinett, Hovedkort, Prosessor, Ram, Harddisk og Strømforsyning");
        }

        if (komponent.isBlank() && komponent.matches("[^\\d]+")) {
            throw new IllegalArgumentException("Komponent kan ikke være tom eller inneholde tall");
        }
    }

    public static void navnInput(String name) {
        if (name.isBlank() && !name.matches("[^\\d]+")) {
            throw new IllegalArgumentException("Navn kan ikke være tom eller inneholde tall");
        }
    }

    public static void produsentInput(String produsent) {
        if (produsent.isBlank() && !produsent.matches("[^\\d]+")) {
            throw new IllegalArgumentException("Produsent kan ikke være tom eller inneholde tall");
        }
    }

    public static void vektInput(String vekt) {
        if (vekt.isBlank() && !vekt.matches("^[0-9]+$")) {
            throw new NumberFormatException();
        }
    }

    public static void prisInput(String pris) {
        if (pris.isBlank() && !pris.matches("[^\\d]+")) {
            throw new NumberFormatException();
        }
    }

    public static void datoInput(String dato) {
        if (!dato.matches("^(((0[1-9]|[12][0-9]|30)[-/]?(0[13-9]|1[012])|31[-/]?(0[13578]|1[02])|(0[1-9]|1[0-9]|2[0-8])[-/]?02)[-/]?[0-9]{4}|29[-/]?02[-/]?([0-9]{2}(([2468][048]|[02468][48])|[13579][26])|([13579][26]|[02468][048]|0[0-9]|1[0-6])00))$")) {
            throw new IllegalArgumentException("Ugyldig dato, Format DD-MM-YYYY");
        } else {
            String DATE_FORMAT = "dd-MM-yyyy";
            try {
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                df.parse(dato);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Ugyldig dato, Format DD-MM-YYYY");
            }
        }
    }


}
