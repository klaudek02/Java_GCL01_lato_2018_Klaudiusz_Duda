package oblugaplikowstrumieni;

import java.io.ObjectInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class ExtendedSystemCache extends SystemCache implements Serializable{
    public void exportCSV(String path) throws IOException {
        OutputStream stream = new FileOutputStream(new File(path));
        exportCSV(stream);
    }
    public void exportCSV(File file)throws IOException
    {
        OutputStream stream = new FileOutputStream(file);
        exportCSV(stream);
    }
    public void exportCSV(OutputStream stream)throws IOException
    {
        Iterator<Entry<Parameter,Double>> iter = getCache().entrySet().iterator();
        while(iter.hasNext())
        {
            Entry<Parameter, Double> entry = iter.next();
            Parameter parameter = entry.getKey();
            for(double k : parameter.values)
            {
                String liczba =Double.toString(k)+"\n";
                stream.write(liczba.getBytes());  
            }
            String liczba = "\n" + Double.toString(entry.getValue())+"\n---\n";
            stream.write(liczba.getBytes());    
        }
        stream.close();
    }
    public void importCSV(String path)throws IOException
    {
        InputStream stream = new FileInputStream(new File(path));
        importCSV(stream);
    }
    public void importCSV(File file)throws IOException
    {
        InputStream stream = new FileInputStream(file);
        importCSV(stream);
    }
    public void importCSV(InputStream stream)throws IOException
    {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(stream))) {
            String newLine;
            ArrayList<Double> lista = new ArrayList<>();
            double wartosc = 0;
            while((newLine = in.readLine())!=null)
            {
                if(newLine.isEmpty())
                {
                    if((newLine = in.readLine()) != null)
                    {
                        wartosc = Double.parseDouble(newLine);
                    }
                    continue;
                }
                if(newLine.equals("---"))
                {
                    double [] array = new double[lista.size()];
                    for(int i = 0; i < lista.size(); i++)
                    {
                        array[i] = lista.get(i);
                    }
                    put(array,wartosc);
                    lista.clear();
                    continue;
                }
                lista.add(Double.parseDouble(newLine));
            }
            stream.close();
            in.close();
        }
    }
    public void serialize(String path)throws IOException
    {
        serialize(new FileOutputStream(path));
    }
    public void serialize(File file)throws IOException
    {
        serialize(new FileOutputStream(file));
    }
    public void serialize(OutputStream stream)throws IOException
    {
        ObjectOutputStream o = new ObjectOutputStream(stream);
        o.writeObject(this);
        o.close();
        stream.close();  
    }
    public void deserialize(String path)throws IOException, ClassNotFoundException
    {
        deserialize(new FileInputStream(path));
    }
    public void deserialize(File file)throws IOException, ClassNotFoundException
    {
        deserialize(new FileInputStream(file));
    }
    public void deserialize(InputStream stream)throws IOException, ClassNotFoundException
    {
        try (ObjectInputStream i = new ObjectInputStream(stream)) {
            System.out.println("DESERIALIZE:");
            ExtendedSystemCache a = (ExtendedSystemCache) i.readObject();
            a.getCache().forEach((key,value)->
                System.out.println(Arrays.toString(key.values)+" "+value));
            /*
            Iterator<Entry<Parameter,Double>> iter = a.cache.entrySet().iterator();
            System.out.println("//////DESERIALIZE/////");
            while(iter.hasNext())
            {
                Entry<Parameter, Double> entry = iter.next();
                Parameter parameter = entry.getKey();
                for(double k : parameter.values)
                {
                    System.out.println(k);
                }
                System.out.println("VALUE: "+entry.getValue());
            }*/
            stream.close();
            i.close();
            this.cache = a.getCache();
        }
    }
    public void save(String path) throws IOException
    {
       save(new FileOutputStream(path)); 
    }
    public void save(File file) throws IOException
    {
        save(new FileOutputStream(file));
    }
    public void save(OutputStream stream) throws IOException
    {
        try (DataOutputStream data = new DataOutputStream(stream)) {
            Iterator<Entry<Parameter,Double>> iter = getCache().entrySet().iterator();
            while(iter.hasNext())
            {
                Entry<Parameter, Double> entry = iter.next();
                Parameter parameter = entry.getKey();
                int rozmiar = parameter.values.length;
                data.writeInt(rozmiar);
                for(double k : parameter.values)
                {
                    data.writeDouble(k);
                }
                data.writeDouble(entry.getValue());
            }
            stream.close();
            data.close();
            
        }
    }
    public void load(String path) throws IOException
    {
        load(new FileInputStream(path));
    }
    public void load(File file) throws IOException
    {
        load(new FileInputStream(file));
    }
    public void load(InputStream stream) throws IOException
    {
        try (DataInputStream data = new DataInputStream(stream)) {
            while(data.available() > 0)
            {
                int rozmiar = data.readInt();
                double tab[] = new double[rozmiar];
                for(int i = 0; i < rozmiar; i++)
                    tab[i] = data.readDouble();
                double wartosc = data.readDouble();
                put(tab,wartosc);
                
            }
            stream.close();
            data.close();
        }
    }
    
}
