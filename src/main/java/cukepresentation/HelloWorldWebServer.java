package cukepresentation;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sun.net.httpserver.HttpServer;

/**
 * @author: Stephen Abrams
 */
public class HelloWorldWebServer {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new SimpleGuiceModule());
        HttpServer httpServer = injector.getInstance(HttpServer.class);
        httpServer.start();
    }
}
