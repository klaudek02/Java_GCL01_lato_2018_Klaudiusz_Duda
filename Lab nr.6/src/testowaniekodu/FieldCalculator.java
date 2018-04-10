package testowaniekodu;

public class FieldCalculator {
    double calculateSquare(double a)
    {
        if(a <= 0)
            throw new IllegalArgumentException("Bok <= 0");
        else
            return a * a;
    }
    double calculateCircle(double r)
    {
        if(r <= 0)
            throw new IllegalArgumentException("Promien <=0");
        else
            return Math.PI * r * r;
    }
    double calculateTriangle(double a, double h)
    {
        if(a <= 0)
            throw new IllegalArgumentException("Bok <= 0");
        else if(h <= 0)
            throw new IllegalArgumentException("Wysokosc <= 0");
        else
            return 0.5 * a * h;
    }
    double calculateRectangle(double a, double b)
    {
        if(a <= 0 || b <= 0)
            throw new IllegalArgumentException("Bok <= 0");
        else
            return a * b;
         
    }
}
