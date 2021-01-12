import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleEdge;
/**
 *
 * @author Luke Skywerpo
 */
public class Function_Chart {

    public static List<Boolean> csvFileFlgs = new ArrayList<>();
    static RegularTimePeriod beginOfMeasure;
    static javax.swing.JTextField myLaeq;
    static RectangleEdge eg;
    private ChartPanel chartPanel;
    
    Function_Chart() 
    {
     TimeSeriesCollection dataset = getReadyDataToChart();
     JFreeChart chart = Function_Chart.createChart(dataset);
     chartPanel = new ChartPanel(chart);
     chartPanel.addMouseListener(new MouseMarker(chartPanel));
    }

    Function_Chart(javax.swing.JTextField _laeq) {
        myLaeq = _laeq;
    }

    public ChartPanel getChartPanel()
    {
        return chartPanel;
    }

public static JPanel createMyPanel() {
    final JFreeChart chart = createChart(new Function_Chart().getReadyDataToChart());
    final ChartPanel panel = new ChartPanel(chart);
    panel.setRangeZoomable(false);
    panel.setDomainZoomable(false);
    return panel;
}

    RegularTimePeriod getTimeFromCSVFile(String date) {
        //TODO check format of date, if not correct, turn defult value
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

    TimeSeriesCollection getReadyDataToChart() {
        TimeSeries series1 = new TimeSeries("Data");

        double timeInms;
        double leq;
        RegularTimePeriod rtp;
        
        for(int i=0;i<DataMeter.LeqFromMeter.size();i++)
        {
            timeInms=DataMeter.dateFromMeter.get(i);
            Date date = new Date((long) timeInms);
            rtp = new Millisecond(date);
            leq = DataMeter.LeqFromMeter.get(i);
            series1.add(rtp, leq);
        }
        
        

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series1);
        return dataset;
    }

    static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart("LAeq", "Godzina", "LAeq dB", dataset, false, false, false);
        chart.setAntiAlias(true);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setBaseShapesVisible(false);
        renderer.setBaseShapesFilled(false);
        return chart;
    }
}