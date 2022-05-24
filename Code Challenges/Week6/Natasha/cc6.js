function detectWord(str) {
  let convertToString = str.toString();
  let arrayFromString = convertToString.split("");
  return arrayFromString.filter(checkCharacterCase).join("");
}

function checkCharacterCase(char) {
  let pattern = /[a-z]/g;
  if (char.match(pattern)) {
    return char;
  }
}

console.log(detectWord("UcUNFYGaFYFYGtNUH")); //➞ "cat"

console.log(detectWord("bEEFGBuFBRrHgUHlNFYaYr")); // ➞ "burglar"

console.log(detectWord("YFemHUFBbezFBYzFBYLleGBYEFGBMENTment")); // ➞ "embezzlement"

console.log(detectWord("")); // ➞ ""

console.log(detectWord(1111)); // ➞ ""

console.log(detectWord("!@()848925(()()()")); // ➞ ""

console.log(detectWord("h#$%ell654oworl__*&_d")); // ➞ "helloworld"
