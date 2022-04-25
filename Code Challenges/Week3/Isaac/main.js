function FirstReverse(str) {

    if (typeof str == "string" || typeof str == "number") {
        // code goes here
        let tempStr = "";
        String(str).split("").forEach(char => {
            tempStr = char + tempStr;
        });

        return tempStr;
    }

}