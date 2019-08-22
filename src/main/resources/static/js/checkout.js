$(document).ready(function(){
    updateTotals();
})

function removeProduct(productIdentifier) {
let items = document.getElementById("items");

    $.ajax({
    type : "POST",
    url :  "/checkout/remove",
    data :{"productIdentifier" : productIdentifier},
    success : function(result) {

        items.textContent = result;
        $('#productRow').remove();
        updateTotals();

}
});
}

function updateTotals() {

    $.ajax({
    type : "POST",
    url :  "/checkout/updateTotals",
    success : function(result) {

        $('#basketTable').remove();
        $(result).insertAfter("#insert");

}
});
}