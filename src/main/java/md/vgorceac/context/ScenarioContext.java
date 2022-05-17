package md.vgorceac.context;

import io.cucumber.spring.ScenarioScope;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Holds information used during test execution
 */
@ScenarioScope
@Service
@Log4j2
public class ScenarioContext {
    private final Map<Keys, Object> DATA = new ConcurrentHashMap<>();

    public void cleanup() {
        log.info("Cleaning up context storage");
        DATA.clear();
    }

    public void save(Keys key, Object value) {
        log.info("Saving key: {}", key);
        DATA.put(key, value);
    }

    public <T> T get(Keys key, Class<T> tClass) {
        log.info("Reading key: {}", key);
        return tClass.cast(DATA.get(key));
    }

    public void remove(Keys key) {
        log.info("Removing key: {}", key);
        DATA.remove(key);
    }
}
