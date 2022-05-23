using System;
using System.Linq;

class Program 
{

  public static void Main (string[] args) 
  {
    Program program = new Program();
    
    
    program.Test("UcUNFYGaFYFYGtNUH");
    program.Test("bEEFGBuFBRrHgUHlNFYaYr");
    program.Test("YFemHUFBbezFBYzFBYLleGBYEFGBMENTment");
  }

  public void Test(string input)
  {
    Console.WriteLine(PullLower(input));
    Console.WriteLine(PullLowerLoop(input));
  }

  public string PullLower(string input)
  {
    return new string(input.Where( ch => ch == Char.ToLower(ch) ).ToArray());
  }

  public string PullLowerLoop(string input)
  {
    string retVal = "";
    
    //perform the ToLower() method once on the whole string as oppsed to every loop.
    string lowerInput = input.ToLower();
    
    for (int i = 0; i < input.Length; i++)
    {
      if (input[i] == lowerInput[i])
      {
        retVal += input[i];
      }
    }

    return retVal;
  }
  
}