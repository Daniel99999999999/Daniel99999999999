<template id="product-overview">
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

            <ul class="product-overview-list">
                <li v-for="product in products">
                    <b-row>
                        <b-col sm> <a :href="`/products/${product.id}`">{{product.name}} </a></b-col>
                        <b-col sm><b-button variant="outline-primary" @click="addProduct(product.id)">Add to Shopping Cart</b-button></b-col>
                        <b-col sm>{{product.description}} {{product.price}}</b-col>
                    </b-row>
                </li>
            </ul>
        </b-container>
    </app-frame>
</template>
<script>
    Vue.component("product-overview", {
        template: "#product-overview",
        data: () => ({
            products: [],
            shoppingCard: null,
        }),
        created() {
            fetch("/api/products")
                .then(res => res.json())
                .then(json => this.products = json)
                .catch(() => alert("Error while fetching products"));
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
<style>
    ul.product-overview-list {
        padding: 0;
        list-style: none;
    }
    ul.product-overview-list a {
        display: block;
        padding: 16px;
        border-bottom: 1px solid #ddd;
    }
    ul.product-overview-list a:hover {
        background: #00000010;
    }
</style>