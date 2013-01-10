package cukepresentation;
import cucumber.annotation.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 *
 * This was taken from the Cucumber-JVM examples and modified
 * <p>
 * Example of a WebDriver implementation that has delegates all methods to a static instance (REAL_DRIVER) that is only 
 * created once for the duration of the JVM. The REAL_DRIVER is automatically closed when the JVM exits. This makes 
 * scenarios a lot faster since opening and closing a browser for each scenario is pretty slow.
 * To prevent browser state from leaking between scenarios, cookies are automatically deleted before every scenario.
 * </p>
 * <p>
 * A new instance of SharedDriver is created for each Scenario and passed to yor Stepdef classes via Dependency Injection
 * </p>
 * <p>
 * A new instance of the SharedDriver is created for each Scenario and then passed to the Step Definition classes'
 * constructor. They all receive a reference to the same instance. However, the REAL_DRIVER is the same instance throughout
 * the life of the JVM.
 * </p>
 */
public class SharedDriver extends EventFiringWebDriver {
    private static final WebDriver REAL_DRIVER = new ChromeDriver();
    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            REAL_DRIVER.close();
        }
    };

    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public SharedDriver() {
        super(REAL_DRIVER);
    }

    @Override
    public void close() {
        if(Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }

    @Before()
    public void deleteAllCookies() {
        manage().deleteAllCookies();
    }
}
