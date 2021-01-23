import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleEdge;

public class Function_Chart {
    
    private static Function_Chart instance;


    static RegularTimePeriod beginOfMeasure;
    static javax.swing.JTextField myLaeq;
    static RectangleEdge eg;
    private static ChartPanel cp;
    private static JFreeChart jfc;
    
    public static Function_Chart getInstance()
    {
        if(instance==null)
        {
            instance = new Function_Chart();
        }
        return instance;
    }
    
    private Function_Chart(){
    }
    
//    public void setFlags(int amount)
//    {
//        for(int i=0;i<amount;i++)
//        {
//           //csvFileFlgs.add(true); 
//        }
//    }
    
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
    
//    public void setMarker()
//    {
//        var xyp = jfc.getXYPlot();
//        double start = (double) 1607894377E12;
//        double stop = (double)1607894380E12;
//        Marker marker = new IntervalMarker(start,stop);
//        
//        xyp.addDomainMarker(marker,Layer.BACKGROUND);
//    }
}