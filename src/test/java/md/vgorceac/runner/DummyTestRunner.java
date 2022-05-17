package md.vgorceac.runner;

import io.cucumber.testng.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@SuppressWarnings("NewClassNamingConvention")
public class DummyTestRunner extends AbstractTestNGCucumberTests {
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        if (testNGCucumberRunner == null)
            setUpClass();
        final Object[][] scenarios = testNGCucumberRunner.provideScenarios();
        return new AllureTestNGFilter().filter(scenarios);
    }

    @SuppressWarnings({"unused", "groupsTestNG"})
    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        // the 'featureWrapper' parameter solely exists to display the feature
        // file in a test report
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        if (testNGCucumberRunner == null) {
            return;
        }
        testNGCucumberRunner.finish();
    }
}
