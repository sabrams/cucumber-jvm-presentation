package cukepresentation;

import com.google.inject.Inject;
import com.sun.net.httpserver.HttpServer;
import cucumber.annotation.After;
import cucumber.annotation.Before;

import java.io.IOException;

/**
 * @author: Stephen Abrams
 */
public class WebServerHooks {

    HttpServer webServer;

    @Inject
    public WebServerHooks(HttpServer webServer) {
        this.webServer = webServer;
    }

    @Before()
    public void startServer() throws IOException {
        this.webServer.start();
    }

    @After()
    public void stopServer() {
        this.webServer.stop(0);
    }
}
