function addUp(num) {
	let sum = 0;
    if (num < 0) {
        for (let i=num; i <=0; i++) {
            sum += Number(i);
        }
    }
    else if (num>=0) {
        for (let i=num; i >=0; i--) {
            sum += Number(i);
        }
    }
    return sum;
}
console.log(addUp(4));
console.log(addUp(-4));
console.log(addUp(0));