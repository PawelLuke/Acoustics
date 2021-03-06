import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class Function_File 
{
    //path used to select file
    private static JFileChooser jfc;
    Function_File(){}
    /**
    Get path selected by user in open dialog 
    @return path to file
    */
    private void getPath()
    {
         jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    }
    
    /**
    Get file from path
    @return path to file
    */
    public File getFile()
    {
        getPath();
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) 
        {
        return jfc.getSelectedFile();
        }
        return null;
    }
 }