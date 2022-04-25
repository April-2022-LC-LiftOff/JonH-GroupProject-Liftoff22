function FirstReverse(str) { 

    // code goes here
    let strArray = str.split("");
    str = strArray.reverse().join("");
    return str; 
  
  }
     
  // keep this function call here 
  console.log(FirstReverse("coderbyte"));