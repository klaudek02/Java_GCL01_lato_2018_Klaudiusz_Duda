package testowaniekodu;

import org.junit.After;
import org.junit.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;


public class BasicCalculatorTest {
    BasicCalculator instance;
    double delta;
    //@Rule
    //public final ExpectedException exception = ExpectedException.none();
    @Before
    public void setUp() {
        instance = new BasicCalculator();
        delta = 0.0000001;
    }
    @After
    public void tearDown() {   
    }
    @BeforeClass
    public static void setUpClass() {
    }
    @AfterClass
    public static void tearDownClass() {
    }
    /**
     * Test of calculateSum method, of class BasicCalculator.
     */
    @Test
    public void testCalculateSumEqualsEquals() {
        assertEquals(0.1, instance.calculateSum(0.05, 0.05), delta);
        assertEquals(150, instance.calculateSum(100, 50), delta);
        assertEquals(1, instance.calculateSum(0, 1), delta);
        assertEquals(111, instance.calculateSum(11, 100), delta);
        assertEquals(0, instance.calculateSum(1, -1), delta);
        assertEquals(200000000, instance.calculateSum(100000000, 100000000), delta);
    }
    @Test
    public void testCalculateSumNotNull() {
        assertNotNull(instance.calculateSum(3.0,3));  
    }
    @Test
    public void testCalculateSumNotEquals() {
        assertNotEquals(7,instance.calculateSum(3.0,3),delta);  
    }

    /**
     * Test of calculateDifference method, of class BasicCalculator.
     */
    @Test
    public void testCalculateDifferenceEquals() {
        assertEquals(0.1, instance.calculateDifference(1.15, 1.05), delta);
        assertEquals(150, instance.calculateDifference(200, 50), delta);
        assertEquals(1, instance.calculateDifference(2, 1), delta);
        assertEquals(21, instance.calculateDifference(121, 100), delta);
        assertEquals(0, instance.calculateDifference(-1, -1), delta);
        assertEquals(200000000, instance.calculateDifference(400000000, 200000000), delta);
    }
    @Test
    public void testCalculateDifferenceNotNull() {
        assertNotNull(instance.calculateDifference(3.0,3));
    }
    @Test
    public void testCalculateDifferenceNotEquals() {
        assertNotEquals(7,instance.calculateDifference(3.0,3),delta);  
    }
    /**
     * Test of calculateMultiplication method, of class BasicCalculator.
     */
    @Test
    public void testCalculateMultiplicationEquals() {
        assertEquals(10, instance.calculateMultiplication(5,2 ), delta);
        assertEquals(150, instance.calculateMultiplication(50, 3), delta);
        assertEquals(1, instance.calculateMultiplication(1, 1), delta);
        assertEquals(120, instance.calculateMultiplication(2, 60), delta);
        assertEquals(0, instance.calculateMultiplication(0, -1), delta);
        assertEquals(200000000, instance.calculateMultiplication(100000000, 2), delta);
    }
    @Test
    public void testCalculateMultiplicationNotEquals() {
        assertNotEquals(7,instance.calculateSum(3.0,3),delta);  
    }
    @Test
    public void testCalculateMultiplicationNotNull() {
        assertNotNull(instance.calculateMultiplication(3.0,3));
    }
    /**
     * Test of calculateDivision method, of class BasicCalculator.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateDivisionException() {
        double a = 5.0;
        double b = 0.0;
        double result = instance.calculateDivision(a, b);
    }
    @Test
    public void testCalculateDivisionEquals() {
        assertEquals(10, instance.calculateDivision(5, 0.5), delta);
        assertEquals(150, instance.calculateDivision(150, 1), delta);
        assertEquals(1, instance.calculateDivision(1, 1), delta);
        assertEquals(12, instance.calculateDivision(144, 12), delta);
        assertEquals(0, instance.calculateDivision(0, -1), delta);
        assertEquals(2, instance.calculateDivision(200000000, 100000000), delta);
    }
    @Test
    public void testCalculateDivisionNotNull() {
        assertNotNull(instance.calculateDivision(3.0,3));
    }
    @Test
    public void testCalculateDivisionNotEquals() {
        assertNotEquals(7,instance.calculateDivision(3.0,3),delta);  
    }
    /**
     * Test of calculatePow method, of class BasicCalculator.
     */
    @Test
    public void testCalculatePowEquals() {
        assertEquals(2, instance.calculatePow(2, 1), delta);
        assertEquals(100, instance.calculatePow(10, 2), delta);
        assertEquals(8, instance.calculatePow(2, 3), delta);
        assertEquals(36, instance.calculatePow(6, 2), delta);   
        assertEquals(1, instance.calculatePow(0, 0), delta);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCalculatePowException()
    {
        double result = instance.calculatePow(0, -1);
    }
    @Test
    public void testCalculatePowNotNull() {
        assertNotNull(instance.calculatePow(3.0,3));
    }
    @Test
    public void testCalculatePowNotEquals() {
        assertNotEquals(7,instance.calculatePow(3.0,3),delta);  
    }
    /**
     * Test of calculateSqlr method, of class BasicCalculator.
     */
    @Test
    public void testCalculateSqlrEquals() {
        assertEquals(2, instance.calculateSqlr(2, 1), delta);
        assertEquals(10, instance.calculateSqlr(1000, 3), delta);
        assertEquals(2, instance.calculateSqlr(8, 3), delta);
        assertEquals(2, instance.calculateSqlr(16, 4), delta);
        assertEquals(0.5, instance.calculateSqlr(4, -2), delta);
        assertEquals(10, instance.calculateSqlr(10, 1), delta);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateSqlrExceptionValueA() {
        double a = -1.0;
        double b = 3.0;
        double result = instance.calculateSqlr(a, b);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateSqlrExceptionValueB() {
        double a = 3.0;
        double b = 0.0;
        double result = instance.calculateSqlr(a, b);
    }
    @Test
    public void testCalculateSqlrNotNull() {
        assertNotNull(instance.calculateSqlr(3.0,3));
    }
    @Test
    public void testCalculateSqlrNotEquals() {
        assertNotEquals(7,instance.calculateSqlr(3.0,3),delta);  
    }
}