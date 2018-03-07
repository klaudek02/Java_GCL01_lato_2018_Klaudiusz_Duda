/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.Scanner;
import basis.*;
/**
 *
 * @author Ada
 */
public class ExtendedCircleCalculator extends CircleCalculator 
        implements ExtendedCalculator {
    public void calculateArea()throws Exception
    {
       double r;
       Scanner odczyt = new Scanner(System.in);
       System.out.println("Wpisz promien");
       r = odczyt.nextDouble();
       System.out.println("Pole wynosi: " + Math.PI*r*r);
       
    }
    
}
