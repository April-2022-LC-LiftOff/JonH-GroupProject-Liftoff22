//With Regex
function detectWord(str) {
    //This will remove all none letter character and return the hidden word
    return str.replace(/[^a-z]/g, "")
}

//With loop
function detectWord1(str) {
    let word = ""
    str.split("").forEach((char) => {
        //Checks whether char + an number still equals a number
        if (char == char.toLowerCase() && !( Number(char) + 4 > 4)) {
            word += char
        }
    })
    return word
}