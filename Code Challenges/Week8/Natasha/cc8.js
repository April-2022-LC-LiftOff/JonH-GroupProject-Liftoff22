function charCount(myChar, str) {
  return [...str].filter((character) => character === myChar).length;
}

console.log(charCount("a", "edabit")); //1

console.log(charCount("c", "Chamber of secrets")); // 1

console.log(charCount("b", "big fat bubble")); // 4
