using System;

class Person {
  public static void Main (string[] args) 
  {
    Person p1 = new Person("Samuel", 24);
    Person p2 = new Person("Joel", 36);
    Person p3 = new Person("Lily", 24);

    p1.CompareAge(p1);
    p3.CompareAge(p1);
    p3.CompareAge(p2);
    p2.CompareAge(p3);
    
  }
  
  public string name = "";
  public int age = 0;


  //A "Constructor" is a template to define properties of an object.  In this case, the temporary _name and _age variables are used to set the name and age properties of the new person object on creation.  
  public Person(string _name, int _age) 
  {
    name = _name;
    age = _age;
  }

  public void CompareAge(Person p)
  {
    if (name == p.name)
    {
      Console.WriteLine(name + ": " + "I am " + name + ".");
    }
    else
    {
      if (age == p.age)
      {
        Console.WriteLine(name + ": " + p.name + " is the same age as me.");
      }
      else
      {
        //When a value is dependent on the results of the comparison, sometimes a ternary operator can make the code look better than an if statement.  This is largely a matter of prefference.
        Console.WriteLine(name + ": " + p.name + " is " + (age > p.age ? "younger" : "older") + " than me.");
      }
    }
  }
  
  
}