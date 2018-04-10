/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testowaniekodu;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FieldCalculatorTest {
    FieldCalculator instance;
    double delta;
    
    @BeforeClass
    public static void setUpClass() {
    }
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new FieldCalculator();
        delta = 0.0000001;
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calculateSquare method, of class FieldCalculator.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateSquareNegativeSide() {
       double result = instance.calculateSquare(-1);
    }
    @Test
    public void testCalculateSquareNotNull() {
       assertNotNull(instance.calculateSquare(10));
    }
    @Test
    public void testCalculateSquareEquals()
    {
        assertEquals(25,instance.calculateSquare(5),delta);
    }
    @Test
    public void testCalculateSquareNotEquals() {
        assertNotEquals(7,instance.calculateSquare(3.0),delta);  
    }
    /**
     * Test of calculateCircle method, of class FieldCalculator.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateCircleNegativeRadius() {
       double result = instance.calculateCircle(-1);
    }
    @Test
    public void testCalculateCircleNotNull() {
        assertNotNull(instance.calculateCircle(10));
    }
    @Test
    public void testCalculateCircleEquals()
    {
        assertEquals(28.26, instance.calculateCircle(3),0.1);
    }
    @Test
    public void testCalculateCircleNotEquals() {
        assertNotEquals(7,instance.calculateCircle(3.0),delta);  
    }
    /**
     * Test of calculateTriangle method, of class FieldCalculator.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateTriangleNegativeSide() {
       double result = instance.calculateTriangle(0, 10);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateTriangleNegativeHeight() {
        double result = instance.calculateTriangle(10, 0);
    }
    @Test
    public void testCalculateTriangleEquals() {
        assertEquals(25,instance.calculateTriangle(10,5),delta);
    }
    @Test
    public void testCalculateTriangleNotEquals() {
        assertNotEquals(7,instance.calculateTriangle(3.0,3),delta);  
    }
    /**
     * Test of calculateRectangle method, of class FieldCalculator.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRectangleNegativeSideA() {
        double  result = instance.calculateRectangle(0,10);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRectangleNegativeSideB() {
        double result = instance.calculateRectangle(10,0);
    }
    @Test
    public void testCalculateRectangleEquals() {
        assertEquals(50,instance.calculateRectangle(10,5),delta);
    }
    @Test
    public void testCalculateRectangleNotEquals() {
        assertNotEquals(7,instance.calculateRectangle(3.0,3),delta);  
    }
}