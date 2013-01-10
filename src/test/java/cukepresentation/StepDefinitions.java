package cukepresentation;

import com.google.inject.Inject;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cukepresentation.guice.Port;
import junit.framework.Assert;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;

/**
 * @author: Stephen Abrams
 */
public class StepDefinitions {

    HttpResponse response;
    String body;

    Integer port;

    @Inject
    public StepDefinitions(@Port Integer port) {
        this.port = port;
    }

    @When("^a GET request is made to \"([^\"]*)\"$")
    public void a_get_request_is_made_to(String arg1) throws Throwable {
        HttpClient httpclient = new DefaultHttpClient();
        String uri;
        if (arg1.startsWith("/"))
            uri = "http://localhost:" + port + arg1;
        else
            uri = arg1;

        HttpGet httpget = new HttpGet(uri);
        response = httpclient.execute(httpget);
    }

    @Then("^the response body should be \"([^\"]*)\"$")
    public void the_response_body_should_be(String expectedBody) throws Throwable {
        Assert.assertEquals(expectedBody, body());

    }

    /**
     * Use this method to make sure we read the body only once
     *
     * @return the response body
     */
    private String body() {
        if (this.body != null)
            return this.body;
        this.body = "";
        try {
            boolean isFirstLine = true;
            String line;
            InputStream is = response.getEntity().getContent();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(is));
            while ((line = bufferedReader.readLine()) != null) {
                if (!isFirstLine)
                    body = body + System.getProperty("line.separator");
                body = body + line;
                isFirstLine = false;
            }
        } catch (IOException e) {
            throw new RuntimeException("Failure while attempting to read HTTP response body", e);
        }
        return this.body;
    }

}
