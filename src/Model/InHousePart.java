package Model;

public class InHousePart extends Part {

    private int machineID;

    /**
     * constructors
     */
    public InHousePart(){ }

    public InHousePart(int partID, String partName, double partPrice, int partStock, int partStockMin, int partStockMax, int machineID) {
        super(partID, partName, partPrice, partStock, partStockMin, partStockMax);
        this.machineID = machineID;
    }

    /**
     * getters and setters
     */
    public int getPartMachineID() { return machineID; }

    public void setPartMachineID(int partMachineID) { this.machineID = partMachineID; }
}
