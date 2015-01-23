package surdegshotell;

import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    }
    
    public int getTopID(){
        int returnInt = 0;
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
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            list[10] = sdf.format(date);
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
        ArrayList<String> checkedInDoughs = FileManager.readFile("CheckedIn.txt");
        for (String dough : checkedInDoughs) {
            String fixedDough = "";
            if (dough.charAt(0) == '﻿') {
                fixedDough = dough.replaceFirst("^[﻿]", "");
            }
            else{
                fixedDough = dough;
            }
            String[] thisdough = fixedDough.split(";");
            if (outdough[0].equals(thisdough[0])) {
                FileManager.removeLine("CheckedIn.txt", dough);
                FileManager.writeToFile("CheckedOut", fixedDough);
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
    public void getStatistics(String dateFrom, String dateTo){
        
    }
    
    /**
     * 
     * @param price 
     */
    public void setPrice(String price){
        
    }
}