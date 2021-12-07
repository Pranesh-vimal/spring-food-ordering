$('document').ready(function(){
    // console.log("Hello World");
    getCart();
});

async function addtoCart(id){
    $('.cart-item').empty();
    $('.cart-body').addClass('d-none');
    const res=await axios.get(`/cart/add/${id}`);
    // console.log(res);
    const data=res.data;
    // console.log(data);
    $('.no-items').addClass('d-none');
    $('.cart-body').removeClass('d-none');
    data.cartItems.forEach(function(item){
        $('.cart-item').append(`
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
                            <p class="mb-2 card-text fw-bold">Rs. ${item.product.price}</p>
                        </div>
                    </div>
                </div>
            </div>
        `);
    });

}

async function getCart(){
    $('.cart-item').empty();
    $('.cart-body').addClass('d-none');
    const res=await axios.get('/cart');
    const data=res.data;
    // console.log(data=="");
    if(data!="" && data.cartItems.length>0){
        $('.no-items').addClass('d-none');
        $('.cart-body').removeClass('d-none');
        data.cartItems.forEach(function(item){
            $('.cart-item').append(`
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
                                <p class="mb-2 card-text fw-bold">Rs. ${item.product.price}</p>
                            </div>
                        </div>
                    </div>
                </div>
            `);
        });
    }
    else{
        $('.no-items').removeClass('d-none');
        $('.cart-body').addClass('d-none');
    }
}

async function removeFromCart(id){
    const res=await axios.get(`/cart/remove/${id}`);
    getCart();
}

async function deleteItem(id){
    const res=await axios.get(`/cart/delete/${id}`);
    getCart();
}

async function clearCart(){
    const res=await axios.get('/cart/clear');
    getCart();
}