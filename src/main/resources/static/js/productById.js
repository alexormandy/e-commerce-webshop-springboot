$(function(){

    $(".dropdown-item").click(function(){
      $(".btn:first-child").text($(this).text());
      $(".btn:first-child").val($(this).text());
   });
});

function submit(button) {

    let productId = document.getElementById("productId").textContent;
    let productTitle = document.getElementById("productTitle").textContent;
    let productSize = document.getElementById("dropdownMenuButton").textContent;
    let productPrice = document.getElementById("productPrice").textContent;

    if (productSize.includes("Product Options")) {

        document.getElementById("message").textContent = "Select An Available Size";
        document.getElementById("message").style.color = "red";
    } else {

    $.ajax({
    type : "POST",
    url :  "/checkout/add",
    data :{"productId" : productId, "productTitle" : productTitle, "productSize" : productSize, "productPrice" : productPrice},
    success : function(itemsInBag) {

        document.getElementById("number").innerHTML = itemsInBag;

        document.getElementById("message").textContent = "";
        document.getElementById("message").textContent = "Added to basket!";
        document.getElementById("message").style.color = "green";
        setTimeout(function(){ document.getElementById("message").textContent = ""; }, 1000);
    }
    });
}

}

