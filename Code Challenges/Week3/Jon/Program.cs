using System;

class Program {
  public static void Main (string[] args) {

    //The following will allow us to run our program multiple consecutive times.
    Program program = new Program();
    const int timesToRun = 1;
    for (int i = 0; i < timesToRun; i++)
    {
      program.Run();
    }
    
  }

  public void Run()
  {
    Console.WriteLine("PROGRAM BEGIN");
    
    string str = Console.ReadLine();
    
    Console.WriteLine("Normal Reverse String: " + str + " => " + ReverseStringCustom(str));
    
    Console.WriteLine("Preserve Word Order Reverse String: " + str + " => " + ReverseStringCustom(str, true));
    
    string customDelim = "ey";
    str = "Jon Bradley Honeycutt";
    Console.WriteLine ("Preserve with Custom Delimeter Reverse (" + customDelim + "): " + str + " => " + ReverseStringCustom(str, true, customDelim));
    
    Console.WriteLine ("Preserve with Custom Delimeter Reverse and Delimeter Reverse (" + customDelim + "): " + str + " => " + ReverseStringCustom(str, true, customDelim, true));
    
    Console.WriteLine("PROGRAM END");
  }

  // Allow optional parameters to preserve the order that the words appear in and to provide a custom word delimeter to be used and a boolean to tell whether we want the delimiter itself reversed.
  public string ReverseStringCustom(string str, bool preserveWordOrder = false, string delimeter = " ", bool reverseDelimeter = false)
  {
    //declare a new string to hold the return value.
    string reversedString = "";

    if (preserveWordOrder)
    {
      //split the string into substrings based on the delimeter.  The delimeter can be a single character or a multiple character string.
      string[] substrings = str.Split(delimeter);

      //Outer loop to go through each substring one word at a time
      for (int i = 0; i < substrings.Length; i++)
      {
        //Inner loop to reverse each word.  Note that this uses a decrementing loop counter rather than an incrementing one.
        for(int j = substrings[i].Length - 1; j >= 0; j--)
        {
          reversedString += substrings[i][j];
        }

        //Checks to see if the entire input string has been reversed, if not it will add the delimeter in, only reversing it if the reverseDelimeter parameter has been set to true
        if (reversedString.Length < str.Length)
        {

          //This uses a "Ternary Operator".  It is sort of like a single line "if" statement.  If the value is true, it uses the value before the colon. Otherwise it uses the value after the colon.
          //A function can call itself...just be very careful when doing this not to create an infinite loop.  In this case, the default value for the reverseDelimeter parameter is false, so as long as we dont chage it here the function should terminate.
          reversedString += (reverseDelimeter ? ReverseStringCustom(delimeter): delimeter);
        }
      }
    }
    else
    {
      //If we are simply reversing an entire string, C# already has a library for that in the static Array class.
      char[] charArray = str.ToCharArray(); // Convert our string into an array of char
      Array.Reverse(charArray); //Reverse our array.
      reversedString = new string(charArray); //Convert our array back into a string.  This can be done in numerous ways ( https://www.geeksforgeeks.org/different-ways-to-convert-char-array-to-string-in-c-sharp/ ) and every language handles the specifics of type conversion a little differently. Javascript uses .join()
    }
    
    return reversedString;
    
  }
  
}