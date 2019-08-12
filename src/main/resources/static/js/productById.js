function myFunction(button) {

let id = document.getElementById("id").textContent;

console.log(id);

 $.ajax({
              type : "POST",
              url :  "/checkout/add",
              data :{"id" : id},
              success : function(result) {
              console.log(result);

              }
            });
 }