package cukepresentation;

import cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author: Stephen Abrams
 *
 * this class must end with the word "Test"
 */
@RunWith(Cucumber.class)
@Cucumber.Options(features = {"src/test/resources/cukepresentation/talk_to_the_world.feature"})
public class AcceptanceTest {
}
