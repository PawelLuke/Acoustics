import java.util.ArrayList;
import java.util.Date;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;

public class DataMeterTs 
{
    /**
     * Get time series and put it in Time Series Collection
     * @param timeArray
     * @param leqArray
     * @return 
     */
    public TimeSeries getTimeSeries(ArrayList<Double> timeArray, ArrayList<Double> leqArray)
    {
        double timeInms;
        double leq;
        RegularTimePeriod rtp;
        TimeSeries ts = new TimeSeries(Constant.SERIES_NULL);
        
        for(int i=0;i<timeArray.size();i++)
        {
            timeInms=timeArray.get(i);
            Date date = new Date((long) timeInms);
            rtp = new Millisecond(date);
            leq = leqArray.get(i);
            ts.add(rtp, leq);
        }
        
        return ts;
    }
}