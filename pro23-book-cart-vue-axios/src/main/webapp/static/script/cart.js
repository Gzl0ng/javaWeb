window.onload=function (){
    var vue = new Vue({
        el:"#cart_div",
        data:{
            cart:{}
        },
        methods:{
            getCart:function (){
                axios({
                    method:"POST",
                    url:"cart.do",
                    params:{
                        operate:"cartInfo"
                    }
                })
                    .then(function (value){
                        var cart = value.data;
                        vue.cart = cart;
                    })
                    .catch(function (reason){});
            },
            editCart(cartItemId ,buyCount){
                axios({
                    method:"POST",
                    url:"cart.do",
                    params:{
                        operate:"editCart",
                        cartItemId:cartItemId,
                        buyCount:buyCount
                    }
                })
                    .then(function (value){
                        vue.getCart();
                    })
                    .catch(function (reason){});
            }
        },
        //这里用this，因为mounted是vue的生命周期，所以可以用this
        mounted:function (){
                this.getCart();
            }
    });
}