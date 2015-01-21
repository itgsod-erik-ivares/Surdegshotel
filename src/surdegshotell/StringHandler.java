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
}