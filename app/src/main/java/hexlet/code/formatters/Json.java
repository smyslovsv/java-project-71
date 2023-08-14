package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.List;
import java.util.Map;

public class Json {
    public static String format(List<Map<String, Object>> listDiff) throws JsonProcessingException {
        StringBuilder result = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
        return result.append(writer.writeValueAsString(listDiff)).append("\n").toString().trim();
    }
}
