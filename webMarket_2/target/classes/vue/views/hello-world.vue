<template id="hello-world">
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
            <div id="app" v-if="localStorage.getItem('client') === null">
                  <div class="login-page">
                     <transition name="fade">
                        <div v-if="!registerActive" class="wallpaper-login"></div>
                     </transition>
                     <div class="wallpaper-register"></div>
                     <div class="container">
                        <div class="row">
                              <div class="col-lg-4 col-md-6 col-sm-8 mx-auto">
                                 <div v-if="!registerActive" class="card login" v-bind:class="{ error: emptyFields }">
                                    <h1>Sign In</h1>
                                    <form  method="post" class="form-group" @submit.prevent="doLogin">
                                          <input v-model="emailLogin" type="text" class="form-control" placeholder="Email" name="login" required>
                                          <input v-model="passwordLogin" type="password" class="form-control" placeholder="Password" name="password" required>
                                             <input type="submit" class="btn btn-primary">
                                    </form>
                                 </div>
                              </div>
                        </div>
                     </div>
                  </div>
            </div>
         </b-container>  
    </app-frame>
</template>
<script>
    Vue.component("hello-world", {
    template: "#hello-world",
     data: () => ({
      registerActive: false,
      emailLogin: "",
      passwordLogin: "",
      emailReg: "",
      passwordReg: "",
      confirmReg: "",
      emptyFields: false
    }),
   methods: {
      doLogin() {
         if (this.emailLogin === "" || this.passwordLogin === "") {
            this.emptyFields = true;
         } else {
            const post = {
              name: this.emailLogin ,
              password: this.passwordLogin,
            };
            const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: "login=" + this.emailLogin + "&password=" + this.passwordLogin
            };

            return fetch("/logIn", requestOptions)
            .then(response => response.json())
            .then(client => {
                  if(client.name !== undefined){
                     console.log("Successful login");
                     console.log(client);
                     localStorage.setItem('client', JSON.stringify(client));
                     location.reload();
                  }else{
                     console.log("Incorrect login");
                     console.log(client);
                  }
            });
         }
      },
      logOut() {
            console.log("logout");
            localStorage.removeItem("client");
            location.reload();
          }   
   }

});
</script>
<style>
p {
   line-height: 1rem;
}

.card {
   padding: 20px;
}

.form-group {
   input {
      margin-bottom: 20px;
   }
}

.login-page {
   align-items: center;
   display: flex;
   height: 100vh;

   .wallpaper-login {
      background: url(https://images.pexels.com/photos/32237/pexels-photo.jpg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260)
         no-repeat center center;
      background-size: cover;
      height: 100%;
      position: absolute;
      width: 100%;
   }

   .fade-enter-active,
   .fade-leave-active {
  transition: opacity .5s;
}
   .fade-enter,
   .fade-leave-to {
      opacity: 0;
   }

   .wallpaper-register {
      background: url(https://images.pexels.com/photos/533671/pexels-photo-533671.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260)
         no-repeat center center;
      background-size: cover;
      height: 100%;
      position: absolute;
      width: 100%;
      z-index: -1;
   }

   h1 {
      margin-bottom: 1.5rem;
   }
}

.error {
   animation-name: errorShake;
   animation-duration: 0.3s;
}

@keyframes errorShake {
   0% {
      transform: translateX(-25px);
   }
   25% {
      transform: translateX(25px);
   }
   50% {
      transform: translateX(-25px);
   }
   75% {
      transform: translateX(25px);
   }
   100% {
      transform: translateX(0);
   }
}
</style>