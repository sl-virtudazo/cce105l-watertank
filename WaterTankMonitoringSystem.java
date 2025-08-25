package CCE105L_LabActs;

import javax.swing.JOptionPane;

abstract class WaterTank {
    protected final int tankCapacity;
    protected int currentWaterLevel;
    public WaterTank(int capacity) {
        this.tankCapacity = capacity;
        this.currentWaterLevel = 0;
    }
    public abstract void fillTank(int liters);
    public abstract void useWater(int liters);
    public abstract void checkStatus();
}

class HomeTank extends WaterTank {
    public HomeTank() { super(500); }

    @Override
    public void fillTank(int liters) {
        currentWaterLevel += liters;
        if (currentWaterLevel > tankCapacity) currentWaterLevel = tankCapacity;
    }
    @Override
    public void useWater(int liters) {
        currentWaterLevel -= liters;
        if (currentWaterLevel < 0) currentWaterLevel = 0;
    }
    @Override
    public void checkStatus() {
        String status;
        if (currentWaterLevel == tankCapacity) status = "Tank is full.";
        else if (currentWaterLevel == 0) status = "Tank is empty.";
        else status = "Tank is in use.";
        JOptionPane.showMessageDialog(null, status);
        if (currentWaterLevel == tankCapacity) { JOptionPane.showMessageDialog(null,"Tank is full. Program ended.");
            System.exit(0);
        }}}

class BuildingTank extends WaterTank {
    public BuildingTank() { super(1000); }

    @Override
    public void fillTank(int liters) {
        currentWaterLevel += liters;
        if (currentWaterLevel > tankCapacity) currentWaterLevel = tankCapacity;
    }
    @Override
    public void useWater(int liters) {
        currentWaterLevel -= liters;
        if (currentWaterLevel < 0) currentWaterLevel = 0;
    }
    @Override
    public void checkStatus() {
        String status;
        if (currentWaterLevel == tankCapacity) status = "Tank is full.";
        else if (currentWaterLevel == 0) status = "Tank is empty.";
        else status = "Tank is in use.";
        JOptionPane.showMessageDialog(null, status);
        if (currentWaterLevel == tankCapacity) { JOptionPane.showMessageDialog(null, "Tank is full. Program ended.");
            System.exit(0);
        }}}

public class WaterTankMonitoringSystem {
    private static void fillTank(WaterTank tank) {

        while (true) {
            String input = JOptionPane.showInputDialog("Enter liters to fill:");
            if (input == null) {
                JOptionPane.showMessageDialog(null, "Program ended.");
                System.exit(0);
            } try {
                int liters = Integer.parseInt(input.trim());
                if (liters <= 0) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Try again.");
                    continue;
                }
                if (tank.currentWaterLevel + liters > tank.tankCapacity) {
                    JOptionPane.showMessageDialog(null, "Exceeds tank capacity! Try again.");
                    continue;
                }
                tank.fillTank(liters);
                JOptionPane.showMessageDialog(null, liters + " liters added. Current level: " + tank.currentWaterLevel + "/" + tank.tankCapacity);
                tank.checkStatus();
                break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Try again.");
            }}}

    private static void useWater(WaterTank tank) {
        if (tank.currentWaterLevel == 0) {
            JOptionPane.showMessageDialog(null, "Tank is empty! Fill the tank first.");
            return;
        }
        while (true) {
            String input = JOptionPane.showInputDialog("Enter liters to use:");
            if (input == null) { // Cancel or close clicked
                JOptionPane.showMessageDialog(null, "Program ended.");
                System.exit(0);
            } try {
                int liters = Integer.parseInt(input.trim());
                if (liters <= 0) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Try again.");
                    continue;
                }
                if (liters > tank.currentWaterLevel) {
                    JOptionPane.showMessageDialog(null, "Tank is empty! Fill the tank first.");
                    continue;
                }
                tank.useWater(liters);
                JOptionPane.showMessageDialog(null, liters + " liters used. Current level: " + tank.currentWaterLevel + "/" + tank.tankCapacity);
                tank.checkStatus();
                break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Try again.");
            }}}

    public static void main(String[] args) {
        WaterTank tank = null;
        int choice;

        do {
            try {
                String options = """
                        Enter a tank type:
                        1. HomeTank (500 liters)
                        2. BuildingTank (1000 liters)
                        3. Exit the program
                        """;
                String input = JOptionPane.showInputDialog(options);
                if (input == null) { // Cancel or close clicked
                    JOptionPane.showMessageDialog(null, "Program ended.");
                    System.exit(0);
                }
                choice = Integer.parseInt(input.trim());
                switch (choice) {
                    case 1:
                        tank = new HomeTank();
                        break;
                    case 2:
                        tank = new BuildingTank();
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, "Program ended.");
                        System.exit(0);
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid input! Try again.");
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Try again.");
            }
        } while (tank == null);

        do {
            try {
                String menu = """
                        Select which action to perform:
                        1. Fill Tank
                        2. Use Water
                        3. Check Status
                        4. Exit the program
                        """;
                String input = JOptionPane.showInputDialog(menu);
                if (input == null) { // Cancel or close clicked
                    JOptionPane.showMessageDialog(null, "Program ended.");
                    System.exit(0);
                }
                choice = Integer.parseInt(input.trim());
                switch (choice) {
                    case 1:
                        fillTank(tank);
                        break;
                    case 2:
                        useWater(tank);
                        break;
                    case 3:
                        tank.checkStatus();
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null, "Program ended.");
                        System.exit(0);
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid input! Try again.");
                        break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Try again.");
            }
        } while (true);
    }}
