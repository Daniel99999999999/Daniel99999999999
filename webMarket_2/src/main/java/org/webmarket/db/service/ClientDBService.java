package org.webmarket.db.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.webmarket.db.objects.Client;

import java.util.Optional;

public class ClientDBService {

    private final SessionFactory sessionFactory;

    public ClientDBService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Client> getUserByCredential(String name, String password) {
        try (Session session = sessionFactory.openSession()) {
            Query<Client> query = session.createQuery("From Client where name =: nameParam and password =: passParam", Client.class);
            query.setParameter("nameParam", name);
            query.setParameter("passParam", password);
            return query.uniqueResultOptional();
        }
    }

    public Optional<Client> getClientById(String clientId, Session session) {
        Query<Client> query = session.createQuery("From Client where id =: clientId", Client.class);
        query.setParameter("clientId", clientId);
        return query.uniqueResultOptional();
    }

    public Optional<Client> getClientById(String clientId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Client> query = session.createQuery("From Client where id =: clientId", Client.class);
            query.setParameter("clientId", clientId);
            return query.uniqueResultOptional();
        }
    }

}
