package cukepresentation;

import com.google.inject.Inject;
import com.sun.net.httpserver.HttpServer;
import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.ScenarioResult;
import cukepresentation.guice.Port;
import junit.framework.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author: Stephen Abrams
 */
public class WebBrowserStepDefs {

    SharedDriver webDriver = new SharedDriver();
    Integer port;

    @Inject
    public WebBrowserStepDefs(@Port Integer port) {
        this.port = port;
    }

    @When("^I use a web browser to open \"([^\"]*)\"$")
    public void I_use_a_web_browser_to_open(String arg1) throws Throwable {
        String uri;
        if (arg1.startsWith("/"))
            uri = "http://localhost:" + port + arg1;
        else
            uri = arg1;
        webDriver.get(uri);
    }

    @Then("^I should see \"([^\"]*)\"$")
    public void I_should_see(String expectedContent) throws Throwable {
        Assert.assertTrue(webDriver.getPageSource().contains(expectedContent));
    }

}
