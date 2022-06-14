const charCount = (myChar, str) => {
    let counter = 0;
    if (myChar.length!==1) {
        return 'Please enter single character as first parameter';
    }
    if((myChar.match(/A-z/))=== null) {
        return 'Please only enter letters as the search character';
    }
    for (let char of str) {
        if(char === myChar) {
            counter++;
        }
    }
    return counter;
}

console.log(charCount('5', 'And another one'));