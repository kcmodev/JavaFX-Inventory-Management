package Model;


public class InHousePart extends Part {

    private int partMachineID;

    public InHousePart(int partID, String partName, double partPrice, int partStock, int partStockMin, int partStockMax) {
        super(partID, partName, partPrice, partStock, partStockMin, partStockMax);
    }

    public int getPartMachineID() { return partMachineID; }

    public void setPartMachineID(int partMachineID) { this.partMachineID = partMachineID; }
}
