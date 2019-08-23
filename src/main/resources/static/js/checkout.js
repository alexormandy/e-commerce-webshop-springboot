$(document).ready(function(){
    updateTotals();
})

function removeProduct(productIdentifier) {

    $.ajax({
    type : "POST",
    url :  "/checkout/remove",
    data :{"productIdentifier" : productIdentifier},
    success : function(itemsInBag) {

        document.getElementById("number").innerHTML = itemsInBag;
        updateTotals();

}
});
}

function updateTotals() {

    $.ajax({
    type : "POST",
    url :  "/checkout/updateTotals",
    success : function(table) {

        $('#basketTable').remove();
        $(table).insertAfter("#insert");

}
});
}