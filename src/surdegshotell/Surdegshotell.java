package surdegshotell;

import com.sun.javafx.binding.StringFormatter;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * main class of the Sourdoughotel.
 * @author Erik
 * @verision 2015-02-01
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
    
    /**
     * cunstructor for sourdough class
     */
    public Surdegshotell(){
        
        FileManager.writeToFile("CheckedIn.txt", "8;Erik Ivares;Musserongången 215;0707512099;kirre_2008@live.se;2;Vetemjöl special;70;40;Skakas;1419664498596");
        FileManager.writeToFile("CheckedIn.txt", "10;Erik Ivares;Musserongången 215;0707512099;kirre_2008@live.se;2;Vete mjöl;20;12;Special Request;1422360765756");
        FileManager.writeToFile("CheckedIn.txt", "11;Erik Ivares;mussronggf;00707512099;kirre_2008@live.se;2;Vetemjöl;200;20;;1422446430567");
        FileManager.writeToFile("CheckedIn.txt", "9;Erik Ivares;Musserongången 215;0707512099;kirre_2008@live.se;2;Vetemjöl special;70;40;Skakas;1419664498596");
        FileManager.writeToFile("CheckedIn.txt", "12;Erik Ivares;Musserongången 215;0707512099;kirre_2008@live.se;2;Vete mjöl;20;12;Special Request;1422360765756");
        FileManager.writeToFile("CheckedIn.txt", "13;Erik Ivares;mussronggf;00707512099;kirre_2008@live.se;2;Vetemjöl;200;20;;1422446430567");
        FileManager.writeToFile("CheckedIn.txt", "14;Erik Ivares;Musserongången 215;0707512099;kirre_2008@live.se;2;Vetemjöl special;70;40;Skakas;1419664498596");
        FileManager.writeToFile("CheckedOut.txt", "10;Name;Address;1234567890;E-Mail@mail.com;1;Flour;200;100;Special Request;1422345794527;1422346133339");
        FileManager.writeToFile("CheckedOut.txt", "12;Name;Address;1234567890;E-Mail@mail.com;1;Flour;200;100;Special Request;1422345796080;1422346218794");
        FileManager.writeToFile("CheckedOut.txt", "13;Name;Address;1234567890;E-Mail@mail.com;1;Flour;200;100;Special Request;1422345797442;1422346379191");
        _topID = getTopID();
        Calendar test = Calendar.getInstance();
        test.add(Calendar.MONTH, -1);
        System.out.println(test.getTimeInMillis()+ "");
    }
    
    /**
     * gets the top id of the inchecked doughs
     * @return the highest id
     */
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
     * checks in the given information as a dough
     * @param input the information needed to create a dough
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
     * checks out the given dough
     * @param sourdough the dough you want to check out
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
     * sends emails to all the owners of the mended doughs
     * @param todaysDoughs a list with the doughs that where mended today
     * @throws AddressException
     * @throws MessagingException 
     */
    public void mendingDone(ArrayList<String> todaysDoughs) throws AddressException, MessagingException{
        for (String dough : todaysDoughs){
            String[] splitdough = dough.split(";");
            String emailMessage = "Hey " + splitdough[1] + ", We Just mended you dough!\n"
                    + "We have feeded it with " + splitdough[7] + "gr of \"" + splitdough[6] + "\" and "
                    + splitdough[8] + "ml of water.\n";
            if (splitdough[9].length() > 1) {
                emailMessage = emailMessage + "we also performed you special request.";
            }
            GoogleMail.Send(splitdough[4], "SourdoughHotel! We mended your dough", emailMessage);
        }
    }
    
    /**
     * gets all the statistics from the specified date to the other spacified date. 
     * @param dateFrom the date you want statistics from
     * @param dateTothe date you wha statistics to
     * @return ArratList<String> a list with the statistics
     */
    public ArrayList<String> getStatistics(String dateFrom, String dateTo){
        ArrayList<String> returnList = Sorter.getDoughsFromTo(dateFrom, dateTo);
        return returnList;
    }
    
    /**
     * creates a list with the information given by the sorter class
     * @param dough the dough you want statistics on
     * @return a list with the information
     */
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
        try{
            Sorter.setPrice(Integer.parseInt(price));
        }
        catch(Exception e){
            System.out.println("could not set price");
        }
    }
}