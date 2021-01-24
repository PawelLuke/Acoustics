import java.util.ArrayList;
public class MouseMarkerFlags 
{
    private static ArrayList<ArrayList<Boolean>> dataMeterFlags = new ArrayList<>();
    Double timeMarkerStart;
    Double timeMarkerEnd;
    
    public void setMarkerFlags(ArrayList<Boolean> array)
    {
        dataMeterFlags.add(array);
    }
    
    public ArrayList<ArrayList<Boolean>> getMarkerFlags()
    {
        return dataMeterFlags;
    }
    
    public void setNewArray(ArrayList<ArrayList<Double>> array)
    {
        ArrayList<Boolean> flags = new ArrayList<>();
        ArrayList<Double> leqArray = array.get(Constant.IDX_LEQ);
         
        int size = leqArray.size();
        for(int i=0;i<size;i++)
        {
            flags.add(Boolean.TRUE);
        }
        
        dataMeterFlags.add(flags);
    }
    
    public void setFlagsMarker(Double timeMarkerStart,Double timeMarkerEnd)
    {
        this.timeMarkerStart = timeMarkerStart;
        this.timeMarkerEnd = timeMarkerEnd;
        int lMarker = (int) (timeMarkerEnd - timeMarkerStart) / 1000;
        
        ArrayList<ArrayList<Double>> dataArray = DataMeter.getInstance().getArrayOfDate();
        int size = dataArray.size();
        int index;
        
        for(int i=0;i<size;i++)
        {
            //find index of specific date in array
            index = indexOf(this.timeMarkerStart, dataArray.get(i));
            
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
    int indexOf(double time,ArrayList<Double> arrayList  )
    {
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