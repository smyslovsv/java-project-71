package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

import static hexlet.code.Differ.ADDED;
import static hexlet.code.Differ.DELETED;
import static hexlet.code.Differ.CHANGED_NEW;
import static hexlet.code.Differ.CHANGED_OLD;
import static hexlet.code.Differ.NOT_CHANGED;

public class Stylish {
    public static String format(List<Map<String, Object>> listDiff) {
        StringBuilder diffString = new StringBuilder("{\n");

        for (Map<String, Object> map : listDiff) {
            if (map.containsKey(NOT_CHANGED)) {
                diffString.append("    ")
                        .append(map.get("key"))
                        .append(": ")
                        .append(map.get(NOT_CHANGED))
                        .append("\n");
                continue;
            }
            if (map.containsKey(CHANGED_OLD)) {
                diffString.append("  - ")
                        .append(map.get("key"))
                        .append(": ")
                        .append(map.get(CHANGED_OLD))
                        .append("\n");
            }
            if (map.containsKey(CHANGED_NEW)) {
                diffString.append("  + ")
                        .append(map.get("key"))
                        .append(": ")
                        .append(map.get(CHANGED_NEW))
                        .append("\n");
                continue;
            }
            if (map.containsKey(ADDED)) {
                diffString.append("  + ")
                        .append(map.get("key"))
                        .append(": ")
                        .append(map.get(ADDED))
                        .append("\n");
            }
            if (map.containsKey(DELETED)) {
                diffString.append("  - ")
                        .append(map.get("key"))
                        .append(": ")
                        .append(map.get(DELETED))
                        .append("\n");
            }
        }
        return diffString.append("}").toString();
    }
}
