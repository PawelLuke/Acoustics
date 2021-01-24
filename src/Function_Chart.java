import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleEdge;

public class Function_Chart {
    
    private Function_Chart(){
    }
    private static Function_Chart instance;

    static RectangleEdge eg;
    private  ChartPanel cp;
    private  JFreeChart jfc;
    
    public static Function_Chart getInstance()
    {
        if(instance==null)
        {
            instance = new Function_Chart();
        }
        return instance;
    }
    
    public JFreeChart getJFreeChart(TimeSeriesCollection ts)
    {
        jfc =  ChartFactory.createTimeSeriesChart("LAeq", "Godzina", "LAeq dB", ts, false, false, false);
        jfc.setAntiAlias(true);
        return jfc;
    }
    
    public ChartPanel getChartPanel(JFreeChart jfc)
    {
     cp = new ChartPanel(jfc);
     return cp;
    }
}