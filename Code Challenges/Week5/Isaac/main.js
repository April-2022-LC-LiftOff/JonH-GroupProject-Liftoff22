function addUp(num) {
	if (num >= 0) {
		let finalNum = 0;
		for (let i = 1; i < num; i++ ) {
			finalNum = finalNum + i;
		}
		return finalNum + num;
	}
    console.error("Error: not a valid number or string.")
    return 0;
}