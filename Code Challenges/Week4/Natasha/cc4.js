function LongestWord(sen) {
  let returnWord = " ";
  let newSen = sen.replace(/[^a-zA-Z ]/g, "");
  let splitSen = newSen.split(" ");

  let max = 0;

  for (let i in splitSen) {
    if (splitSen[i].length > max) {
      returnWord = splitSen[i];
      max = splitSen[i].length;
    }
  }
  return returnWord;
}

// keep this function call here
console.log(LongestWord("I love dogs"));
console.log(LongestWord("fun&!! time"));
