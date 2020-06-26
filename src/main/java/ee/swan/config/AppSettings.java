package ee.swan.config;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AppSettings {

    @Bean
    public Map<String, String> hobbyCodeList() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("sports", "스포츠");
        map.put("music", "음악");
        return Collections.unmodifiableMap(map);
    }
}
