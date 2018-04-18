import java.util.ArrayList;

public class Controller {
    public static final int POLUTANTS_CAPACITY = 5;

    public Controller() {
    }

    public ArrayList<Double> doCount(ArrayList<Double> emissionValues, double fuelConsumption) {
        ArrayList<Double> result = new ArrayList<>(POLUTANTS_CAPACITY * 3);
        for (int i = 0; i < POLUTANTS_CAPACITY; i++) {
            Double grossEmission = 0.000001 * emissionValues.get(i) * fuelConsumption * FuelConstants.FUEL_WORK;
            result.add(grossEmission);
            result.add(FuelConstants.taxes.get(i));
            result.add(FuelConstants.taxes.get(i) * grossEmission);
        }
        return result;
    }

    public double countFuelConsumption() {
        return FuelConstants.FUEL_AMOUNT * FuelConstants.FUEL_DENSITY;
    }
}
