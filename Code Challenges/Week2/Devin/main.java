// Create a function that takes two strings and returns true if the first string ends with the second string;
// otherwise return false.

public class Challenge {
    public static boolean checkEnding(String str1, String str2) {
        if(str1.endsWith(str2)) {
            return true;
        } else {
            return false;
        }
    }
}