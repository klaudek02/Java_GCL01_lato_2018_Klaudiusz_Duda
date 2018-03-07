package ui;
import basis.*;
import logic.*;
import java.util.Scanner;
public class IFCalculator {
    public static void main(String[] args) throws Exception {
       ExtendedCalculator kalkulator;
       ExtendedCalculator kalk;
       System.out.println("Wybierz: 1-4\n 1.SquareCalculator\n 2.CircleCalculator"
               + "\n3.TriangleCalculator\n 4.RectangleCalculator");
       int wybor;
       double obwod=0, pole;
       Scanner odczyt = new Scanner(System.in);
       wybor = odczyt.nextInt();
       
       switch(wybor)
       {
           case 1:
               kalkulator = new ExtendedSquareCalculator();
               obwod = kalkulator.calculatePerimeter();

               kalkulator.calculateArea();
               
               break;
           case 2:
               kalkulator = new ExtendedCircleCalculator();
               obwod = kalkulator.calculatePerimeter();
               kalkulator.calculateArea();
               break;
           case 3:
               kalkulator = new ExtendedTriangleCalculator();
               obwod = kalkulator.calculatePerimeter();
               kalkulator.calculateArea();
               break;
           case 4:
               kalkulator = new ExtendedRectangleCalculator();
               obwod = kalkulator.calculatePerimeter();
               kalkulator.calculateArea();
               break;
               
       }
       System.out.println("Obwod: "+obwod);
      
        
    }
    
}
