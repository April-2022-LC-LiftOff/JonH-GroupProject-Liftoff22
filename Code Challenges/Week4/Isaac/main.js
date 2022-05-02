function LongestWord(sen) {

    let longestWord = "";
    sen.split(" ").forEach(word => {
        word = word.replace(/[\W_]+/g, '');
        if (word.length > longestWord.length) {
            longestWord = word;
        }
    });

    return longestWord;
}