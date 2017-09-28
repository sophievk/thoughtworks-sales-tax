import java.math.BigDecimal;

public class Product{

    private String name;
    private BigDecimal price;
    private boolean imported;

    public Product(String name, String price){
        this.name = name;
        this.price = new BigDecimal(price);
        this.imported = isImported(name);
    }

    /**
     * Determines if the product is imported.
     * @param imported
     *              string of product name
     * @return true if "imported" is in the given String
     *         else return false
     */
    private boolean isImported(String imported){
        return (imported.indexOf("imported") >= 0) ? true : false;
    }

    /**
     * Gives the name of the calling Product
     * @return the name of the product
     */
    public String getName(){
        return this.name;
    }

    /**
     * Gives the price of the calling Product
     * @return the price of the product
     */
    public BigDecimal getPrice(){
        return this.price;
    }

    /**
     * Gives the import boolean of the calling Product
     * @return the import boolean
     */
    public boolean getImport(){
        return this.imported;
    }

}
