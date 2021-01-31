/**
 * Simple math calculation
 * @author Luck
 */
public class Calculation 
{
    public Calculation(){
    }
       /**
       *  Calculate Leq level from Array with Leq data. Allow for marker flags.
       */
       public void updateLeq(){
            var arrayLeqs = DataMeter.getInstance().getArrayOfLeq();
            var arrayFlags =  new DataFlags().getArraysFlags();
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
                    //check if Leq!=null
                    boolean flags = arrayFlag.get(j);
                    boolean leq = (arrayLeqs.get(i).get(j)!=null);
                    
                    if(flags&&leq){
                        lq = arrayLeqs.get(i).get(j);
                        pascale = Math.pow(10, lq / 10);
                        sumeOfPascale += pascale;
                        numerator++;
                    }
                }
            }
            
            if(numerator!=0)
            {
                avaragePascale = sumeOfPascale / numerator;
            }
            
            double dlaeq = 10 * Math.log10(avaragePascale);
            dlaeq = Math.round(dlaeq * 100);
            dlaeq/=100;
            //Set new result on screen
            Main.getInstance().setTextLeq(Double.toString(dlaeq));
        }
}