package programutvikling.base;

public class ComponentValidator {
    public static final int MAX_AGE = 130;

    public static boolean stringInput(String name) {
        return !name.isBlank() && name.matches("[^\\d]+");
    }

    public static boolean intInput(int number) {
        boolean temp = true;
        if (number == (int)number)
        {
            temp = true;
        } else {
            temp = false;
        }
        return temp;
    }

    static boolean phone(String phone) {
        return !phone.isEmpty() && !phone.isBlank() && phone.length() < 20 &&
                phone.matches("[\\d+(]+([\\d()-]+(?: [\\d()-]+)*)");
    }
}
