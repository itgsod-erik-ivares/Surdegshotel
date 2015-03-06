package surdegshotell;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * a class used to sort doughs in diffrent ways
 * @author Erik
 * @version 2015-02-01
 */
public class Sorter {
    private static int _price = 30;
    private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * sets the price 
     * @param _price the new price 
     */
    public static void setPrice(int _price) {
        Sorter._price = _price;
    }
    
    /**
     * returns all the doughs that are to be mended today
     * @return ArratList<String> a list with all the doughs
     */
    public static ArrayList<String> getAllDoughsForToday(){
        ArrayList<String> returnList = new ArrayList();
        ArrayList<String> checkedInDoughs = FileManager.readFile("CheckedIn.txt");
        Calendar today = Calendar.getInstance();
        for (String aDough : checkedInDoughs) {
            String[] dough = aDough.split(";");
            String temp = dough[10];
            Date date = new Date(Long.parseLong(temp));
            Calendar checkInDate = Calendar.getInstance();
            checkInDate.setTime(date);
            while(checkInDate.before(today)){
                int counter = 0;
                String regString = formatter.format(checkInDate.getTime());
                String todString = formatter.format(today.getTime());
                if(regString.equals(todString)){
                    String countString = Integer.toString(counter);
                    StringHandler.add(aDough, countString);
                    returnList.add(aDough);
                    checkInDate.add(Calendar.DAY_OF_MONTH, Integer.parseInt(dough[5]));
                }
                else{
                    checkInDate.add(Calendar.DAY_OF_MONTH, Integer.parseInt(dough[5]));
                    counter++;
                }
            }
        }
        return returnList;
    }
    
    /**
     * get all the doughs from the set date to the set datee
     * @param from the date you whant the method to start sorting from
     * @param to the date you whant the methosd to stop sorting at
     * @return ArratList<String> a list with the doughs
     */
    public static ArrayList<String> getDoughsFromTo(String from, String to) throws ParseException {
        ArrayList<String> returnList = new ArrayList<>();
        ArrayList<String> checkedOutDoughs = FileManager.readFile("CheckedOut.txt");
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
            Date date = new Date(Long.parseLong(dough[11]));
            Calendar checkOut = Calendar.getInstance();
            checkOut.setTime(date);
            if (checkOut.before(calTo) && checkOut.after(calFrom)) {
                returnList.add(adough);
            }
        }
        return returnList;
    }
    
    /**
     * gets the price and mendings of the dough
     * @param dough the dough that you whant the statistics from
     * @return AraryList<String> a list with the information index 0 = price and index 1 = mendings
     */
    public static ArrayList<String> getBillStatistics(String dough){
        ArrayList<String> returnList = new ArrayList();
        ArrayList<String> checkedOutDoughs = FileManager.readFile("CheckedOut.txt");
        for (String cod : checkedOutDoughs) {
            String[] codList = cod.split(";");
            String[] dList = dough.split(";");
            if (codList[0].equals(dList[0])) {
                Date ci = new Date(Long.parseLong(dList[10]));
                Calendar cci = Calendar.getInstance();
                cci.setTime(ci);
                Calendar ccit = Calendar.getInstance();
                ccit.setTime(ci);
                Date co = new Date(Long.parseLong(codList[11]));
                Calendar cco = Calendar.getInstance();
                cco.setTime(co);
                int mendCounter = 0;
                while(ccit.before(cco)){
                    mendCounter++;
                    ccit.add(Calendar.DAY_OF_MONTH, Integer.parseInt(codList[5]));
                }
                int daycounter = 0;
                while (cci.before(cco)) {
                    daycounter++;
                    cci.add(Calendar.DAY_OF_MONTH, 1);
                }
                int cost = daycounter * _price;
                returnList.add(Integer.toString(cost));
                returnList.add(Integer.toString(mendCounter));
            }
        }
        return returnList;
    }
}