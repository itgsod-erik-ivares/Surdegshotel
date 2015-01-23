package surdegshotell;

/**
 *
 * @author Erik
 */
public class StringHandler {
    public static String convertToString(String[] stringList){
        StringBuilder returnString = new StringBuilder();
        for (String string : stringList) {
            returnString.append(string+";");
        }
        returnString.deleteCharAt(returnString.length()-1);
        return returnString.toString();
    }
    
    public static String fixString(String inputString){
        StringBuilder returnString = new StringBuilder();
        String[] stringList = inputString.split(";");
        for (String string : stringList) {
            returnString.append(string+ " | ");
        }
        return returnString.toString();
    }
}