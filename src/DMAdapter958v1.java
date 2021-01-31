import java.io.BufferedReader   ;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.jfree.data.time.RegularTimePeriod;

/**
 * Get time and Leq from Svan 958
 * @author Pawel Lak
 */
public class DMAdapter958v1 implements DMEnvironment
{   /**
    * get data located in CSV file
    * @return 
    */
    @Override
    public ArrayList<String[]> getCSVFromFile(){
        ArrayList<String[]> array = new ArrayList<>();
        File file = new Function_File().getFile();
        String extension = new Function_File().getFileExtension(file);
        extension = extension.toLowerCase();
         
         if(extension.equals(".csv"))
         {
           array = getDataArrayCsv(file);
         }
         
         return array;
    }

    /**
     * Set and Get array of Data from CSV file
     * @param arrayCsv
     * @return 
     */
    @Override
    public ArrayList<Double> getDateArray(ArrayList<String[]> arrayCsv){
        ArrayList<Double> dateMsArray = new ArrayList<>();
        double timeInMs;
         for (int i = Constant.IDX_START_CSV; i < arrayCsv.size(); i++) 
         {
             String[] tab = arrayCsv.get(i);
             RegularTimePeriod rtp = new Converter().getTimeInMsFromRow(tab[Constant.IDX_DATE_CSV]);
             timeInMs = (double)rtp.getFirstMillisecond();
             dateMsArray.add(timeInMs);
             
         }
         DataMeter.getInstance().setArrayOfDate(dateMsArray);
         return dateMsArray;
    }
    /**
     * Get array of Leq from CSV file and Set it to data base
     * @param arrayCsv
     * @return 
     */
    @Override
    public ArrayList<Double> getLeqArray(ArrayList<String[]> arrayCsv){
        ArrayList<Double> LeqMsArray = new ArrayList<>();
        String[] tab;
        
         for (int i = Constant.IDX_START_CSV; i < arrayCsv.size(); i++) 
         {
             tab = arrayCsv.get(i);
             LeqMsArray.add(Double.parseDouble(tab[Constant.IDX_LEQ_CSV]));
         }
         
         DataMeter.getInstance().setArrayOfLeq(LeqMsArray);
         return LeqMsArray;
    }
     
      /**
      * Set File and get file in CSV format
      * @param file
      * @return Array with data from meter in CSV format
      */
        private ArrayList<String[]> getDataArrayCsv(File file){
            ArrayList<String[]> csvFromMeter = new ArrayList<>();

            try
            {
            BufferedReader csvReader = new BufferedReader(new FileReader(file));
            String row;
            while ((row = csvReader.readLine())!= null)
            {
                csvFromMeter.add(row.split(Constant.CSV_SPLIT));
            }
            csvReader.close();
            } 
            catch (IOException ex) 
            {
                System.out.println(ex.toString());
            }
            return csvFromMeter;
        }
}