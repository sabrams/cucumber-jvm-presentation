from urllib2 import Request, urlopen

@When('^a request is made to "([^"]*)"$')
def a_request_is_made_to(self, arg1):
    if (arg1.startswith("/")):
        uri = "http://localhost:8080" + arg1
    else:
        uri = arg1
    request = Request(uri, None)
    self.response = urlopen(request)


@Then('^the response body should be "([^"]*)"$')
def the_response_body_should_be(self, expected_body):
    assert (body(self) == expected_body)


def body(self):
    if (self.response_body is not None):
        return self.response_body
    self.response_body = self.response.read()
    return self.response_body

