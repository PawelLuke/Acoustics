import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.Layer;

public class MouseMarker extends MouseAdapter{

        private Marker marker;
        private Double timeMarkerStart = Double.NaN;
        private Double timeMarkerEnd = Double.NaN;
        private final XYPlot xyp;
        private final JFreeChart jfc;
        private final ChartPanel cp;

        public MouseMarker(ChartPanel chartPnl){
            this.cp = chartPnl;
            this.jfc = chartPnl.getChart();
            this.xyp =  jfc.getXYPlot();
        }
        
        private double roundMs(double value){
            return (double) Math.round(value / 1000) * 1000;
        }

        private void getMarker(){
            if (!(timeMarkerStart.isNaN() && timeMarkerEnd.isNaN())) {
                if (timeMarkerEnd > timeMarkerStart) {

                    timeMarkerStart = roundMs(timeMarkerStart);
                    timeMarkerEnd = roundMs(timeMarkerEnd);
                    marker = new IntervalMarker(timeMarkerStart, timeMarkerEnd);
                    marker.setPaint(new Color(255, 0, 0, 255));
                    xyp.addDomainMarker(marker,Layer.BACKGROUND);
                    Main.getInstance().setTextStartTime(timeMarkerStart);
                    Main.getInstance().setTextStopTime(timeMarkerEnd);
                }
            }
        }
        
        private void setFlags(){
            if (!(timeMarkerStart.isNaN() && timeMarkerEnd.isNaN()))            {
                if (timeMarkerEnd > timeMarkerStart)                {
                    double roundStart = roundMs(timeMarkerStart);
                    double roundEnd = roundMs(timeMarkerEnd);
                    new MouseMarkerFlags().setFlagsMarker(roundStart,roundEnd);
                }
            }
        }

        private Double getPosition(MouseEvent e){
            Point2D p = cp.translateScreenToJava2D(e.getPoint());
            Rectangle2D plotArea = cp.getScreenDataArea();
            return xyp.getDomainAxis().java2DToValue(p.getX(), plotArea, xyp.getDomainAxisEdge());
        }

        @Override
        public void mouseReleased(MouseEvent e){
            timeMarkerEnd = getPosition(e);
            getMarker();
            setFlags();
            new Calculation().updateLaeq();
        }

        @Override
        public void mousePressed(MouseEvent e){
            timeMarkerStart = getPosition(e);
        }
    }