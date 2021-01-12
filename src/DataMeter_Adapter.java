import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Second;
import org.jfree.data.time.Year;

/**
 *
 * @author mgr inz. Pawel Lak
 */

public class DataMeter_Adapter implements DataMeter_Svantek958
{
    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");

        if (lastIndexOf == -1) {
            return ""; // empty extension
        }

        return name.substring(lastIndexOf);
    }
     
     public boolean getDataFromMeter()
     {
         boolean flags=false;
         File file = new Function_File().getFile();
         String extension = getFileExtension(file);
         extension = extension.toLowerCase();
         if(extension.equals(".csv"))
         {
           flags = getDataArraycsv(file);
         }
         
         if(flags)
         {
             splitcsvToDateAndLAeq();
         }
         return flags;
     }

    @Override
    public boolean getDataArraycsv(File file) 
    {
        try {
        BufferedReader csvReader = new BufferedReader(new FileReader(file));
        String row;
        while ((row = csvReader.readLine()) != null) {
            DataMeter.csvFromMeter.add(row.split(Constant.CSV_SPLIT));
        }
        csvReader.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        
        return !DataMeter.csvFromMeter.isEmpty();
    }
    
    void setFlagsForChart()
    {
        Function_Chart.csvFileFlgs.add(Boolean.TRUE);
    }
    
    void splitcsvToDateAndLAeq() {

        double[] dtmp;
        for (int i = 5; i < DataMeter.csvFromMeter.size(); i++) {
            dtmp = new double[DataMeter.csvFromMeter.get(i).length - 1];
            for (int j = 1; j < 3; j++) {
                if (j == 1) {
                    //time in ms
                    DataMeter.dateFromMeter.add((double)getTimeInMsFromDate(DataMeter.csvFromMeter.get(i)[j]).getFirstMillisecond());
                }
                if (j == 2) {    //Laeq
                    DataMeter.LeqFromMeter.add(Double.parseDouble(DataMeter.csvFromMeter.get(i)[j]));
                }
            }
            setFlagsForChart();
        }
    }
    
     RegularTimePeriod getTimeInMsFromDate(String date) {

         if (date == null) {
            return new Second(0, 0, 15, 1, 1, 2020);
        }

        String second = new String();
        String minute = new String();
        String hour = new String();
        String day = new String();
        String month = new String();
        String year = new String();

        int intSecond;
        int intMinute;
        int intHour;
        int intDay;
        int intMonth;
        int intYear;

        for (int i = 0; i < date.length(); i++) {
            switch (i) {
                case 0, 1 ->
                    day = day + date.charAt(i);
                case 3, 4 ->
                    month = month + date.charAt(i);
                case 6, 7, 8, 9 ->
                    year = year + date.charAt(i);
                case 11, 12 ->
                    hour = hour + date.charAt(i);
                case 14, 15 ->
                    minute = minute + date.charAt(i);
                case 17, 18 ->
                    second = second + date.charAt(i);
                default -> {
                }
            }
        }

        intDay = Integer.parseInt(day);
        intMonth = Integer.parseInt(month);
        intYear = Integer.parseInt(year);
        intHour = Integer.parseInt(hour);
        intMinute = Integer.parseInt(minute);
        intSecond = Integer.parseInt(second);

        RegularTimePeriod tmpYear = new Year();
        int intCurrentlyYear = Integer.parseInt(tmpYear.toString());

        if (!(intDay <= 31 && intDay >= 1)) {
            intDay = 1;
        }
        if (!(intMonth <= 12 && intMonth >= 1)) {
            intMonth = 1;
        }
        if (!(intYear <= intCurrentlyYear && intYear >= 1970)) {
            intYear = 2020;
        }
        if (!(intHour <= 24 & intHour >= 0)) {
            intHour = 15;
        }
        if (!(intMinute <= 59 && intMinute >= 0)) {
            intMinute = 1;
        }
        if (!(intSecond <= 59 && intDay >= 0)) {
            intSecond = 1;
        }

        return new Millisecond(0, intSecond, intMinute, intHour, intDay, intMonth, intYear);
    }
}