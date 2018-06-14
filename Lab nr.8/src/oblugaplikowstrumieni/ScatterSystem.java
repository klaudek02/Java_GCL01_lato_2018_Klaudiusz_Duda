package oblugaplikowstrumieni;

public class ScatterSystem {
    
    double makeOperation(double [] input)
    {
        double suma = 0;
        for(double liczba : input)
            suma+=liczba;
        return suma;        
    }
}
