/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import oblugaplikowstrumieni.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class JUnitTest {
    
    ExtendedSystemCache e;
    ExtendedSystemCache im;
    public JUnitTest() {
    }
    
    @Before
    public void setUp() {
        e  = new ExtendedSystemCache();
        im = new ExtendedSystemCache();
    }
    @After
    public void tearDown() {
    }
    @Test
    public void testValuesImportExport()
    {
        double array[] = {1,2,2,33,24,1,4,21,3,2};
        double array2[] = {1,20,0,330,240,1,4,21,3,2};
        Double put1 = 10.0;
        Double put2 = 999.0;
        e.put(array,put1);
        e.put(array2,put2);
        try{
            e.exportCSV("test.csv");
            im.importCSV("test.csv");
        }catch(IOException ex){}
        Double des = im.get(array);
        Double des2 = im.get(array2);
        Assert.assertEquals(des2,put2);
        Assert.assertEquals(put1,des);
        Assert.assertEquals(im.getCache().size(), e.getCache().size(),0);
    }
    @Test
    public void testValuesSerializeDeserialize()
    {
        double array[] = {1,2,2,33,24,1,4,21,3,2};
        double array2[] = {1,20,0,330,240,1,4,21,3,2};
        Double put1 = 10.0;
        Double put2 = 999.0;
        e.put(array,put1);
        e.put(array2,put2);
        try
        {
            e.serialize("test.txt");
            im.deserialize("test.txt");
        }catch(IOException | ClassNotFoundException ex){System.out.println(e);}
        Double des = im.get(array);
        Double des2 = im.get(array2);
        Assert.assertEquals(des2,put2);
        Assert.assertEquals(put1,des);
        Assert.assertEquals(im.getCache().size(), e.getCache().size(),0);
        }
    }