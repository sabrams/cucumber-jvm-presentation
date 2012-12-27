package cukepresentation;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.sun.net.httpserver.HttpServer;
import cukepresentation.guice.Port;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author: Stephen Abrams
 */
public class SimpleGuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Integer.class).annotatedWith(Port.class).toInstance(8086);
    }

    @Provides
    HttpServer provideHttpServer(@Port Integer port) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/hello", new HelloWorldHandler());
        server.setExecutor(null);
        return server;
    }


}
