import java.util.ArrayList;

public class FuelConstants {
    public static final double FUEL_DENSITY = 0.723;
    public static final double FUEL_WORK = 45.75;
    public static final int FUEL_AMOUNT = 750;

    public static ArrayList<Double> taxes = new ArrayList<>();

    static {
        taxes.add(2451.84);
        taxes.add(92.37);
        taxes.add(138.57);
        taxes.add(598.4);
        taxes.add(138.57);
    }
}
