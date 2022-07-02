package org.webmarket.db.service;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.webmarket.db.objects.Client;
import org.webmarket.db.objects.ShoppingCart;

import java.util.Optional;

public class ShoppingCartDBService {

    private final SessionFactory sessionFactory;

    public ShoppingCartDBService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<ShoppingCart> getClientShoppingCart(String clientId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<ShoppingCart> query = session.createQuery("From ShoppingCart where client_id =: clientId", ShoppingCart.class);
            query.setParameter("clientId", clientId);
            Optional<ShoppingCart> shoppingCart = query.uniqueResultOptional();
            shoppingCart.ifPresent(e -> Hibernate.initialize(e.getProducts()));
            transaction.commit();
            return shoppingCart;
        }
    }

    public Optional<ShoppingCart> getClientShoppingCart(String clientId, Session session) {
        Query<ShoppingCart> query = session.createQuery("From ShoppingCart where client_id =: clientId", ShoppingCart.class);
        query.setParameter("clientId", clientId);
        return query.uniqueResultOptional();
    }

    public Optional<ShoppingCart> createShoppingCart(Client client) {
        try (Session session = sessionFactory.openSession()) {
            Transaction trans2 = session.beginTransaction();
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setState("CURRENT");
            shoppingCart.setClient(client);
            session.save(shoppingCart);
            session.flush();
            session.clear();
            trans2.commit();
            return Optional.of(shoppingCart);
        }
    }

    public Optional<ShoppingCart> createShoppingCart(Client client, Session session) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setState("CURRENT");
        shoppingCart.setClient(client);
        session.save(shoppingCart);
        return Optional.of(shoppingCart);
    }


    public void save(ShoppingCart shoppingCart) {
        try (Session session = sessionFactory.openSession()) {
            Transaction trans2 = session.beginTransaction();
            session.save(shoppingCart);
            session.flush();
            session.clear();
            trans2.commit();
        }
    }

    public void save(ShoppingCart shoppingCart, Session session) {
        session.save(shoppingCart);
    }

}
