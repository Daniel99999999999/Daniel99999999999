package org.webmarket.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webmarket.db.objects.Client;
import org.webmarket.db.objects.Product;
import org.webmarket.db.objects.ShoppingCart;

public class HibernateProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateProvider.class);
    private static SessionFactory sessionFactory;

    private HibernateProvider() {
    }

    public static void init() {
        try {
            sessionFactory = new Configuration()
                    .configure()
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(Client.class)
                    .addAnnotatedClass(ShoppingCart.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            init();
        }
        return sessionFactory;
    }

}
