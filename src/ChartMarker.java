import java.awt.Color;
import java.util.ArrayList;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.Layer;

/**
 *
 * @author Pawel Lak
 */
public class ChartMarker {

private  XYPlot xyp;
private  JFreeChart jfc;
private  ChartPanel cp;
Marker marker;
private static ChartMarker instance;
    
public static ChartMarker getInstance()
{
    if(instance==null)
    {
        instance = new ChartMarker();
    }
    return instance;
}

/**
 * Initialize ChartMaker tool
 * @param chartPnl 
 */
private ChartMarker() {
}

public void initialization(ChartPanel chartPnl)
{
    this.cp = chartPnl;
    this.jfc = cp.getChart();
    this.xyp =  jfc.getXYPlot();
}

 /**
 * Any data of Leq above marker, are disabled from calculation
 * @param marker 
 */
public void setFlagsMarker(int marker){
    ArrayList<ArrayList<Double>> leqArrays = DataMeter.getInstance().getArrayOfLeq();
    ArrayList<ArrayList<Double>> timeArrays = DataMeter.getInstance().getArrayOfDate();
    ArrayList<Double> leqArray;
    int idx;

    int size = leqArrays.size();
    //Get each of measure series
    for(int i=0;i<size;i++)
    {
     leqArray = leqArrays.get(i);
     idx=leqArray.size();
     //Get each of element from series
     for(int j=0;j<idx;j++)
     {
         //Get ID of element 
         if((leqArrays.get(i).get(j)>=marker))
         {
             if(j==0)
             {
                setMarkerOnChart(timeArrays.get(i).get(j), timeArrays.get(i).get(j)+300);
             }
             else if(j==(idx-1))
             {
                setMarkerOnChart(timeArrays.get(i).get(j)-300, timeArrays.get(i).get(j));   
             }          
             else
             {
                setMarkerOnChart(timeArrays.get(i).get(j)-300, timeArrays.get(i).get(j)+300);   
             }
         }
     }
    }
} 
    
/**
* Set marker on chart
* @param tMarkerStart
* @param tMarkerEnd 
*/ 
public void setMarkerOnChart(Double tMarkerStart, Double tMarkerEnd){
    if (!(tMarkerStart.isNaN() && tMarkerEnd.isNaN())){
        if (tMarkerEnd > tMarkerStart){
            
            //TODO -> Get index of tMarkerStart and check if tMarkerStart is overchart 
            
            
            marker = new IntervalMarker(tMarkerStart, tMarkerEnd);
            marker.setPaint(new Color(255, 0, 0, 255));
            xyp.addDomainMarker(marker,Layer.BACKGROUND);

            Main.getInstance().setTextStartTime(tMarkerStart);
            Main.getInstance().setTextStopTime(tMarkerEnd);
        }
    }
}
}