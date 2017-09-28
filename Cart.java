import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.Set;
import java.util.Collection;

public class Cart{
    private Hashtable<Product, BigDecimal> purchase;
    private Hashtable<Product, BigDecimal> tax;
    private BigDecimal subTotal;
    private BigDecimal totalTax;

    public Cart(){
        purchase = new Hashtable();
        tax = new Hashtable();
        subTotal = new BigDecimal(0.0);
        totalTax = new BigDecimal(0.0);
    }

    /**
     * Adds the product to cart, updates the subTotal, calculate the tax
     * and updates the totalTax
     * @return void
     */
    public void add(Product p, BigDecimal quantity, CalcTax c){
        purchase.put(p, quantity);
        BigDecimal price = p.getPrice();
        BigDecimal t = c.calculateTax(p);

        subTotal = subTotal.add(price.multiply(quantity));
        tax.put(p, t);
        totalTax = totalTax.add(t);
    }

    /**
     * Adds subTotal and totalTax to find the final total price of the cart.
     * @return total cart price
     */
    private BigDecimal calculateTotal(){
        return subTotal.add(totalTax);
    }

    /**
     * Prints an itemized list of products purchased.
     * @return void
     */
    public void print(){
        Set<Product> products = purchase.keySet();

        for(Product p : products){
            BigDecimal quantity = purchase.get(p);
            BigDecimal totalPrice = p.getPrice().add(tax.get(p));
            System.out.printf("%.0f %s : %.2f\n", quantity, p.getName(), totalPrice);
        }
        System.out.printf("Sales Taxes : %.2f\n", totalTax);
        System.out.printf("Total : %.2f\n", calculateTotal());
    }

}
