package surdegshotell;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
/**
 *
 * @author Erik
 */
public class FileManager {
    public static void writeToFile(String file, String line){
        BufferedWriter output;
        try{
            output = new BufferedWriter(new FileWriter(file, true));  //clears file every time
            output.append(line);
            output.newLine();
            output.close();
        }
        catch(IOException ioe){
            System.out.println("Chould not find file!!!");
        }
    }
}
