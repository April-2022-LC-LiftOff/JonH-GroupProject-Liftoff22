using System;
using System.Collections.Generic;
using System.Text.RegularExpressions;
using System.Linq;

class Program 
{
  public static void Main (string[] args) 
  {
    Program program = new Program();
    program.Run();
  }

  public void Run()
  {
    //define our list:  The words here should be [asdf, qwerty, jkl, h77h98, g] and output the largest word.
    OutputLargestWord(ParseWords("asdf qwerty jkl;h77h98.!@#$%^g"));
    
    //The results of one function can be directly used as a parameter for another function.  This isn't always the cleanest looking code, but it is sometimes more efficient as you don't have to store the results in a variable.  This level of efficiency is almost always unnecessary on modern devices and it is usually better to make your code more readable by separating these functions


    //Note how this does the same thing, but first stores the resulting list from ParseWords into a new variable.  For a few fractions of a second, this will take up a tiny amount more memory but is significantly more readable.
    List<string> list = ParseWords("The quick brown fox jumps over the lazy dog.");
    OutputLargestWord(list);
  }

  public List<string> ParseWords(string str)
  {
    
    List<string> retVal = new List<string>();
    
    //Uses a regular expression to define the set of characters that will split the string.  In this case, all characters other than letters and numbers.  It is a near impossible task to remember all of the Regex commands unless you work with them often.  Don't be afraid to look them up regularly.  They can get real crazy...Just look at this one which checks for valid email addresses: ^[A-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\.[A-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[A-Z0-9-]+(?:\.[A-Z0-9-]+)*$
    foreach (string substr in Regex.Split(str, @"[^A-Za-z0-9]"))
    {
      retVal.Add(substr); //add to our return list.  Here we will add entries to the list with 0 characters when multiple splits happen.
    }

    //Filter our list to only return the list items which are not blank.
    return retVal.Where(x => x != "").ToList();

    //Note:  This is called a lambda. This uses C# Linq, which was created in 2007.  It is similar in function to Java's Lambda (2014) and Javascript's Arrow Functions introduced with ES6 (2015).  
  }

  public void OutputLargestWord(List<string> list)
  {
    if (list.Count > 0)
    {
      //Find our max length
      int maxLength = list.Max(listItem => listItem.Length);

      //Write the first item taht has a length equal to the max length.
      Console.WriteLine(list.FirstOrDefault(str => str.Length == maxLength));
    }
    else
    {
      //Output error if there are no list items.
      Console.WriteLine("Error: List is Blank!");
    }
  }
  
}