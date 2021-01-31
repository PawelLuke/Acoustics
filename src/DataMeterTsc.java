import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class DataMeterTsc 
{
    /**
     * Static is required to display more than one TimeSeries on chart
     */
    private static TimeSeriesCollection tsc;
    
    /**
     * put TimeSerieis and get TimeSeriesCollection. TSC rememer all TimeSeries added
     * @param ts
     * @return TimeSeriesCollection
     */
    public  TimeSeriesCollection getAndsetTimeSeriesCollection(TimeSeries ts){
        if(tsc==null)
        {
            tsc = new TimeSeriesCollection();
        }
        tsc.addSeries(ts);
        return tsc;
    }
}