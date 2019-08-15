function removeProduct(productIdentifier) {

//let productIdentifier = document.getElementById("productId").textContent;
//console.log(productIdentifier);

console.log(productIdentifier);

 $.ajax({
              type : "POST",
              url :  "/checkout/remove",
              data :{"productIdentifier" : productIdentifier},
              success : function(result) {

              $( ".hello" ).remove();

              }
            });

 }