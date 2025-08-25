package CCE105L_LabActs;

import javax.swing.JOptionPane;

abstract class WaterTank {
    protected final int tankCapacity;
    protected int currentWaterLevel;
    public WaterTank(int capacity) {
        tankCapacity = capacity; currentWaterLevel = 0;
    }
    public abstract void fillTank(int liters);
    public abstract void useWater(int liters);
    public abstract void checkStatus();
}

class HomeTank extends WaterTank {
    public HomeTank() {
        super(200);
    }
    @Override public void fillTank(int liters) { currentWaterLevel += liters; }
    @Override public void useWater(int liters) { currentWaterLevel -= liters; }
    @Override public void checkStatus() {
        String status;
        if (currentWaterLevel == 0) {
            status = "Tank is empty.";
            JOptionPane.showMessageDialog(null, status);
            System.exit(0);
        }
        else if (currentWaterLevel == tankCapacity) {
            status = "Tank is full.";
            JOptionPane.showMessageDialog(null, status);
            System.exit(0);
        }
        else {
            status = "Tank is in use.";
            JOptionPane.showMessageDialog(null, status);
        }
    }
}

class BuildingTank extends WaterTank {
    public BuildingTank() {
        super(500);
    }
    @Override public void fillTank(int liters) { currentWaterLevel += liters; }
    @Override public void useWater(int liters) { currentWaterLevel -= liters; }
    @Override public void checkStatus() {
        String status;
        if (currentWaterLevel == 0) {
            status = "Tank is empty.";
            JOptionPane.showMessageDialog(null, status);
            System.exit(0);
        }
        else if (currentWaterLevel == tankCapacity) {
            status = "Tank is full.";
            JOptionPane.showMessageDialog(null, status);
            System.exit(0);
        }
        else {
            status = "Tank is in use.";
            JOptionPane.showMessageDialog(null, status);
        }
    }
}

public class WaterTankMonitoringSystem {
    static void fillTank(WaterTank tank) {
        int liters;
        do {
            String input = JOptionPane.showInputDialog("Current Water Level: %d/%d\nEnter liters to fill:".formatted(tank.currentWaterLevel, tank.tankCapacity));
            if (input == null) System.exit(0);
            try { liters = Integer.parseInt(input.trim()); } catch (Exception e) { liters = 0; }
            if (liters <= 0 || liters > (tank.tankCapacity - tank.currentWaterLevel)) {
                JOptionPane.showMessageDialog(null, "Invalid input! Try again."); liters = 0; }
        } while (liters == 0);
        tank.fillTank(liters);
        JOptionPane.showMessageDialog(null, liters + " liters added. Current Water Level: " + tank.currentWaterLevel + "/" + tank.tankCapacity);
    }

    static void useWater(WaterTank tank) {
        if (tank.currentWaterLevel == 0) {
            JOptionPane.showMessageDialog(null, "Tank is empty! Fill the tank first."); return;
        }
        int liters;
        do {
            String input = JOptionPane.showInputDialog("Current Water Level: %d/%d\nEnter liters to use:".formatted(tank.currentWaterLevel, tank.tankCapacity));
            if (input == null) System.exit(0);
            try { liters = Integer.parseInt(input.trim()); } catch (Exception e) { liters = 0; }
            if (liters <= 0 || liters > tank.currentWaterLevel)
            { JOptionPane.showMessageDialog(null, "Invalid input! Try again."); liters = 0; }
        } while (liters == 0);
        tank.useWater(liters);
        JOptionPane.showMessageDialog(null, liters + " liters used. Current Water Level: " + tank.currentWaterLevel + "/" + tank.tankCapacity);
    }

    static void checkStatus(WaterTank tank) {
        tank.checkStatus();
    }

    public static void main(String[] args) {
        while (true) {
            WaterTank tank = null;
            do {
                String input = JOptionPane.showInputDialog("""
                    Enter type of tank:
                    1. HomeTank (200 liters)
                    2. BuildingTank (500 liters)
                    3. Exit the program
                    """);
                if (input == null) { JOptionPane.showMessageDialog(null, "Program ended."); System.exit(0); }
                int choice;
                try { choice = Integer.parseInt(input.trim()); } catch (Exception e) { choice = 0; }
                if (choice == 1) tank = new HomeTank();
                else if (choice == 2) tank = new BuildingTank();
                else if (choice == 3) { JOptionPane.showMessageDialog(null, "Program ended."); System.exit(0); }
                else JOptionPane.showMessageDialog(null, "Invalid input! Try again.");
            } while (tank == null);

            while (true) {
                String input = JOptionPane.showInputDialog("""
                    Current Water Level: %d/%d
                    Select which action to perform:
                    1. Fill Tank
                    2. Use Water
                    3. Check Status
                    4. Change Tank
                    5. Exit the program
                    """.formatted(tank.currentWaterLevel, tank.tankCapacity));
                if (input == null) { JOptionPane.showMessageDialog(null, "Program ended."); System.exit(0); }
                int action;
                try { action = Integer.parseInt(input.trim()); } catch (Exception e) { action = 0; }
                if (action == 1) fillTank(tank);
                else if (action == 2) useWater(tank);
                else if (action == 3) checkStatus(tank);
                else if (action == 4) break;
                else if (action == 5) { JOptionPane.showMessageDialog(null, "Program ended."); System.exit(0); }
                else JOptionPane.showMessageDialog(null, "Invalid input! Try again.");
            }
        }
    }
}