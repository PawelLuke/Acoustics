import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/**
 * Class used to get file path from user
 * @author Luck
 */
public class Function_File{
     private static JFileChooser jfc;
    Function_File(){}
    /**
    Get path selected by user in open dialog 
    @return path to file
    */
    private void getPath(){
         jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    }
    
    /**
    Get file from path
    @return path to file
    */
    public File getFile(){
        getPath();
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) 
        {
        return jfc.getSelectedFile();
        }
        return null;
    }
    
    /**
    * @param File selected by user
    * @return Return file extension 
    */
    public String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");

        if (lastIndexOf == -1) {
            return ""; // empty extension
        }

        return name.substring(lastIndexOf);
    }
 }