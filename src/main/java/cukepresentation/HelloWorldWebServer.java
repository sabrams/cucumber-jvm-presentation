package cukepresentation;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author: Stephen Abrams
 */
public class HelloWorldWebServer {

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8086), 0);
        server.createContext("/hello", new HelloWorldHandler());
        server.setExecutor(null);
        server.start();
    }

    public void stop() {

    }
}
