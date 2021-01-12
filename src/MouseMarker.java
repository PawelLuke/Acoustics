import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Date;
import javax.swing.JTextField;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.Layer;

/**
 *
 * @author Luck
 */
public class MouseMarker extends MouseAdapter {

        private Marker marker;
        private Double timeMarkerStart = Double.NaN;
        private Double timeMarkerEnd = Double.NaN;
        private final XYPlot plot;
        private final JFreeChart chart;
        private final ChartPanel panel;

        public MouseMarker(ChartPanel panel) 
        {
            this.panel = panel;
            this.chart = panel.getChart();
            this.plot = (XYPlot) chart.getPlot();
        }
        
        //Return index of specific time in Data and LAeq array
        int indexOf(double time) {
            int index = 0;
            for (var array : DataMeter.dateFromMeter) {
                //array[0] <- data array
                if (array == time) {
                    return index;
                }
                index++;
            }
            return -1;
        }

        void setMarkersOnArrayWithDate() {
            int startIndex = indexOf(timeMarkerStart);
            int lenghtMarker = (int) (timeMarkerEnd - timeMarkerStart) / 1000;

            for (int i = startIndex; i <= lenghtMarker+startIndex; i++) {
                Function_Chart.csvFileFlgs.set(i, Boolean.FALSE);
            }
        }

        private double round(double value) {
            return (double) Math.round(value / 1000) * 1000;
        }

        private void getMarker() {

            if (!(timeMarkerStart.isNaN() && timeMarkerEnd.isNaN())) {
                if (timeMarkerEnd > timeMarkerStart) {

                    //Time rouned to second
                    timeMarkerStart = round(timeMarkerStart);
                    timeMarkerEnd = round(timeMarkerEnd);

                    setMarkersOnArrayWithDate();
                    marker = new IntervalMarker(timeMarkerStart, timeMarkerEnd);
                    marker.setPaint(new Color(255, 0, 0, 255));
                    plot.addDomainMarker(marker,Layer.BACKGROUND);
                    //Update Leq label
                    Calculation.getInstance().updateLaeq();
                    Main.getInstance().setTextStartTime(timeMarkerStart);
                    Main.getInstance().setTextStopTime(timeMarkerEnd);
                }
            }
        }

        private Double getPosition(MouseEvent e) 
        {
            Point2D p = panel.translateScreenToJava2D(e.getPoint());
            Rectangle2D plotArea = panel.getScreenDataArea();
            XYPlot plot = (XYPlot) chart.getPlot();
            return plot.getDomainAxis().java2DToValue(p.getX(), plotArea, plot.getDomainAxisEdge());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            timeMarkerEnd = getPosition(e);
            getMarker();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            timeMarkerStart = getPosition(e);
        }
    }