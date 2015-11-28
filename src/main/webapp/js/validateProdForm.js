
function validateProdForm(form) {
	var name = form.name.value;
	var nameBool = false;
	document.getElementById("nameError").innerHTML = " ";
	if (name == null || name.length < 1) {
		document.getElementById("nameError").innerHTML = "Enter product's name!";
	} else {
		nameBool = true;
	}
	var currentPrice = parseFloat(form.currentPrice.value);
	var currentPriceBool = false;
	document.getElementById("currentPriceError").innerHTML = "";
	if (isNaN(currentPrice) || currentPrice <= 0) {
		document.getElementById("currentPriceError").innerHTML = "Price must be greater than 0!";
	} else {
		currentPriceBool = true;
	}

	var weight = parseFloat(form.weight.value);
	var weightBool = false;
	document.getElementById("weightError").innerHTML = "";
	if (isNaN(weight) || weight <= 0) {
		document.getElementById("weightError").innerHTML = "Weight must be greater than 0!";
	} else {
		weightBool = true;
	}
	var bulk = parseFloat(form.bulk.value);
	var bulkBool = false;
	document.getElementById("bulkError").innerHTML = "";
	if (isNaN(bulk)|| bulk <= 0) {
		document.getElementById("bulkError").innerHTML = "Bulk must be greater than 0!";
	} else {
		bulkBool = true;
	}
	var category = form.category.name.value;
	var categoryBool = false;
	console.log(form.category.value);
	console.log(form.category.name.value);

	document.getElementById("categoryError").innerHTML = "";
	if (category == null) {
		document.getElementById("categoryError").innerHTML = "Choose category!";
	} else {
		categoryBool = true;
	}
	if (nameBool && currentPriceBool && bulkBool && weightBool && categoryBool)
		return true;
	else
		return false;
}