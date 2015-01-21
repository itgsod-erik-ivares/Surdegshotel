package surdegshotell;

import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * main class of the Sourdoughotel.
 * @author Erik
 */
public class Surdegshotell {
    
    /**
     * starts the application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new GUI();
    }
    
    public Surdegshotell(){
        
    }
    
    /**
     * 
     * @param input 
     */
    public void checkIn(ArrayList<JTextField> input){
        System.out.println("values tacken in");
        String name = input.get(0).getText();
        String address = input.get(1).getText();
        String number = input.get(2).getText();
        String email = input.get(3).getText();
        String interval = input.get(4).getText();
        String flourType = input.get(5).getText();
        String flourAmount = input.get(6).getText();
        String waterAmount = input.get(7).getText();
        String specialRequest = input.get(8).getText();
        if (Validator.validatePhoneNumber(number) &&
            Validator.validateEMail(email) &&
            Validator.validateInt(interval) &&
            Validator.validateInt(flourAmount) &&
            Validator.validateInt(waterAmount)) {
            System.out.println("values passed validation");
            String[] list = new String[10];
            list[0] = name;
            list[1] = address;
            list[2] = number;
            list[3] = email;
            list[4] = interval;
            list[5] = flourType;
            list[6] = flourAmount;
            list[7] = waterAmount;
            list[8] = specialRequest;
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            list[9] = sdf.format(date);
            FileManager.writeToFile("CheckedIn.txt", StringHandler.convertToString(list));
            System.out.println("values saved to file");
        }
        else {
            throw new InvalidParameterException();
        }
    }
    
    /**
     * 
     * @param sourdough 
     */
    public void checkOut(String sourdough){
        
    }
    
    /**
     * 
     */
    public void mendingDone(){
        
    }
    
    /**
     * 
     * @param dateFrom
     * @param dateTo 
     */
    public void getStatistics(String dateFrom, String dateTo){
        
    }
    
    /**
     * 
     * @param price 
     */
    public void setPrice(String price){
        
    }
}