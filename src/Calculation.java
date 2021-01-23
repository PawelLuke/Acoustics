public class Calculation 
{
    public Calculation(){
    }
       public void updateLaeq()
       {
            var arrayLeqs = DataMeter.getInstance().getArrayOfLeq();
            var arrayFlags =  new MouseMarkerFlags().getMarkerFlags();
            int numerator = 0;
            double avaragePascale = 0;
            double sumeOfPascale =0;
            int sizes = arrayLeqs.size();
            int size;
            double pascale;
            double lq;
            
            for(int i=0;i<sizes;i++){
                var arrayFlag = arrayFlags.get(i);
                size = arrayFlag.size();
                for(int j=0;j<size;j++){
                    if(arrayFlag.get(j)==Boolean.TRUE){
                        lq = arrayLeqs.get(i).get(j);
                        pascale = Math.pow(10, lq / 10);
                        sumeOfPascale += pascale;
                        numerator++;
                    }
                }
            }

            avaragePascale = sumeOfPascale / numerator;
            double dlaeq = 10 * Math.log10(avaragePascale);
            dlaeq = Math.round(dlaeq * 100);
            dlaeq/=100;
            Main.getInstance().setTextLeq(Double.toString(dlaeq));
        }
}