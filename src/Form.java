import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.util.function.DoubleBinaryOperator;

public class Form extends JDialog {
    private JPanel contentPane;
    private JButton countButton;
    private JButton exitButton;
    private JTable resultTable;
    private JTextField fuelConsumptionTf;
    private JTextField fuelDensityTf;
    private JTextField fuelWorkTf;
    private JTextField fuelAmountTf;
    private JScrollPane tablePane;

    private Controller controller;

    public Form() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(countButton);
        controller = new Controller();

        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Double> values = controller.doCount(getEmissionValues(), controller.countFuelConsumption());
                updateTable(values);
            }
        });
        tuneTable();
        tuneTextFields();
    }

    private ArrayList<Double> getEmissionValues() {
        ArrayList<Double> result = new ArrayList<Double>();
        for (int i = 0; i < Controller.POLUTANTS_CAPACITY; i++) {
            result.add(Double.valueOf((String) resultTable.getValueAt(i, 2)));
        }
        return result;
    }

    private void updateTable(ArrayList<Double> values) {
        DefaultTableModel model = new DefaultTableModel(0, 6);
        if (model.getRowCount() > 0) {
            model.setNumRows(0);
        }
        generateTableHeaders(model);
        generateRows(model, values);
        resultTable.setModel(model);
    }

    private void tuneTextFields() {
        fuelAmountTf.setText(String.valueOf(FuelConstants.FUEL_AMOUNT));
        fuelWorkTf.setText(String.valueOf(FuelConstants.FUEL_WORK));
        fuelDensityTf.setText(String.valueOf(FuelConstants.FUEL_DENSITY));
        fuelConsumptionTf.setText(String.valueOf(controller.countFuelConsumption()));
    }

    private void tuneTable() {
        DefaultTableModel model = new DefaultTableModel(0, 6);
        generateTableHeaders(model);
        generateRows(model, null);
        resultTable.setModel(model);
    }

    private void generateTableHeaders(DefaultTableModel model) {
        Vector colHdrs = new Vector(6);
        colHdrs.addElement("Назва показника еміссії");
        colHdrs.addElement("Умовне позначення");
        colHdrs.addElement("Значення показника (г/ГДж)");
        colHdrs.addElement("Валовий викид");
        colHdrs.addElement("Ставки податку");
        colHdrs.addElement("ПЗ");
        model.setColumnIdentifiers(colHdrs);
    }

    private void generateRows(DefaultTableModel model,  ArrayList<Double> values) {
        String grossEmission;
        String tax;
        String PZ;
        for (int i = 0; i < Controller.POLUTANTS_CAPACITY; i++) {
            int valuesCounter = i * 3;
            if (values == null) {
                grossEmission = "";
                tax = "";
                PZ = "";
            } else {
                grossEmission = String.valueOf(values.get(valuesCounter));
                tax = String.valueOf(values.get(valuesCounter + 1));
                PZ = String.valueOf(values.get(valuesCounter + 2));
            }
            Vector rowData = new Vector(6);
            switch (i) {
                case 0: {
                    generateRowElement(rowData, "Оксиди азоту", "kNOx", "64.311", grossEmission, tax, PZ);
                    break;
                }
                case 1: {
                    generateRowElement(rowData, "Оксид вуглецю", "kCO", "248.75", grossEmission, tax, PZ);
                    break;
                }
                case 2: {
                    generateRowElement(rowData, "Діоксид вуглецю", "kCO2", "58748.13", grossEmission, tax, PZ);
                    break;
                }
                case 3: {
                    generateRowElement(rowData, "Діоксид азоту", "kN2O", "0.1", grossEmission, tax, PZ);
                    break;
                }
                case 4: {
                    generateRowElement(rowData, "Метан", "kCH4", "1.0", grossEmission, tax, PZ);
                    break;
                }
            }
            model.addRow(rowData);
        }
    }

    private void generateRowElement(Vector rowData, String name, String abbreviation, String value, String grossEmission, String tax, String PZ) {
        rowData.addElement(name);
        rowData.addElement(abbreviation);
        rowData.addElement(value);
        rowData.addElement(grossEmission);
        rowData.addElement(tax);
        rowData.addElement(PZ);
    }
}
