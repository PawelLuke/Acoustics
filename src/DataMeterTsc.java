import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class DataMeterTsc 
{
    private static TimeSeriesCollection tsc;
    
    public  TimeSeriesCollection getAndsetTimeSeriesCollection(TimeSeries ts)
    {
        if(tsc==null)
        {
            tsc = new TimeSeriesCollection();
        }
        tsc.addSeries(ts);
        return tsc;
    }
}