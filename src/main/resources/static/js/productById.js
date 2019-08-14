$(function(){

    $(".dropdown-item").click(function(){
      $(".btn:first-child").text($(this).text());
      $(".btn:first-child").val($(this).text());

   });
});

function myFunction(button) {

let productId = document.getElementById("productId").textContent;
let productTitle = document.getElementById("productTitle").textContent;
let productSize = document.getElementById("dropdownMenuButton").textContent;
let productPrice = document.getElementById("productPrice").textContent;

let items = document.getElementById("items");

 $.ajax({
              type : "POST",
              url :  "/checkout/add",
              data :{"productId" : productId, "productTitle" : productTitle, "productSize" : productSize, "productPrice" : productPrice},
              success : function(result) {

               items.textContent = result;
              }
            });

 }

