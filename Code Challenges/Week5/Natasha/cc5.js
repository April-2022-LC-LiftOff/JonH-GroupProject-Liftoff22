function addUp(num) {
  if (num < 1 || num > 1001 || typeof num !== "number") {
    return "Enter any positive number between 1 and 1000.";
  } else {
    let addUpSum = 0;
    for (let i = 1; i <= num; i++) {
      addUpSum += i;
    }
    return addUpSum;
  }
}

console.log(addUp(-4));
console.log(addUp("4"));
console.log(addUp(4000));
console.log(addUp(1000));
console.log(addUp(0));
console.log(addUp(1));
console.log(addUp(4));
console.log(addUp(13));
console.log(addUp(600));
