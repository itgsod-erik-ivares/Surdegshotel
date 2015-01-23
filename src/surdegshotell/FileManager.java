package surdegshotell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;
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
    
    public static ArrayList<String> readFile(String filename){
        ArrayList<String> returnList = new ArrayList();
        try{
            Scanner myScanner = new Scanner(new File(filename));
            while (myScanner.hasNextLine()) {
                String line = myScanner.nextLine();
                returnList.add(line);
            }
            myScanner.close();
        }
        catch(FileNotFoundException fnfe){
                System.out.println("File Not found");
        }
        return returnList;
    }
    
    public static void removeLine(String file, String lineToRemove){
    try {

        File inFile = new File(file);

        if (!inFile.isFile()) {
            System.out.println("Parameter is not an existing file");
            return;
        }

        //Construct the new file that will later be renamed to the original filename.
        File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

        BufferedReader br = new BufferedReader(new FileReader(file));
        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

        String line = null;

        //Read from the original file and write to the new
        //unless content matches data to be removed.
        while ((line = br.readLine()) != null) {

            if (!line.trim().equals(lineToRemove)) {
                pw.println(line);
                pw.flush();
            }
        }
        pw.close();
        br.close();

        //Delete the original file
        if (!inFile.delete()) {
            System.out.println("Could not delete file");
            return;
        }

        //Rename the new file to the filename the original file had.
        if (!tempFile.renameTo(inFile)){
          System.out.println("Could not rename file");
        }

    }
    catch (FileNotFoundException ex) {
    ex.printStackTrace();
    }
    catch (IOException ex) {
    ex.printStackTrace();
    }
    }
}
