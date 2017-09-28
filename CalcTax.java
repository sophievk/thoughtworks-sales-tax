import java.util.Hashtable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.math.RoundingMode;

public class CalcTax{

    private Hashtable<String, Tax> taxes;
    private List<String> exempted;
    //  = Arrays.asList("book", "chocolate", "pills");
    private BigDecimal roundFactor = new BigDecimal(0.05);

    public CalcTax(){
        taxes = new Hashtable();
        exempted = new ArrayList();
    }

    /**
     * Adds the Tax to the Hashtable.
      * @return void
     */
    public void addTax(String s, Tax t){
        taxes.put(s,t);
    }

    /**
     * Adds a new exemption to the list.
     * @return void
     */
    public void addExemption(String s){
        exempted.add(s);
    }

    /**
     * Determines if the product is exempted from basic sales tax.
     * @return true if the product is found in the list of exempted items
     *         else return false
     */
    private boolean isExempt(Product p){
        String name = p.getName();
        for(String s : exempted){
            if(name.indexOf(s) >= 0){
                return true;
            }
        }
        return false;
    }

    /**
     * Rounds to the nearest 0.05 cents.
     * @return rounded BigDecimal
     */
    private BigDecimal roundCents(BigDecimal b){
        BigDecimal rounded = b.divide(roundFactor, 0, RoundingMode.UP);
        rounded = rounded.multiply(roundFactor);
        rounded = rounded.setScale(2, RoundingMode.HALF_UP);
        return rounded;
    }

    /**
     * Multiplies the price by the tax rate.
     * @return the amount of tax to be added
     */
    private BigDecimal calculateTax(BigDecimal price, Tax t){
        return price.multiply(t.getRate());
    }

    /** Determines what tax needs to be applied and calls the methods to calculate those taxes.
     * @param item
     *          the product to calculate tax on
     * @return the total amount of tax applied
     */
    public BigDecimal calculateTax(Product item){
        BigDecimal total = new BigDecimal(0.0);
        BigDecimal price = item.getPrice();
        if(item.getImport()){
            Tax imported = taxes.get("imported");
            BigDecimal tax = roundCents(calculateTax(price, imported));
            total = total.add(tax);
        }
        if(!isExempt(item)){
            Tax basic = taxes.get("basic");
            BigDecimal tax = roundCents(calculateTax(price, basic));
            total = total.add(tax);
        }
        return total;
    }

}
