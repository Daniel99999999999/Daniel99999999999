package org.webmarket.rest;

import io.javalin.Javalin;
import io.javalin.core.security.RouteRole;
import io.javalin.core.util.Header;
import io.javalin.http.Context;
import io.javalin.plugin.rendering.vue.JavalinVue;
import io.javalin.plugin.rendering.vue.VueComponent;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import org.webmarket.db.HibernateProvider;
import org.webmarket.rest.service.LoginController;
import org.webmarket.rest.service.ProductController;
import org.webmarket.rest.service.ShoppingCartController;

import java.util.Objects;


public class Main {

    public static void main(String[] args) {
        HibernateProvider.init();

        Javalin app = Javalin.create(c -> {
            c.enableWebjars();
            c.accessManager((handler, ctx, routeRoles) -> {
                if (routeRoles.contains(Role.ANYONE)) {
                    handler.handle(ctx);
                } else if (routeRoles.contains(Role.LOGGED_IN) && Objects.nonNull(currentUser(ctx))) {
                    handler.handle(ctx);
                } else {
                    ctx.status(401).header(Header.WWW_AUTHENTICATE, "Basic");
                }
            });
            JavalinVue.stateFunction = (ctx -> MapsKt.mapOf(new Pair<>("cc", currentUser(ctx))));
        }).start(7000);
        app.get("/", new VueComponent("hello-world"), Role.ANYONE);
        app.get("/products", new VueComponent("product-overview"), Role.ANYONE);
        app.get("/shoppingCart", new VueComponent("shopping-cart"), Role.ANYONE);
        app.get("/products/{product-id}", new VueComponent("product-profile"), Role.ANYONE);

        app.get("/api/products", ProductController::getAll, Role.ANYONE);
        app.get("/api/products/{product-id}", ProductController::getOne, Role.ANYONE);
        app.get("/api/shoppingCart/{client-id}", ShoppingCartController::getClientShoppingCart, Role.ANYONE);
        app.get("/api/shoppingCart/{client-id}/{product-id}", ShoppingCartController::addProductToShoppingCart, Role.ANYONE);
        app.get("/api/shoppingCart/remove/{client-id}/{product-id}", ShoppingCartController::removeProductToShoppingCart, Role.ANYONE);
        app.post("/logIn", LoginController::logIn, Role.ANYONE);
    }

    private static String currentUser(Context context) {
        if (context.basicAuthCredentialsExist()) {
            return context.basicAuthCredentials().getUsername();
        }
        return null;
    }

}

enum Role implements RouteRole {
    ANYONE, LOGGED_IN
}
