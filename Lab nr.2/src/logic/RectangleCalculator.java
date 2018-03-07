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
public class RectangleCalculator implements Calculator {
    
    public double calculatePerimeter()
    {
       double a,b;
       Scanner odczyt = new Scanner(System.in);
       System.out.println("Wpisz a i b");
       a = odczyt.nextDouble();
       b = odczyt.nextDouble();
       return 2*a + 2*b;
    }
}
