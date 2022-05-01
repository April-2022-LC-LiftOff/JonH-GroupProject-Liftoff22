const LongestWord = (sen)=> {
    let wordArray = sen.split(" ");
    let longestCount = 0;
    let finalword;
    for (let word of wordArray) {
        let trimmedWord = '';
        for (let i=0; i<word.length; i++) {
            if ((word.charCodeAt(i) >= 97 && word.charCodeAt(i) <= 122) 
            || word.charCodeAt(i) >= 48 && word.charCodeAt(i) <= 57 
            || word.charCodeAt(i) >= 65 && word.charCodeAt(i) <= 90) {
                trimmedWord += word[i];
            }
            if (trimmedWord.length > longestCount) {
                longestCount = trimmedWord.length;
                finalword = trimmedWord;
            }
        }
    }
    sen = finalword;
    return sen; 
}

  // keep this function call here 
console.log(LongestWord("funny&!! ti!!me p!ne@pplE"));