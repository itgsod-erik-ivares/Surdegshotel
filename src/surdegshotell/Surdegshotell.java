package surdegshotell;

import com.sun.javafx.binding.StringFormatter;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * main class of the Sourdoughotel.
 * @author Erik
 */
public class Surdegshotell {
    
    private int _topID = 0; 
    
    /**
     * starts the application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new GUI();
    }
    
    public Surdegshotell(){
        _topID = getTopID();
        Calendar test = Calendar.getInstance();
        test.add(Calendar.MONTH, -1);
        System.out.println(test.getTimeInMillis()+ "");
    }
    
    public int getTopID(){
        int returnInt = 1;
        ArrayList<String> list = FileManager.readFile("CheckedIn.txt");
        for (String string : list) {
            if (string.charAt(0) == '﻿') {
                string = string.replaceFirst("^[﻿]", "");
            }
            String[] splitString = string.split(";");
            if (Integer.parseInt(splitString[0]) > _topID) {
                returnInt = Integer.parseInt(splitString[0]);
            }
        }
        return returnInt;
    }
    
    /**
     * 
     * @param input 
     */
    public void checkIn(ArrayList<JTextField> input){
        System.out.println("Checking in values");
        int id = ++_topID;
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
            String[] list = new String[11];
            list[0] =  "" + id;
            list[1] = name;
            list[2] = address;
            list[3] = number;
            list[4] = email;
            list[5] = interval;
            list[6] = flourType;
            list[7] = flourAmount;
            list[8] = waterAmount;
            list[9] = specialRequest;
            Calendar today = Calendar.getInstance();
            list[10] = Long.toString(today.getTimeInMillis());
            System.out.println(list[10]);
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
        String[] outdough = sourdough.split(";");
        System.out.println("1 test");
        ArrayList<String> checkedInDoughs = FileManager.readFile("CheckedIn.txt");
        for (String dough : checkedInDoughs) {
            System.out.println("2 test");
            if (dough.charAt(0) == '﻿') {
                System.out.println("3 test");
                dough = dough.replaceFirst("^[﻿]", "");
            }
            System.out.println("4 test");
            String[] thisdough = dough.split(";");
            if (outdough[0].equals(thisdough[0])) {
                System.out.println("Sourdough Found!");
                Calendar today = Calendar.getInstance();
                String fixedDough = StringHandler.add(dough, Long.toString(today.getTimeInMillis()));
                System.out.println(fixedDough);
                FileManager.removeLine("CheckedIn.txt", dough);
                FileManager.writeToFile("CheckedOut.txt", fixedDough);
            }
        }
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
    public ArrayList<String> getStatistics(String dateFrom, String dateTo){
        ArrayList<String> returnList = Sorter.getDoughsFromTo(dateFrom, dateTo);
        return returnList;
    }
    
    public ArrayList<String> createBill(String dough){
        ArrayList<String> returnList = Sorter.getBillStatistics(dough);
        for (String info : returnList) {
            System.out.println(info);
        }
        return returnList;
    }
    
    /**
     * 
     * @param price 
     */
    public void setPrice(String price){
        
    }
}