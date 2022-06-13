using System;
using System.Linq;

class Program {
  public static void Main (string[] args) {
    Program program = new Program();

    program.Run('a', "edabit");

    program.Run('c', "Chamber of secrets");

    program.Run('b', "big fat bubble");
    
  }

  public void Run(char c, string s)
  {
    Console.WriteLine("How many times does '" + c + "' appear in '" + s + "'?");
    Console.WriteLine("Loop: " + charCountLoop(c,s));
    Console.WriteLine("Split: " + charCountSplit(c,s));
    Console.WriteLine("LINQ: " + charCountLinq(c,s));
    Console.WriteLine("");
    
    
  }
  
  public int charCount(char c, string s)
  {

    //return charCountSplit(c,s);
    //return charCountLoop(c,s);
    return charCountLinq(c,s);
  }

  private int charCountSplit(char c, string s)
  {
    return s.Split(c).Length-1;
  }

  private int charCountLoop(char c, string s)
  {
    int count = 0;
    foreach (char ch in s)
    {
      if (ch == c)
      {
        count++;
      }
    }

    return count;
  }

  private int charCountLinq(char c, string s)
  {
    return s.Count(ch=> ch == c);
  }
  
}