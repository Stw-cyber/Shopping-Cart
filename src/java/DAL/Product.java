package DAL;

public class Product {

    private Integer ProductID;
    private String ProductName;
    private Integer CategoryID;
    private String QuantityPerUnit;
    private Double UnitPrice;
    private Integer UnitsInStock;
    private Integer UnitsOnOrder;
    private Integer ReorderLevel;
    private Boolean Discontinued;

    public Product(Integer ProductID, String ProductName, Integer CategoryID, String QuantityPerUnit, Double UnitPrice, Integer UnitsInStock, Integer UnitsOnOrder, Integer ReorderLevel, Boolean Discontinued) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.CategoryID = CategoryID;
        this.QuantityPerUnit = QuantityPerUnit;
        this.UnitPrice = UnitPrice;
        this.UnitsInStock = UnitsInStock;
        this.UnitsOnOrder = UnitsOnOrder;
        this.ReorderLevel = ReorderLevel;
        this.Discontinued = Discontinued;
    }

    public Integer getProductID() {
        return ProductID;
    }

    public void setProductID(Integer ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public Integer getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(Integer CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getQuantityPerUnit() {
        return QuantityPerUnit;
    }

    public void setQuantityPerUnit(String QuantityPerUnit) {
        this.QuantityPerUnit = QuantityPerUnit;
    }

    public Double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(Double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public Integer getUnitsInStock() {
        return UnitsInStock;
    }

    public void setUnitsInStock(Integer UnitsInStock) {
        this.UnitsInStock = UnitsInStock;
    }

    public Integer getUnitsOnOrder() {
        return UnitsOnOrder;
    }

    public void setUnitsOnOrder(Integer UnitsOnOrder) {
        this.UnitsOnOrder = UnitsOnOrder;
    }

    public Integer getReorderLevel() {
        return ReorderLevel;
    }

    public void setReorderLevel(Integer ReorderLevel) {
        this.ReorderLevel = ReorderLevel;
    }

    public Boolean getDiscontinued() {
        return Discontinued;
    }

    public void setDiscontinued(Boolean Discontinued) {
        this.Discontinued = Discontinued;
    }

    @Override
    public String toString() {
        return "Product{" + "ProductID=" + ProductID + ", ProductName=" + ProductName + ", CategoryID=" + CategoryID + ", QuantityPerUnit=" + QuantityPerUnit + ", UnitPrice=" + UnitPrice + ", UnitsInStock=" + UnitsInStock + ", UnitsOnOrder=" + UnitsOnOrder + ", ReorderLevel=" + ReorderLevel + ", Discontinued=" + Discontinued + '}';
    }

}
