import java.text.SimpleDateFormat;
import java.util.Date;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Second;
import org.jfree.data.time.Year;

/**
 * Convert one data for another
 * @author Pawel Lak
 */
public class Converter{
   /**
     * Convert time in "20.01.1970 13:30" format to milisecond
     * @param date
     * @return 
     */
   public RegularTimePeriod getTimeInMsFromRow(String date) {
        if (date == null)
        {
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
   /**
    * Convert ms to date in format "HH:mm:ss"
    * @param timeInMs
    * @return 
    */
   public String msToDate(double timeInMs){
       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
       return sdf.format(new Date((long) timeInMs));
   }
   /**
    * Round ms to full second, and convert it to ms
    * @param value
    * @return 
    */
   public double roundMs(double value){
       return (double) Math.round(value / 1000) * 1000;
   }
}