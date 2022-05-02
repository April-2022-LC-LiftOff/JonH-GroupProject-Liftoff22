// Have the function LongestWord(sen) take the sen parameter being passed and return the longest word
//in the string. If there are two or more words that are the same length, return the first word
//from the string with that length.

function LongestWord(sen) {

  var lower = sen.toLowerCase();
  var splitStr = lower.split(" ").sort(function(a, b) {
    return b.replace(/[^A-Z0-9]/ig, "").length - a.replace(/[^A-Z0-9]/ig, "").length; });


  return splitStr[0];

}

// keep this function call here
console.log(LongestWord(readline()));