package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

import static hexlet.code.Differ.*;
import static hexlet.code.Differ.DELETED;

public class Plain {
    public static String format(List<Map<String, Object>> listDiff) {
        StringBuilder diffString = new StringBuilder();

        for (Map<String, Object> map : listDiff) {
            if (map.containsKey(NOT_CHANGED)) {
                continue;
            }
            if (map.containsKey(CHANGED_OLD)) {
                diffString.append(String.format("Property '%s' was updated. From %s to %s\n", map.get("key"),
                        getValue(map.get(CHANGED_OLD)), getValue(map.get(CHANGED_NEW))));
                continue;
            }
            if (map.containsKey(ADDED)) {
                diffString.append(String.format("Property '%s' was added with value: %s\n",
                        map.get("key"), getValue(map.get(ADDED))));
                continue;
            }
            if (map.containsKey(DELETED)) {
                diffString.append(String.format("Property '%s' was removed\n", map.get("key")));
            }
        }
        return diffString.toString().trim();
    }
    private static String getValue(Object value) {
        String complexValue = "[complex value]";
        if (value == null) {
            return null;
        }
        return switch (value.getClass().getSimpleName()) {
            case "ArrayList", "LinkedHashMap" -> complexValue;
            case "String" -> "'" + value + "'";
            default -> value.toString();
        };
    }
}
