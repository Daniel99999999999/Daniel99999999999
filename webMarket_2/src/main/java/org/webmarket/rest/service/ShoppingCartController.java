package org.webmarket.rest.service;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.webmarket.db.HibernateProvider;
import org.webmarket.db.objects.Client;
import org.webmarket.db.objects.Product;
import org.webmarket.db.objects.ShoppingCart;
import org.webmarket.db.service.ClientDBService;
import org.webmarket.db.service.ProductDBService;
import org.webmarket.db.service.ShoppingCartDBService;

import java.util.Optional;

public class ShoppingCartController {

    private static final String CLIENT_ID = "client-id";
    private static final String PRODUCT_ID = "product-id";

    public static Context getClientShoppingCart(Context ctx) {
        ShoppingCartDBService service = new ShoppingCartDBService(HibernateProvider.getSessionFactory());
        Optional<ShoppingCart> shoppingCart = service.getClientShoppingCart(ctx.pathParam(CLIENT_ID));
        return shoppingCart.map(ctx::json).orElseThrow(NotFoundResponse::new);
    }

    public static Context addProductToShoppingCart(Context ctx) {
        Session session = HibernateProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        ShoppingCartDBService cartDBService = new ShoppingCartDBService(HibernateProvider.getSessionFactory());
        ClientDBService clientDBService = new ClientDBService(HibernateProvider.getSessionFactory());
        ProductDBService productDBService = new ProductDBService(HibernateProvider.getSessionFactory());
        Optional<ShoppingCart> shoppingCartOptional = cartDBService.getClientShoppingCart(ctx.pathParam(CLIENT_ID), session);
        ShoppingCart shoppingCart = shoppingCartOptional.orElseGet(() -> {
            Client clientById = clientDBService.getClientById(ctx.pathParam(CLIENT_ID), session).orElseThrow(NotFoundResponse::new);
            return cartDBService.createShoppingCart(clientById, session).orElseThrow();
        });
        Product product = productDBService.getById(ctx.pathParam(PRODUCT_ID), session).orElseThrow(NotFoundResponse::new);
        shoppingCart.getProducts().add(product);
        cartDBService.save(shoppingCart, session);
        session.flush();
        transaction.commit();
        session.close();
        return ctx.json(shoppingCart);
    }

    public static Context removeProductToShoppingCart(Context ctx) {
        Session session = HibernateProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        ShoppingCartDBService cartDBService = new ShoppingCartDBService(HibernateProvider.getSessionFactory());
        ProductDBService productDBService = new ProductDBService(HibernateProvider.getSessionFactory());
        Optional<ShoppingCart> shoppingCartOptional = cartDBService.getClientShoppingCart(ctx.pathParam(CLIENT_ID), session);
        ShoppingCart shoppingCart = shoppingCartOptional.orElseThrow();
        Product product = productDBService.getById(ctx.pathParam(PRODUCT_ID), session).orElseThrow(NotFoundResponse::new);
        shoppingCart.getProducts().remove(product);
        cartDBService.save(shoppingCart, session);
        session.flush();
        transaction.commit();
        session.close();
        return ctx.json(shoppingCart);
    }

}
