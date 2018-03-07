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
public class ExtendedSquareCalculator extends SquareCalculator
        implements ExtendedCalculator{
    @Override
    public void calculateArea() throws Exception
    {
       
       double a;
       Scanner odczyt = new Scanner(System.in);
       System.out.println("Wpisz a");
       a = odczyt.nextDouble();
       System.out.println("Pole wynosi :"+ a*a);
    }
}
