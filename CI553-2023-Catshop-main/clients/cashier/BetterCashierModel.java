package clients.cashier;

import catalogue.BetterBasket;
import catalogue.Product;
import middle.MiddleFactory;

import java.io.Serializable;
import java.util.Comparator;

public class BetterCashierModel extends CashierModel  {
    @Override
    protected BetterBasket makeBasket()
    {
        return new BetterBasket();
    }
    public BetterCashierModel(MiddleFactory mf) {
        super(mf);


    }



}
