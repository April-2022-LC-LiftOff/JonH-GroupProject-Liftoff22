function charCounter(myChar, str) { //Loop
    if(str == null || myChar == null) {
        return 0;
    }
	count = 0;
	str.split("").forEach(char => {
		if (myChar === char){
			count++;
		}
	})
	return count;
}

function charCount(myChar, str) { //Regex
    if(str == null || myChar == null) {
        return 0;
    }
	const ex = new RegExp(myChar, 'g')
	let tmpStr = str.match(ex)
	if(tmpStr != null) {
		return tmpStr.length;
	}
	return 0
}