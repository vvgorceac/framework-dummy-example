package md.vgorceac.configuration;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration
@SpringBootTest
@AllArgsConstructor
@Log4j2
public class Hooks {
	// No actions needed yet

	@Before
	public void printScenario(Scenario scenario) {
		log.info("Running scenario: {}", scenario);
	}
}
