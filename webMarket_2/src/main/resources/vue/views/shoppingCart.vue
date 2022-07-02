<template id="shopping-cart" >
    <app-frame>   
         <b-container class="bv-example-row">
            <b-row>
                <b-nav>
                    <b-nav-item to="/">Menu</b-nav-item>
                    <b-nav-item to="/products/">Products</b-nav-item>
                    <b-nav-item to="/shoppingCart">Shopping Card</b-nav-item>
                    <b-nav-item v-if="localStorage.getItem('client') !== null" @click="logOut">log Out</b-nav-item>
                </b-nav>
            </b-row> 
            <b-row>   
                <dl v-if="shoppingCart">
                    <dt>State</dt>
                    <dd>{{shoppingCart.state}}</dd> 
                    <p>My Products</p> 
                    <b-row>
                         <b-col>Name</b-col>
                         <b-col>Description</b-col>
                         <b-col>Price</b-col>
                         <b-col></b-col>
                    </b-row>
                     <div v-for="product in shoppingCart.products">
                        <b-row>
                             <b-col><div class="prodVal">{{product.name}}</div></b-col>
                             <b-col><div class="prodVal">{{product.description}}</div></b-col>
                             <b-col><div class="prodVal">{{product.price}}</div></b-col>
                             <b-col><div class="prodVal"><b-button variant="outline-primary" @click="removeProduct(product.id)">Remove from Shopping Cart</b-button></div></b-col>
                        </b-row>
                     </div>                      
                </dl>
            </b-row> 
        </b-container>  
    </app-frame>
</template>
<script>
    Vue.component("shopping-cart", {
        template: "#shopping-cart",
        data: () => ({
            shoppingCart: null,
        }),
        created() {
            const user =  JSON.parse(localStorage.getItem('client'));
            if(user !== null){
                fetch(`/api/shoppingCart/${user.id}`)
                    .then(res => res.json())
                    .then(json => this.shoppingCart = json)
                    .catch(() => console.log("Cannot find ShoppingCart"));
            }else{
                console.log("Non client is login")
            }
        },
        methods: {
            logOut() {
                console.log("logout");
                localStorage.removeItem("client");
                location.reload();
            },
               removeProduct(productId){
                console.log(productId);
                const user =  JSON.parse(localStorage.getItem('client'));
                fetch(`/api/shoppingCart/remove/${user.id}/${productId}`)
                .then(res =>  window.location.reload())
                .catch(() => alert("Error while fetching product"));
               
            }
       }
    });
</script>
<style>
   .prodVal{   
       margin: 8;
       border-bottom-width: 20px;
   }
 
</style>