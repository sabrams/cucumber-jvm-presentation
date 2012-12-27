from urllib2 import Request, urlopen
import cukepresentation.HelloWorldWebServer as HelloWorldWebServer

# Note that parenthesis are required
@Before()
def start_server(self):
    self.web_server = HelloWorldWebServer()
    self.web_server.start()

@After()
def stop_server(self):
    self.web_server.stop()


@When('^a request is made to "([^"]*)"$')
def a_request_is_made_to(self, arg1):
    if (arg1.startswith("/")):
        uri = "http://localhost:8086" + arg1
    else:
        uri = arg1
    request = Request(uri, None)
    self.response = urlopen(request)


@Then('^the response body should be "([^"]*)"$')
def the_response_body_should_be(self, expected_body):
    assert body(self) == expected_body, "Expected: %s, but was: %s" % (expected_body, body(self))


def body(self):
    try:
        return self.response_body
    except AttributeError:
        self.response_body = self.response.read()
        return self.response_body

