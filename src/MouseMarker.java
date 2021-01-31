import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.XYPlot;

/**
 * Class used to get start and end marker
 * @author Pawel Lak
 */
public class MouseMarker extends MouseAdapter{

        private Marker marker;
        private Double timeMarkerStart = Double.NaN;
        private Double timeMarkerEnd = Double.NaN;
        private final XYPlot xyp;
        private final JFreeChart jfc;
        static private  ChartPanel cp;

        /**
         * Initialization of Mouse marker
         * @param chartPnl 
         */
        public MouseMarker(ChartPanel chartPnl){
            this.cp = chartPnl;
            this.jfc = cp.getChart();
            this.xyp =  jfc.getXYPlot();
        }
        
        public MouseMarker()
        {
            this.jfc = cp.getChart();
            this.xyp =  jfc.getXYPlot();
        }
        
        /**
         * Get position on mouse 
         * @param e
         * @return 
         */
        private Double getPosition(MouseEvent e){
            Point2D p = cp.translateScreenToJava2D(e.getPoint());
            Rectangle2D plotArea = cp.getScreenDataArea();
            return xyp.getDomainAxis().java2DToValue(p.getX(), plotArea, xyp.getDomainAxisEdge());
        }
        /**
         * Handle mouse Released on chart
         * @param e 
         */
        @Override
        public void mouseReleased(MouseEvent e){
            timeMarkerEnd = getPosition(e);
            new DataFlags().setFlagsMarker(timeMarkerStart, timeMarkerEnd);
            
            timeMarkerStart = new Converter().roundMs(timeMarkerStart);
            timeMarkerEnd = new Converter().roundMs(timeMarkerEnd);
            ChartMarker.getInstance().setMarkerOnChart(timeMarkerStart, timeMarkerEnd);
            new Calculation().updateLeq();
        }

        /**
         * Handle mouse Pressed on chart
         * @param e 
         */
        @Override
        public void mousePressed(MouseEvent e){
            timeMarkerStart = getPosition(e);
        }
    }