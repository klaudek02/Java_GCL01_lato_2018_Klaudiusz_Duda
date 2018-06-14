package oblugaplikowstrumieni;
import java.io.IOException;
import java.util.Arrays;

public class SystemCacheMain {
    public static void main(String[] args) {
        ExtendedSystemCache cache = new ExtendedSystemCache();
        ExtendedSystemCache cache2 = new ExtendedSystemCache();
        ScatterSystem system = new ScatterSystem();
        double d[] = {1,2,2,33,24,1,4,21,3,2};
        double d2[] = {1,20,0,330,240,1,4,21,3,2};
        Double output = cache.get(d);
        if( output == null)
        {
            output = system.makeOperation( d );
            cache.put( d, output );
            cache2.put(d,output);
        }
        output = cache.get(d2);
        if( output == null)
        {
            output = system.makeOperation( d2 );
            cache.put( d2, output );
            cache2.put(d2,output);
        }
        try{
            cache.exportCSV("plik.csv");
            cache.serialize("text");
        }catch(IOException e){System.out.println(e);}
        try{
            cache.importCSV("plik.csv");
            cache.deserialize("text");
            
        }catch(IOException | ClassNotFoundException e){System.out.print(e);}
        
        /////////////SAVE I LOAD/////////////////////////////
        
        System.out.println("SAVE");
        try{
            cache2.save("binary.data");
        }catch(IOException e){System.out.println(e);}
        cache2.getCache().forEach((key,value)->
                System.out.println(Arrays.toString(key.values)+" "+value));
        System.out.println("LOAD");
        try{
            cache2.load("binary.data");
        }catch(IOException e){System.out.println(e);}
        cache2.getCache().forEach((key,value)->
                System.out.println(Arrays.toString(key.values)+" "+value));
    }
}
