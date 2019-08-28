$(document).ready(function(){
    updateTotals();
})

function removeProduct(productIdentifier) {

    let quantity = $('#quantityForm').val();

    $.ajax({
    type : "POST",
    url :  "/checkout/remove",
    data :{"productIdentifier" : productIdentifier, "quantity" : quantity},
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