import java.util.ArrayList;
import java.util.Date;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;

public class DataMeterTs 
{
    /**
     * put here array with time in ms and Leq value
     * @param timeArray
     * @param leqArray
     * @return TimeSeries, set it to TimeSeriesCollection 
     */
    public TimeSeries getTimeSeries(ArrayList<Double> timeArray, ArrayList<Double> leqArray){
        double timeInms;
        double leq;
        RegularTimePeriod rtp;
        TimeSeries ts = new TimeSeries(Constant.SERIES_NULL);
        
        for(int i=0;i<timeArray.size();i++)
        {
            timeInms=timeArray.get(i);
            Date date = new Date((long) timeInms);
            rtp = new Millisecond(date);
            
            if(leqArray.get(i)!= null)
            {
                leq = leqArray.get(i);
                ts.addOrUpdate(rtp, leq);
            }else
            {
                 ts.addOrUpdate(rtp, null);
            }
        }
        return ts;
    }
}