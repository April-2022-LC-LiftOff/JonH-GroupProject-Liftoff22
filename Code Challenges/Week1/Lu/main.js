const triArea = (base, height) => {

        if(isNaN(base) || isNaN(height)) {
                return 'Please enter numerical values for base and height';
        }
        else if(base==0 || height==0) {
                return 'Please enter positive non-zero numbers'
        }
        else {
                let area = (base * height) / 2;
                return area;
        }
}


//Tests
let test1 = triArea(1,1);
console.log(test1);

let test2 = triArea('K',1);
console.log(test2);

let test3 = triArea(0,1);
console.log(test3);