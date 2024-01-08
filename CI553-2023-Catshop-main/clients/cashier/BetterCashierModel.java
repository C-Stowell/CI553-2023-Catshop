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
    /**
     * Construct the model of the Cashier
     *
     * @param mf The factory to create the connection objects
     */
    public BetterCashierModel(MiddleFactory mf) {
        super(mf);


    }



}
