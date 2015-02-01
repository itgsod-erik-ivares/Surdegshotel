package surdegshotell;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * a class used to validate different types of data
 * @author Erik
 * @version 2015-02-01
 */
public class Validator {
    
    /**
     * validates if a string is parseble into an int
     * @param integer the string that reresent an integer
     * @return true if the string was parseble
     */
    public static boolean validateInt(String integer){
        boolean returnValue = false;
        
        try{
            Integer.parseInt(integer);
            System.out.println("int: OK");
            returnValue = true;
        }
        catch(NumberFormatException nfe){
            System.out.println("int: fail");
            returnValue = false;
        }
      return returnValue;
    }
    
    /**
     * validates if a String is a valid phonenumber
     * @param phoneNumber the string to be validated
     * @return true if the string was valid
     */
    public static boolean validatePhoneNumber(String phoneNumber){
        boolean returnValue = false;
        try{
            Integer.parseInt(phoneNumber);
            if (phoneNumber.length() > 5 && phoneNumber.length() < 15) {
                System.out.println("number: OK");
                returnValue = true;
            }
        }
        catch(NumberFormatException nfe){
            System.out.println("number: fail");
            returnValue = false;
        }
        
        return returnValue;
    }
    
    /**
     * validates a string to se if it was a valid email address
     * @param email the string to be validated
     * @return true if the string was a valid email
     */
    public static boolean validateEMail(String email){
        System.out.println(email);
        boolean returnValue = false;
        Pattern emailPattern;
        emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        matcher = emailPattern.matcher(email);
        if (matcher.matches()) {
            System.out.println("email: OK");
            returnValue = true;
        }
        else{
            System.out.println("email: fail");
        }
        return returnValue;
    }
    
}