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
public class ExtendedTriangleCalculator extends TriangleCalculator
        implements ExtendedCalculator {
    public void calculateArea() throws Exception
    {
       double a,h;
       Scanner odczyt = new Scanner(System.in);
       System.out.println("Wpisz a i h");
       a = odczyt.nextDouble();
       h = odczyt.nextDouble();
       System.out.println("Pole wynosi :"+ a*h/2.0);
    }
}

