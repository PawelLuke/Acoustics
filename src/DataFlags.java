import java.util.ArrayList;
public class DataFlags 
{
    private static ArrayList<ArrayList<Boolean>> dataMeterFlags = new ArrayList<>();
    Double timeMarkerStart;
    Double timeMarkerEnd;
    
    /**
     * Add new array flags for new measure series
     * @param array 
     */
    public void setArraysFlags(ArrayList<Boolean> array){
        dataMeterFlags.add(array);
    }
    
    /**
     * Get all arrays flags
     * @return 
     */
    public ArrayList<ArrayList<Boolean>> getArraysFlags(){
        return dataMeterFlags;
    }
    
    /**
     * Add new array flags for data meter
     * @param array 
     */
    public void setFlagsArray(ArrayList<ArrayList<Double>> array){
        ArrayList<Boolean> flags = new ArrayList<>();
        ArrayList<Double> dateArray = array.get(Constant.IDX_DATE);
         
        int size = dateArray.size();
        
        for(int i=0;i<size;i++)
        {
            flags.add(Boolean.TRUE);
        }
        
        setArraysFlags(flags);
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
                 //set flag marker for calculation
                 setFlagsMarker(timeArrays.get(i).get(j));
             }
         }
        }
    } 
    
    /**
     * Set flag marker for one sample of Leq
     * @param tMarkerStart 
     */
    private void setFlagsMarker(Double tMarkerStart){
        ArrayList<ArrayList<Double>> dataArray = DataMeter.getInstance().getArrayOfDate();
        int size = dataArray.size();
        int index;
        
        for(int i=0;i<size;i++)
        {
            //find index of specific date in array
            index = indexOf(tMarkerStart, dataArray.get(i));
            
            if(index!=-1)
            {
               var arrayFlags = getArraysFlags().get(i);
               arrayFlags.set(index, Boolean.FALSE);
            }
        }
    }
    
    /**
     * Set flags marker from Start time to End time
     * @param timeMarkerStart
     * @param timeMarkerEnd 
     */
    public void setFlagsMarker(Double tMarkerStart,Double tMarkerEnd) {
        tMarkerStart = new Converter().roundMs(tMarkerStart);
        tMarkerEnd = new Converter().roundMs(tMarkerEnd);
        int lMarker = ((int) (tMarkerEnd - tMarkerStart) / 1000)+1;
        ArrayList<ArrayList<Double>> dataArray = DataMeter.getInstance().getArrayOfDate();
        int size = dataArray.size();
        int index;
        
        for(int i=0;i<size;i++)
        {
            //find index of specific date in array
            index = indexOf(tMarkerStart, dataArray.get(i));
            
            if(index!=-1)
            {
               var arrayFlags = dataMeterFlags.get(i);
               
               for(int j=index;j<(index+lMarker);j++)
               {
                    arrayFlags.set(j, Boolean.FALSE);
               }
            }
        }
    }
    
     /**
      * Return index of specific time in Date array
      * @param time
      * @param arrayList
      * @return 
      */
    int indexOf(double time,ArrayList<Double> arrayList  ){
        int index = 0;

        for (var item : arrayList) 
        {
            if (item == time)
            {
                return index;
            }
            index++;
        }
        return -1;
    }
}