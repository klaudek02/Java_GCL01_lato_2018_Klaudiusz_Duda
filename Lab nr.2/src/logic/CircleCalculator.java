/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;
import basis.Calculator;
import java.util.Scanner;
/**
 *
 * @author Ada
 */
public class CircleCalculator implements Calculator {
    @Override
    public double calculatePerimeter()
    {
       double r;
       Scanner odczyt = new Scanner(System.in);
       System.out.println("Wpisz promien");
       r = odczyt.nextDouble();
       return 2*r*Math.PI;
        
    }
    
}
