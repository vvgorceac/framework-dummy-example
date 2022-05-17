package md.vgorceac.runner;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "classpath:features",
		plugin = {"pretty",
//                "html:build/cucumber/pretty",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
				"summary"},
		glue = {"md.vgorceac"},
		monochrome = true
)
@SuppressWarnings("NewClassNamingConvention")
public class Runner extends DummyTestRunner {
}
