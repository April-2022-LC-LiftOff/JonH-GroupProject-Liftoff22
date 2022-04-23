
public class FirstReverse {
    public static void main(String[] args) {
        // keep this function call here
        String input1 = "coderbyte";
        String input2 = "I Love Code";

        System.out.println(reverseString(input1));
        System.out.println(reverseString(input2));
    }

   public static String reverseString(String str) {
        StringBuilder newString = new StringBuilder();
       char character;
       for (int i = 0; i < str.length(); i++) {
           character = str.charAt(i);
           newString.insert(0, character);
       }
       
       return newString.toString();

   }


}
