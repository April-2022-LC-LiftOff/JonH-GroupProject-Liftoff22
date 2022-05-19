using System;

class Program {
  public static bool showWarnings = false;
  public static bool usePreferrredMethod = false;
  
  public static void Main (string[] args) {
    Program program = new Program();
    
    //positive ints
    program.Test(0); // 0
    program.Test(1); //1
    program.Test(2); //3
    program.Test(3); //6
    program.Test(5); //15
    program.Test(8); //36
    program.Test(13); //91
    Console.WriteLine("----");
    
    //Custom StartValues
    program.Test(13, 1); // 91
    program.Test(13, 4); // 85
    program.Test(13, 12); // 25
    Console.WriteLine("----");
    
    //Negative StartValues
    
    program.Test(13, -1); //90
    program.Test(13, -8); //55
    program.Test(-2,-5); //-14
    Console.WriteLine("----");


    //N less than startvalue
    program.Test(1, 13); // should be (13,1) - correct the error and should be 91
    program.Test(-7, 1); //-27
    program.Test(-9); //-45
    Console.WriteLine("----");
    
    //Using max Int Values.  Looping code would have to run the code > 4 Billion times, instead we do a simple one line calculation
    program.Test(Int32.MaxValue, -Int32.MaxValue);
  }

  
  public void Test (int n, int startValue = 0)
  {
    Console.WriteLine(SigmaSum(n, startValue));
  }


  
  public float SigmaSum(int n, int startValue = 0)
  {
    //If N is less than the number to count to, the formula will not work.
    if (startValue > n)
    {
      if (showWarnings)
      {
        Console.WriteLine("Warning: Start Value is greater than N! Swapping values to correct error.");
      }
      
      int temp = n;
      n = startValue;
      startValue = temp;
    }

    //The preferred method will be faster (as it only needs to run once)
    if (!usePreferrredMethod)
    {
      //modifier = will get all the sum of all numbers to exclude from the count.
      //for startValue > 0, subtract 1 from the startValue to get an exclude the startValue itself, since that value will be added into the total.  We only want to exclude the sum of the values less than the start value.
    
      float modifier = ReturnSum(MathF.Abs(startValue-(startValue > 0 ? 1: 0)));
      return (ReturnSum(n) - modifier);
      
    }

    return ReturnSumPreferred(n, startValue);

    
  }


  
  //Uses a float to minimize data type conversions and prevent rounding errors.
  public float ReturnSum(float n)
  {
    //returns the sum of all ints between 0 and a number N
    return ((n + 1) / 2) * n;
  }

public float ReturnSumPreferred(float n, float startVal)
  {
    //returns the sum of all ints between 0 and a number N
  
    return ((n + startVal) / 2) * (1+n-startVal);
  }
  
}