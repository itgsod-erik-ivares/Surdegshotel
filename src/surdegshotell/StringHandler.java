package surdegshotell;

/**
 * a class used to manage strings to be more easily used by the rest of the program
 * @author Erik
 * @version 2015-02-01
 */
public class StringHandler {
    
    /**
     * converts a list of strings into a string seperated by semicolons 
     * @param stringList the list of strings to be converted
     * @return the finished string
     */
    public static String convertToString(String[] stringList){
        StringBuilder returnString = new StringBuilder();
        for (String string : stringList) {
            returnString.append(string+";");
        }
        returnString.deleteCharAt(returnString.length()-1);
        return returnString.toString();
    }
    
    /**
     * fixses a string to be more user friendly
     * @param inputString the string to be fixed
     * @return the fixed strirng
     */
    public static String fixString(String inputString){
        StringBuilder returnString = new StringBuilder();
        String[] stringList = inputString.split(";");
        for (String string : stringList) {
            returnString.append(string+ " | ");
        }
        return returnString.toString();
    }
    
    /**
     * adds one string to another
     * @param string the string to be in the start of the new string
     * @param add the addition that will be in the end of the new string
     * @return the new string
     */
    public static String add(String string, String add){
        StringBuilder returnString = new StringBuilder();
        returnString.append(string);
        returnString.append(";" + add);
        return returnString.toString();
    }
}