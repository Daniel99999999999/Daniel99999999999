package org.webmarket.rest.service;

import io.javalin.http.Context;
import org.webmarket.db.HibernateProvider;
import org.webmarket.db.objects.Client;
import org.webmarket.db.service.ClientDBService;

import java.util.Optional;

public class LoginController {

    public static Context logIn(Context ctx) {
        String login = ctx.formParam("login");
        String password = ctx.formParam("password");
        ClientDBService clientDBService = new ClientDBService(HibernateProvider.getSessionFactory());
        Optional<Client> userByCredential = clientDBService.getUserByCredential(login, password);
//        BasicAuthCredentials basicAuthCredentials = ctx.basicAuthCredentials();
//        basicAuthCredentials.getUsername();
        ctx.basicAuthCredentialsExist();
        return userByCredential.map(ctx::json).orElseGet(() -> ctx.json("{}"));
    }

}
