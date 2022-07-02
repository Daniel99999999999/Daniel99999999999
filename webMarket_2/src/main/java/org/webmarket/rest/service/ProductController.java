package org.webmarket.rest.service;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import org.webmarket.db.HibernateProvider;
import org.webmarket.db.objects.Product;
import org.webmarket.db.service.ProductDBService;

import java.util.Optional;

public class ProductController {

    public static Context getAll(Context ctx) {
        ProductDBService productDBService = new ProductDBService(HibernateProvider.getSessionFactory());
        return ctx.json(productDBService.getAllProducts());
    }

    public static Context getOne(Context ctx) {
        ProductDBService productDBService = new ProductDBService(HibernateProvider.getSessionFactory());
        Optional<Product> product = productDBService.getById(ctx.pathParam("product-id"));
        return product.map(ctx::json).orElseThrow(NotFoundResponse::new);
    }

}
