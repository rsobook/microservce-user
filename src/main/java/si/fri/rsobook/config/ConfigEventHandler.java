package si.fri.rsobook.config;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import java.util.logging.Logger;

@ApplicationScoped
public class ConfigEventHandler {

    private static final Logger log = Logger.getLogger(ConfigEventHandler.class.getName());

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        String watchedKey = "properties.lists-users";
        ConfigurationUtil.getInstance().subscribe(watchedKey, (String key, String value) -> {
            if (watchedKey.equals(key)) {
                log.info("Changed max listed user to :" + value.toLowerCase());
            }
        });
    }

}
