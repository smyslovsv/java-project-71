package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    private static final String STYLISH_FORMAT = "stylish";
    private static final String JSON_FORMAT = "json";
    private static final String PLAIN_FORMAT = "plain";
    public static String format(List<Map<String, Object>> listDiff, String format) throws Exception {
        switch (format) {
            case (STYLISH_FORMAT) -> {
                return Stylish.format(listDiff);
            }
            case (JSON_FORMAT) -> {
                return Json.format(listDiff);
            }
            case (PLAIN_FORMAT) -> {
                return Plain.format(listDiff);
            }
            default -> throw new Exception("Wrong out format");
        }
    }
}
