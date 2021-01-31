import java.util.ArrayList;

/**
 * all data getted from CSV file
 * @author Pawel Lak
 */
public class DataMeter {
    
   private static DataMeter instance; 
   private ArrayList<ArrayList<Double>> arrayOfdate = new ArrayList<>();
   private ArrayList<ArrayList<Double>> arrayOfLeq = new ArrayList<>();
   private ArrayList<ArrayList<String[]>> csvSeriesFromMeter = new ArrayList<>();  
    
   private DataMeter(){}
   /**
    * get instance of DataMeter, only one instance is allowed
    * @return 
    */
   public static DataMeter getInstance()
   {
       if(instance==null)
       {
           instance= new DataMeter();
       }
        return instance;
    }
     
   /**
    * set new array of date to array
    * @param element 
    */
    public void setArrayOfDate(ArrayList<Double> element)
    {
        arrayOfdate.add(element);
    }
   /**
    * set new array of Leq to array
    * @param element 
    */     
     public void setArrayOfLeq(ArrayList<Double> element)
     {
         arrayOfLeq.add(element);
     }
    /**
    * set new data to array from CSV file, date and leq only
    * @param element 
    */
     public void setArrayOfCsvSeries(ArrayList<String[]> array)
     {
         csvSeriesFromMeter.add(array);
     }
     /**
      * get all array with leq data
      * @return 
      */
     public ArrayList<ArrayList<Double>> getArrayOfLeq()
     {
         return arrayOfLeq;
     }
     /**
      * get all array with date data
      * @return 
      */
     public ArrayList<ArrayList<Double>> getArrayOfDate()
     {
         return arrayOfdate;
     }
     /**
      * get all array with CSV file, date and leq only 
      * @return 
      */
     public ArrayList<ArrayList<String[]>> getArrayOfCsvSeries()
     {
         return csvSeriesFromMeter;
     }
}