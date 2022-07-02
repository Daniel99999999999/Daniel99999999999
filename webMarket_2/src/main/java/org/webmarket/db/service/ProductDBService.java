package org.webmarket.db.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.webmarket.db.objects.Product;

import java.util.List;
import java.util.Optional;

public class ProductDBService {

    private final SessionFactory sessionFactory;

    public ProductDBService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Product> getAllProducts() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("From Product", Product.class).list();
        }
    }

    public List<Product> getAllProducts(Session session) {
        return session.createQuery("From Product", Product.class).list();

    }

    public Optional<Product> getById(String id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery("From Product where id =: idParam", Product.class);
            query.setParameter("idParam", id);
            return query.uniqueResultOptional();
        }
    }

    public Optional<Product> getById(String id, Session session) {
        Query<Product> query = session.createQuery("From Product where id =: idParam", Product.class);
        query.setParameter("idParam", id);
        return query.uniqueResultOptional();
    }

}
