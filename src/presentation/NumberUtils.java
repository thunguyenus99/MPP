package presentation;

public class NumberUtils {
    public static boolean isNumeric(String val) {
        try {
            Integer.parseInt(val);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
