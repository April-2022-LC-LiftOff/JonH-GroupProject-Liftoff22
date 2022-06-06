const assert = require('assert');

class Person {
	constructor(name, age) {
		this.name = name;
		this.age = age;
	}

	compareAge(other) {
		// Write code here!
        let message = "";
        if (other.age > 150 || this.age > 150 || other.age < 0 || this.age < 0 ) {
            message = "Are you sure you entered the ages correctly?"
        }
		else if (Number(other.age) == Number(this.age)) {
            message = `${other.name} is the same age as me.`;
        } 
        else if (Number(other.age) > Number(this.age)) {
            message = `${other.name} is older than me.`;
        }
        else {
            message = `${other.name} is younger than me.`
        }
        return message;
	}
}

p1 = new Person("Samuel", 24);
p2 = new Person("Joel", 36);
//p3 = new Person("Lily", 24);
p3 = new Person("Lily", "24");

assert.equal((p1.compareAge(p2)), "Joel is older than me.");
assert.equal(p1.compareAge(p3), "Lily is the same age as me.");

assert.equal(p2.compareAge(p1), "Samuel is younger than me.");
assert.equal(p2.compareAge(p3), "Lily is younger than me.");

assert.equal(p3.compareAge(p1), "Samuel is the same age as me.");
assert.equal(p3.compareAge(p2), "Joel is older than me.");
