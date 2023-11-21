
import static org.junit.Assert.*;
import org.junit.Test;
public class CalculatorTest
{
    @Test
    public void testAdd1()
    {
        Calculator calculator = new Calculator();
        double result = calculator.add(10,50);
        assertEquals(10.0,result,0);
    }
    
    @Test
    public void testAdd2()
    {
        Calculator calculator = new Calculator();
        double result = calculator.add(7.5,1.5);
        assertEquals(10.0,result,0);
    }
    
}
