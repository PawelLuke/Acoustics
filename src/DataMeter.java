import java.util.ArrayList;

public class DataMeter {
    
   private static DataMeter instance; 
   private ArrayList<ArrayList<Double>> arrayOfdate = new ArrayList<>();
   private ArrayList<ArrayList<Double>> arrayOfLeq = new ArrayList<>();
   private ArrayList<ArrayList<String[]>> csvSeriesFromMeter = new ArrayList<>();  
   private double startChart = Double.MAX_VALUE;
   private double stopChart =0; 
    
   private DataMeter(){
   }
   
   public static DataMeter getInstance()
   {
       if(instance==null)
       {
           instance= new DataMeter();
       }
        return instance;
    }
     
   public void setNewStartTime(double time)
   {
       startChart=time;
   }
   
   public void setNewEndTime(double time)
   {
       startChart=time;
   }
   
   public double getNewStartTime()
   {
       return startChart;
   }
   
   public double getNewEndTime()
   {
       return stopChart;
   }
   
    public void setArrayOfDate(ArrayList<Double> element)
    {
        arrayOfdate.add(element);
    }
     
     public void setArrayOfLeq(ArrayList<Double> element)
     {
         arrayOfLeq.add(element);
     }
     
     public void setArrayOfCsvSeries(ArrayList<String[]> array)
     {
         csvSeriesFromMeter.add(array);
     }
     
     public ArrayList<ArrayList<Double>> getArrayOfLeq()
     {
         return arrayOfLeq;
     }
     
     public ArrayList<ArrayList<Double>> getArrayOfDate()
     {
         return arrayOfdate;
     }
     
     public ArrayList<ArrayList<String[]>> getArrayOfCsvSeries()
     {
         return csvSeriesFromMeter;
     }
}