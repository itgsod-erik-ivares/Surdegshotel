package surdegshotell;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Erik
 */
public class Sorter {
    private static int _price = 30;
    
    public static ArrayList<String> getAllDoughsForToday(){
        ArrayList<String> returnList = new ArrayList();
        ArrayList<String> checkedInDoughs = FileManager.readFile("CheckedIn.txt");
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar today = Calendar.getInstance();
        for (String aDough : checkedInDoughs) {
            String[] dough = aDough.split(";");
            try{
                Date date = formatter.parse(dough[10]);
                Calendar redistration = Calendar.getInstance();
                redistration.setTime(date);
                while(redistration.before(today)){
                    int counter = 0;
                    String regString = formatter.format(redistration.getTime());
                    String todString = formatter.format(today.getTime());
                    if(regString.equals(todString)){
                        String countString = Integer.toString(counter);
                        StringHandler.add(aDough, countString);
                        returnList.add(aDough);
                        System.out.println("dough added to mending");
                        redistration.add(Calendar.DAY_OF_MONTH, Integer.parseInt(dough[5]));
                    }
                    else{
                        redistration.add(Calendar.DAY_OF_MONTH, Integer.parseInt(dough[5]));
                        counter++;
                    }
                }
            }
            catch(ParseException pe){
                System.out.println("Could not parse date");
            }
        }
        return returnList;
    }
    
    public static ArrayList<String> getDoughsFromTo(String from, String to){
        ArrayList<String> returnList = new ArrayList<>();
        ArrayList<String> checkedOutDoughs = FileManager.readFile("CheckedOut.txt");
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar today = Calendar.getInstance();
        try{
            Date dateTo = formatter.parse(to);
            Calendar calTo = Calendar.getInstance();
            calTo.setTime(dateTo);
            Date dateFrom = formatter.parse(from);
            Calendar calFrom = Calendar.getInstance();
            calFrom.setTime(dateFrom);
            calTo.add(Calendar.DAY_OF_MONTH, 1);
            calFrom.add(Calendar.DAY_OF_MONTH, -1);
            for (String adough : checkedOutDoughs) {
                String[] dough = adough.split(";");
                Date date = formatter.parse(dough[11]);
                Calendar checkOut = Calendar.getInstance();
                checkOut.setTime(date);
                while (checkOut.before(calTo)) {
                    if (checkOut.before(calTo) && checkOut.after(calFrom)) {
                        returnList.add(adough);
                    }
                }
            }
        }
        catch(ParseException pe){
            System.out.println("Could not parse date");
        }
        return returnList;
    }
    
    public static ArrayList<String> getBillStatistics(String dough){
        ArrayList<String> returnList = new ArrayList();
        ArrayList<String> checkedOutDoughs = FileManager.readFile("CheckedOut.txt");
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            for (String cod : checkedOutDoughs) {
                String[] codList = cod.split(";");
                String[] dList = dough.split(";");
                
                if (dList[0].charAt(0) == '﻿') {
                    dList[0] = dList[0].replaceFirst("^[﻿]", "");
                }
                if (codList[0].charAt(0) == '﻿') {
                    codList[0] = codList[0].replaceFirst("^[﻿]", "");
                }
                System.out.println("Dough ID: " + dList[0]);
                System.out.println("Currents search item: "+ codList[0]);
                if (codList[0].equals(dList[0])) {
                    System.out.println("Item found!");
                    
                    Date ci = formatter.parse(dList[10]);
                    Calendar cci = Calendar.getInstance();
                    cci.setTime(ci);
                    
                    Calendar ccit = Calendar.getInstance();
                    ccit.setTime(ci);
                    
                    Date co = formatter.parse(codList[11]);
                    Calendar cco = Calendar.getInstance();
                    cco.setTime(co);
                    
                    System.out.println("checking mendings: ");
                    System.out.println(ccit.getTime());
                    
                    int mendCounter = 0;
                    while(ccit.before(cco)){
                        System.out.println(mendCounter);
                        mendCounter++;
                        ccit.add(Calendar.DAY_OF_MONTH, Integer.parseInt(codList[5]));
                    }
                    int daycounter = 0;
                    System.out.println("checking days");
                    System.out.println(cci.getTime());
                    while (cci.before(cco)) {
                        System.out.println(daycounter);
                        daycounter++;
                        cci.add(Calendar.DAY_OF_MONTH, 1);
                    }
                    int cost = daycounter * _price;
                    System.out.println("Price: " + cost);
                    returnList.add(Integer.toString(cost));
                    returnList.add(Integer.toString(mendCounter));
                    
                }
            }
        }
        catch(ParseException pe){
            System.out.println("Could not parse date");
        }
        for (String info : returnList) {
            System.out.println(info);
        }
        return returnList;
    }
}