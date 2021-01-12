public class Calculation 
{
    private static Calculation instance;
    private Calculation(){
    }
    
    public static Calculation getInstance(){
        if(instance==null)
        {
            instance = new Calculation();
        }
        return instance;
    }
    
           public void updateLaeq(){
            var data = DataMeter.LeqFromMeter;
            int numerator = 0;
            double avaragePascale = 0;

            for (int i = 0; i < data.size(); i++) 
            {
                if (Function_Chart.csvFileFlgs.get(i) == true) {
                    double laeq = data.get(i);
                    double tmpLaeq = laeq;
                    double pascale = Math.pow(10, tmpLaeq / 10);
                    avaragePascale += pascale;
                    numerator++;
                }
            }
            avaragePascale = avaragePascale / numerator;
            double dlaeq = 10 * Math.log10(avaragePascale);
            dlaeq = Math.round(dlaeq * 100);
            dlaeq/=100;
            Main.getInstance().setTextLaeq(Double.toString(dlaeq));
        }
}