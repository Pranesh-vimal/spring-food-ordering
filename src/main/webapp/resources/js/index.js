$("document").ready(function () {
    // console.log("Hello World");
    getCart();
    $(".checkout-btn").click(async function () {
        $(".cart-container").addClass("d-none");
        $(".order-details").empty();
        $(".checkout-container").removeClass("d-none");
        const res = await axios.get("/cart");
        const data = res.data;
        if (data != "" && data.cartItems.length > 0) {
            $(".order-details").append(`
                <h5>Order Details</h5>
                <table class="table table-bordered">
                    <tr>
                        <th>Total</th>
                        <td>Rs. ${data.totalPrice}</td>
                    </tr>
                </table>
            `);
        }
    });
    $(".back-btn").click(function () {
        $(".cart-container").removeClass("d-none");
        $(".checkout-container").addClass("d-none");
    });
    $(".order-btn").click(function () {
        let name = $("#name").val();
        let email = $("#email").val();
        let phone = $("#phone").val();
        if (name == "" || email == "" || phone == "") {
            $(".error-text").removeClass("d-none");
            $(".error-text").text("Please fill all the fields");
        } else {
            if (phone.length < 10 || phone.length > 13) {
                $(".error-text").removeClass("d-none");
                $(".error-text").text("Please enter valid Phone Number");
            }else if( name.length<=3 ){
                $(".error-text").removeClass("d-none");
                $(".error-text").text("Please enter valid Name above 3 Characters");
            } 
            else {
                checkout();
            }
        }
    });
});

async function addtoCart(id) {
    $(".cart-item").empty();
    $(".cart-body").addClass("d-none");
    const res = await axios.get(`/cart/add/${id}`);
    getCart();
    $(".cart-container").removeClass("d-none");
    $(".checkout-container").addClass("d-none");
}

async function getCart() {
    $(".cart-item").empty();
    $(".cart-body").addClass("d-none");
    const res = await axios.get("/cart");
    const data = res.data;
    // console.log(data);
    if (data != "" && data.cartItems.length > 0) {
        $(".no-items").addClass("d-none");
        $(".cart-body").removeClass("d-none");
        data.cartItems.forEach(function (item) {
            $(".cart-item").append(`
                <div class="item mb-3">                                        
                    <div class="card d-flex flex-row" style="height: 10rem;">
                        <div class="card-body d-flex">
                            <img src="${item.product.imageUrl}" style="width:75px; height: 100%" class=" card-img-top" alt="${item.product.name}">
                            <div class="content px-2">
                                <h6 class="card-title">${item.product.name}</h6>
                                <p class="mb-2 card-text">Rs. ${item.product.price} </p>
                                <div class="d-inline">
                                    <button onclick="removeFromCart(${item.product.id})" class="btn w-20 fw-bold text-center btn-dark">-</button>
                                    <p  class="w-20 d-inline text-center fs-6 py-2 rounded px-3 border cart-quantity" >${item.quantity}</p>
                                    <button onclick="addtoCart(${item.product.id})" class="btn w-20 btn-dark">+</button>
                                    <button onclick="deleteItem(${item.product.id})" class="btn float-end btn-danger w-20 pe-4 text-center"><i class="far fa-trash-alt"></i></button>
                                </div>
                                <p class="mb-2 card-text fw-bold">Rs. ${item.subTotal}</p>
                            </div>
                        </div>
                    </div>
                </div>
            `);
        });
        $(".cart-item").append(`
            <div class="d-flex justify-content-between total-amount">
                <h5 class="fw-bold">Total Amount:</h5>
                <h5 class="fw-bold">Rs. ${data.totalPrice}</h5>
            </div>
        `);
    } else {
        $(".no-items").removeClass("d-none");
        $(".cart-body").addClass("d-none");
    }
}

async function removeFromCart(id) {
    const res = await axios.get(`/cart/remove/${id}`);
    getCart();
}

async function deleteItem(id) {
    const res = await axios.get(`/cart/delete/${id}`);
    getCart();
}

async function clearCart() {
    const res = await axios.get("/cart/clear");
    getCart();
}

async function checkout() {
    $(".loader").removeClass("d-none");
    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");
    await axios({
        method: "post",
        url: "/cart/checkout",
        headers: {
            [header]: token,
        },
        data: {
            name: $("#name").val(),
            email: $("#email").val(),
            phone: $("#phone").val(),
        },
    })
        .then(function (res) {
            if (res.data != null) {
                window.location = res.data;
            } else {
                alert("Order Failed");
            }
        })
        .catch(function (err) {
            console.log(err);
        });
}
