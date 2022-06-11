class Person {
	constructor(name, age) {
		this.name = name;
		this.age = age;
	}

	compareAge(other) {
		// Write code here!
		if(typeof other === 'object' && other instanceof Person) {
				if(other.age > this.age) {
					return `${other.name} is older than me.`;
				} else if(other.age < this.age) {
					return `${other.name} is younger than me.`;
				} else {
					return `${other.name} is the same age as me.`;
				}
		} else {
			console.error("Bad input: other is not an object or person")
		}
	}
}