$(function(){

    $(".dropdown-item").click(function(){
      $(".btn:first-child").text($(this).text());
      $(".btn:first-child").val($(this).text());

   });
});

function myFunction(button) {

let id = document.getElementById("id").textContent;
let productSize = document.getElementById("dropdownMenuButton").textContent;

console.log(id);
console.log(productSize);

 $.ajax({
              type : "POST",
              url :  "/checkout/add",
              data :{"id" : id, "productSize" : productSize},
              success : function(result) {
              console.log(result);

              }
            });

 }

