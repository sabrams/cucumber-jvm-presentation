package cukepresentation;

import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.PendingException;

/**
 * @author: Stephen Abrams
 */
public class StepDefinitions {

    @When("^a request is made to \"([^\"]*)\"$")
    public void a_request_is_made_to(String arg1) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @Then("^the response body should be \"([^\"]*)\"$")
    public void the_response_body_should_be(String arg1) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

}
