$(document).ready(function(){
    updateTotals();
})

function removeProduct(productIdentifier, button) {

    let quantity = $(button).parent().find('#quantityForm').val();
    console.log(quantity);

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