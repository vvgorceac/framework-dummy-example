package md.vgorceac.config;

import md.vgorceac.dto.ServiceConfigItem;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "services")
@Component
@Data
public class ServicesConfig {
    private ServiceConfigItem jsonPlaceholderService;
}
