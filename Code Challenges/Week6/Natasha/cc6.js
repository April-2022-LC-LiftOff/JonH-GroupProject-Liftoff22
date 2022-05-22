function detectWord(str) {
  let arrayFromString = convertToString.split("");
  return arrayFromString.filter(checkCharacterCase).join("");
}

function checkCharacterCase(char) {
  if (char.toLowerCase() === char) {
    return char;
  }
}

console.log(detectWord("UcUNFYGaFYFYGtNUH")); //➞ "cat"

console.log(detectWord("bEEFGBuFBRrHgUHlNFYaYr")); // ➞ "burglar"

console.log(detectWord("YFemHUFBbezFBYzFBYLleGBYEFGBMENTment")); // ➞ "embezzlement"
