package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    public static Map<String, Object> getData(String content, String format) throws Exception {
        switch (format) {
            case ("json") -> {
                return parserJson(content);
            }
            case ("yml") -> {
                return parserYaml(content);
            }
            default -> throw new Exception("Wrong file format");
        }
    }

    public static Map<String, Object> parserYaml(String yamlSource) throws Exception {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(yamlSource, Map.class);
    }

    public static Map<String, Object> parserJson(String jsonSource) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonSource, Map.class);
    }
}
