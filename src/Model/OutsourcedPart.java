/**
 * Author: kcmodev
 * Class: C482 Software 1
 * Email: ****@wgu.edu
 * Date Submitted: 7/21/2020
 */

package Model;


import javafx.beans.property.SimpleStringProperty;

public class OutsourcedPart extends Part {

    private SimpleStringProperty companyName;

    /**
     * constructors
     */
    public OutsourcedPart() { }

    public OutsourcedPart(int partID, String partName, double partPrice, int partStock, int partStockMin, int partStockMax, String companyName) {
        super(partID, partName, partPrice, partStock, partStockMin, partStockMax);
        this.companyName = new SimpleStringProperty(companyName);
    }


    /**
     * getters and setters
     */
    public String getCompanyName() { return companyName.get(); }

    public void setCompanyName(String companyName) { this.companyName = new SimpleStringProperty(companyName); }
}
