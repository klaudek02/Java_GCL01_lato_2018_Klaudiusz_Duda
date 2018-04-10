package testowaniekodu;

public class BasicCalculator {
    double calculateSum(double a, double b)
    {
        return a + b;
    }
    double calculateDifference(double a, double b)
    {
        return a - b;
    }
    double calculateMultiplication(double a, double b)
    {
        return a * b;
    }
    double calculateDivision(double a, double b)
    {
        if(b == 0)
            throw new IllegalArgumentException("Dzielenie przez zero");
        else
            return a / b;
    }
    double calculatePow(double a, double b)
    {
        if(a == 0 && b < 0)
            throw new IllegalArgumentException("Nieprawidlowe dane werjsciowe (a=0; b < 0)");
        return Math.pow(a,b);
    }
    double calculateSqlr(double a, double b)
    {
        if(a < 0)
            throw new IllegalArgumentException("Pierwiastek liczby ujemnej");
        else if(b == 0)
            throw new IllegalArgumentException("Pierwiastek stopnia 0");
        else
            return Math.pow(a,1.0/b);
    }
    
}
