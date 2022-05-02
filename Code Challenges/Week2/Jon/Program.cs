using System;
using System.Collections;
using System.Collections.Generic;

class Program {
  
  public static void Main (string[] args) 
  {
    Program program = new Program();
    program.Run();
  }

  public void Run()
  {
    Console.WriteLine("Begin Program");
    List<int> blank = new List<int>();
    OutputMinMax(blank);
    OutputMinMax(RandomIntList());
    OutputMinMax(RandomFloatList());
    OutputMinMax(RandomIntArray());
    OutputMinMax(RandomFloatArray());
    Console.WriteLine("End Program");
  }

  //methods to handle numbers passed as an array
  public void OutputMinMax(int[] list)
  {
    //if the list is an int array, cast it to a list of float and call the correct method.
    List<float> newlist = new List<float>();
    for (int i = 0; i < list.Length ; i++)
    {
      newlist.Add(list[i]);
    }
    OutputMinMax(newlist);
  }
  
  public void OutputMinMax(float[] list)
  {
    //if a float array is passed, convert it to a list.
    List<float> newlist = new List<float>();
    for (int i = 0; i < list.Length ; i++)
    {
      newlist.Add(list[i]);
    }
    OutputMinMax(newlist);
  }

  //methods to handle numbers passed as  list object
  public void OutputMinMax(List<int> list)
  {
    List<float> newlist = new List<float>();
    for (int i = 0; i < list.Count ; i++)
    {
      newlist.Add(list[i]);
    }
    OutputMinMax(newlist);
  }
  
  public void OutputMinMax(List<float> list)
  {
    if (list.Count == 0)
    {
      Console.WriteLine("Error: Array/List is empty!");
      return;
    }

    string listString = "";
    
    //set the initial min and max values to a value that should be overwritten
    float minValue = Int32.MaxValue;
    float maxValue = -Int32.MaxValue;
    foreach (float i in list)
    {
      //set the min and max values if they are lower/higher than the previous values.
      minValue = System.MathF.Min(i, minValue);
      maxValue = System.MathF.Max(i, maxValue);

      //For clear output, build a string that contains the entire list
      listString += i + ", ";
    }

    //trim trailing comma and space from listString
    listString = listString.TrimEnd(' ');
    listString = listString.TrimEnd(',');
    
    Console.WriteLine("From [" + listString + "] => [" + minValue + ", " + maxValue +"]");
  }


  // The following methods are used to create random lists and arrays of the types int and float to be used for testing.
  public List<int> RandomIntList(int count = 0)
  {
    Random rand = new Random();
    List<int> retVal = new List<int>();
    
    //if count is less not specified or is less than 1, set count to a random number between 1 and 9
    if (count <= 0)
    {
      count = rand.Next(10);
    }

    for (int i = 0; i < count; i++)
    {
      retVal.Add(rand.Next());
    }
    return retVal;
  }

  public List<float> RandomFloatList(int count = 0)
  {
    Random rand = new Random();
    List<float> retVal = new List<float>();
    
    //if count is less not specified or is less than 1, set count to a random number between 1 and 9
    if (count <= 0)
    {
      count = rand.Next(10);
    }

    for (int i = 0; i < count; i++)
    {
      retVal.Add((float)rand.NextDouble()); //cast the double type to a float type
    }
    return retVal;
  }

  public int[] RandomIntArray(int count = 0)
  {
    Random rand = new Random();
    
    //if count is less not specified or is less than 1, set count to a random number between 1 and 9
    if (count <= 0)
    {
      count = rand.Next(10);
    }

    //set the array to the correct size
    int[] retVal = new int[count];

    for (int i = 0; i < count; i++)
    {
      retVal[i] = rand.Next();
    }
    return retVal;
  }

  public float[] RandomFloatArray(int count = 0)
  {
    Random rand = new Random();
    
    //if count is less not specified or is less than 1, set count to a random number between 1 and 9
    if (count <= 0)
    {
      count = rand.Next(10);
    }

    //set the array to the correct size
    float[] retVal = new float[count];

    for (int i = 0; i < count; i++)
    {
      retVal[i] = (float)rand.NextDouble(); //cast the double type to a float type
    }
    return retVal;
  }
  
}