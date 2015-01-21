package surdegshotell;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Erik
 */
public class Validator {
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
    
    public static boolean validatePhoneNumber(String phoneNumber){
        boolean returnValue = false;
        try{
            Integer.parseInt(phoneNumber);
            if (phoneNumber.length() > 5 && phoneNumber.length() < 14) {
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