import java.util.ArrayList;

/**
 * Design pattern - Adapter
 * @author Pawel Lak
 */
public interface DMEnvironment{
    public ArrayList<String[]> getCSVFromFile();
    public ArrayList<Double> getDateArray(ArrayList<String[]> array);
    public ArrayList<Double> getLeqArray(ArrayList<String[]> array);
}