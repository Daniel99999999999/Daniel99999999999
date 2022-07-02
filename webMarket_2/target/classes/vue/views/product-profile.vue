<template id="product-profile">
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
                     <b-col ><img src="https://image.shutterstock.com/image-vector/no-image-available-icon-flat-260nw-1240855801.jpg" alt="Trulli" width="500" height="333"></b-col>
                      <b-col >
                    <dl v-if="product">
                        <dt>Name</dt>
                        <dd>{{product.name}}</dd>
                        <dt>Description</dt>
                        <dd>{{product.description}}</dd>
                        <dt>State</dt>
                        <dd>{{product.state}}</dd>
                        <dt>Number of items available</dt>
                        <dd>{{product.amount}}</dd>
                        <dt>Price</dt>
                        <dd>{{product.price}}</dd>
                        <dd><b-button variant="outline-primary" @click="addProduct(product.id)">Add to Shopping Cart</b-button></dd>
                    </dl>
                    </b-col>
            </b-row>
        </b-container>
    </app-frame>
</template>
<script>
    Vue.component("product-profile", {
        template: "#product-profile",   
        data: () => ({
            product: null,
            items: [
                 //{ title: 'Chat', icon: require('@/assets/images/no-image-available.jpg') },
            ],
        }),
        created() {
            const productId = this.$javalin.pathParams["product-id"];
            fetch(`/api/products/${productId}`)
                .then(res => res.json())
                .then(json => this.product = json)
                .catch(() => alert("Error while fetching product"));
        },
         methods: {
              logOut() {
                console.log("logout");
                localStorage.removeItem("client");
                location.reload();
            },
             addProduct(productId){
                console.log(productId);
                const user =  JSON.parse(localStorage.getItem('client'));
                if(user != null){
                    fetch(`/api/shoppingCart/${user.id}/${productId}`)
                    .then(res => res.json())
                    .then(json => this.oppingCar = json)
                    .catch(() => alert("Error while fetching product"));
                }
            }
          }
    });
</script>
