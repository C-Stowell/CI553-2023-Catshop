package catalogue;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;

/**
 * A better version of the basket program,
 * when multiple of the same products have been ordered they total rather than being individual.
 * 
 * @author  Cameron Stowell
 * @version 1.0
 */
public class BetterBasket extends Basket implements Serializable, Comparator<Product>
{
  private static final long serialVersionUID = 1L;
  @Override
  public boolean add(Product p1) {
    for (Product p2: this) {
      if (p1.getProductNum().equals(p2.getProductNum())){
        p2.setQuantity(p2.getQuantity()+p1.getQuantity());
        return(true);
      }
    }
    super.add(p1);
    Collections.sort(this, this);
    return(true);
  }
  public int compare(Product p1, Product p2) {
    return p1.getProductNum().compareTo(p2.getProductNum());
  }
}
