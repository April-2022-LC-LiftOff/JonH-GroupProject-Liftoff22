class Person {
  constructor(name, age) {
    this.name = name;
    this.age = age;
  }

  compareAge(other) {
    let evaluateAges = Math.sign(this.age - other.age);

    return evaluateAges === -1
      ? `${other.name} is older then me.`
      : evaluateAges === 1
      ? `${other.name} is younger then me.`
      : `${other.name} is the same age as me.`;
  }
}

p1 = new Person("Samuel", "24");
p2 = new Person("Joel", 36);
p3 = new Person("Lily", "24");

console.log(p1.compareAge(p2)); // "Joel is older than me. -1"

console.log(p2.compareAge(p1)); // "Samuel is younger than me. 1"

console.log(p1.compareAge(p3)); // "Lily is the same age as me. 0"
