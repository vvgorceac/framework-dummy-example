package md.vgorceac;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = {"md.vgorceac"})
@EntityScan(basePackages = {"md.vgorceac"})
public class SpringBootCucumberApplication {
}
