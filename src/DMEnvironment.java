import java.util.ArrayList;

public interface DMEnvironment 
{
    public ArrayList<String[]> getCSVFromMeter();
    public ArrayList<Double> setDateArray(ArrayList<String[]> array);
    public ArrayList<Double> setLeqArray(ArrayList<String[]> array);
}