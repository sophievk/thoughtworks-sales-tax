import java.math.BigDecimal;
import java.util.Arrays;

public class Tax{

    private String type;
    private BigDecimal rate;

    public Tax(String type, double rate){
        this.type = type;
        this.rate = new BigDecimal(rate);
    }

    /**
     * Gives the tax rate for the calling Tax
     * @return the tax rate 
     */
    public BigDecimal getRate(){
        return this.rate;
    }

}
