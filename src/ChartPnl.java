import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.TimeSeriesCollection;
/**
 * Function used to display data on Chart
 * @author Pawel Lak
 */
public class ChartPnl {
    
    private ChartPnl(){
    }
    private static ChartPnl instance;

    /**
     * get instance of function chart
     * @return 
     */
    public static ChartPnl getInstance()
    {
        if(instance==null)
        {
            instance = new ChartPnl();
        }
        return instance;
    }
    
    private  ChartPanel cp;
    
    public ChartPanel getChartPanel(JFreeChart jfc)
    {
     cp = new ChartPanel(jfc);
     return cp;
    }
}