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
public class ExtendedRectangleCalculator extends RectangleCalculator
        implements ExtendedCalculator {
    public void calculateArea() throws Exception
    {
       double a,b;
       Scanner odczyt = new Scanner(System.in);
       System.out.println("Wpisz a i b");
       a = odczyt.nextDouble();
       b = odczyt.nextDouble();
       System.out.println("Pole wynosi :"+ a*b);
    }
}
