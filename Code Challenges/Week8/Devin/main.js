//Create a function that takes two strings as arguments and returns the number of times the
//first string (the single character) is found in the second string.

// myChar = "a";
// str = "the rock fell and hit my head";
// count = 2;
function charCount(myChar, str) {
	let count = 0;
	for(let i = 0;i < str.length;i++) {
		if(myChar == str[i]) {
			count++;
		}
	}
	return count;
}