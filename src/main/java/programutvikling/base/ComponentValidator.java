package programutvikling.base;

public class ComponentValidator {

    public static void komponentInput(String komponent) {

        if (!komponent.equals("Case") && !komponent.equals("Motherboard") && !komponent.equals("Prosessor") && !komponent.equals("Ram") && !komponent.equals("Harddisk") && !komponent.equals("Power")) {
            throw new IllegalArgumentException("Komponent må være Case, Motherboard, Prosessor, Ram, Harddisk og power");
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

}
