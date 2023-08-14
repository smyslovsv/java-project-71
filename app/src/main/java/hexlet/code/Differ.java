package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Differ {

    private static Map<String, Object> map1 = new HashMap<>();
    private static Map<String, Object> map2 = new HashMap<>();
    public static final String NOT_CHANGED = "not changed";
    public static final String CHANGED_OLD = "old value";
    public static final String CHANGED_NEW = "new value";
    public static final String ADDED = "added property";
    public static final String DELETED = "deleted property";

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Path path1 = getPath(filepath1);
        Path path2 = getPath(filepath2);

        String fileFormat1 = getFormat(filepath1);
        String fileFormat2 = getFormat(filepath2);

        map1 = Parser.getData(Files.readString(path1), fileFormat1);
        map2 = Parser.getData(Files.readString(path2), fileFormat2);
        // получаем список ключей
        Set<String> set = new HashSet<>(map1.keySet());
        set.addAll(map2.keySet());
        List<String> sortedList = new ArrayList<>(set);
        Collections.sort(sortedList);
        return getDifferent(sortedList, format);
    }

    private static String getFormat(String filepath) {
        return filepath.substring(filepath.lastIndexOf(".") + 1);
    }

    public static Path getPath(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return path;
    }

    private static String getDifferent(List<String> list, String format) throws Exception {
        List<Map<String, Object>> listDiff = new ArrayList<>();
        for (String key : list) {
            listDiff.add(getDiff(key));
        }

        return Formatter.format(listDiff, format);
    }

    private static Map<String, Object> getDiff(String key) {
        Map<String, Object> mapDiff = new HashMap<>();
        mapDiff.put("key", key);

        if (map1.containsKey(key) && map2.containsKey(key)) {
            boolean checkNull = map1.get(key) != null && map2.get(key) != null;
            if (checkNull && map1.get(key).equals(map2.get(key))) {
                mapDiff.put(NOT_CHANGED, map1.get(key));
            } else {
                mapDiff.put(CHANGED_OLD, map1.getOrDefault(key, null));
                mapDiff.put(CHANGED_NEW, map2.getOrDefault(key, null));
            }
        } else if (map1.containsKey(key)) {
            mapDiff.put(DELETED, map1.getOrDefault(key, null));
        } else {
            mapDiff.put(ADDED, map2.getOrDefault(key, null));
        }

        return new HashMap<>(mapDiff);
    }
}
