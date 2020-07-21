package Model;


public class OutsourcedPart extends Part {

    private String companyName;

    public OutsourcedPart(int partID, String partName, double partPrice, int partStock, int partStockMin, int partStockMax, String companyName) {
        super(partID, partName, partPrice, partStock, partStockMin, partStockMax);
        this.companyName = companyName;
    }

    public OutsourcedPart() { }

    public String getCompanyName() { return companyName; }

    public void setCompanyName(String companyName) { this.companyName = companyName; }
}
