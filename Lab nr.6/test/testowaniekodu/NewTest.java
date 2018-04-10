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

/**
 *
 * @author Ada
 */
public class NewTest {
    
    public NewTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void testCalculateSum1() {
        BasicCalculator instance = new BasicCalculator();
        double delta = 0.0000001;
        assertEquals(0.1, instance.calculateSum(0.05, 0.05), delta);
    }
    @Test
    public void testCalculateSum2() {
        BasicCalculator instance = new BasicCalculator();
        double delta = 0.0000001;
        assertEquals(150, instance.calculateSum(100, 50), delta);
    }
    @Test
    public void testCalculateSum3() {
        BasicCalculator instance = new BasicCalculator();
        double delta = 0.0000001;
        assertEquals(1, instance.calculateSum(0, 1), delta);
    }
        
    @Test
    public void testCalculateSum4() {
        BasicCalculator instance = new BasicCalculator();
        double delta = 0.0000001;
        assertEquals(111, instance.calculateSum(11, 100), delta);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
