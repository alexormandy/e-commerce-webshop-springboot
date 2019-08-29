function productById(button) {

    let searchById = $(button).parent().find('#search').val();
    console.log(searchById);

    $.ajax({
    type : "POST",
    url :  "/search",
    data :{"searchById" : searchById},

})
};