import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.TimeSeriesCollection;

/**
 * JfreeChart class
 * @author Pawel Lak
 */
public class ChartJfreeChart {
    private  JFreeChart jfc;
    /**
     * JfreeChart is a component of Chart Panel 
     * @param ts
     * @return JFreeChart
     */
    public JFreeChart getJFreeChart(TimeSeriesCollection ts)
    {
        jfc =  ChartFactory.createTimeSeriesChart(Constant.CH_TITLE, Constant.CH_DOMAIN, Constant.CH_RANGE, ts, false, false, false);
        jfc.setAntiAlias(true);
        return jfc;
    }
}