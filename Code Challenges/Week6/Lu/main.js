function detectWord(str) {
    str = str.toString();
	word = "";
	for (let letter of str) {
		if (checkCase(letter)) {
            word += letter;
        }
	}
	return word
}

function checkCase(letter) {
    let check = /[a-z]/g;
    if (letter.match(check)) {
      return letter;
    }
  }

console.log(detectWord("UcUNFYGaFYFYGtNUH"));
console.log(detectWord("YFemHUFBbezFBYzFBYLleGBYEFGBMENTment"));
console.log(detectWord("cLXSNVVJVOJBIQRVKIZWKJOIVHXELVReLXSNVVJVOJBIQRVKIZWKJOIVHXELVRrLXSNVVJVOJBIQRVKIZWKJOIVHXELVRtLXSNVVJVOJBIQRVKIZWKJOIVHXELVRaLXSNVVJVOJBIQRVKIZWKJOIVHXELVRiLXSNVVJVOJBIQRVKIZWKJOIVHXELVRn"));
console.log(detectWord("1231234436753!c!!&^$#&#@$**"));