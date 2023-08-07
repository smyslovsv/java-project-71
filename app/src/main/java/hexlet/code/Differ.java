package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

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
    private static Path path1;
    private static Path path2;

    private static Map<String, Object> map1 = new HashMap<>();
    private static Map<String, Object> map2 = new HashMap<>();
    public static String generate(String format, String filepath1, String filepath2) throws Exception {
        path1 = getPath(filepath1);
        path2 = getPath(filepath2);

        map1 = getData(Files.readString(path1));
        map2 = getData(Files.readString(path2));
        // получаем список ключей
        Set<String> set = new HashSet<>(map1.keySet());
        set.addAll(map2.keySet());
        List<String> sortedList = new ArrayList<>(set);
        Collections.sort(sortedList);
        return getDifferent(sortedList);
    }
    public static Path getPath(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return path;
    }
    public static Map<String, Object> getData(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }

    private static String getDifferent(List<String> list) {
        StringBuilder diffString = new StringBuilder("{\n");
        for (String key : list) {
            diffString.append("  ");

            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (map1.get(key).equals(map2.get(key))) {
                    diffString.append("  ").append(key).append(": ").append(map1.get(key));
                } else {
                    diffString.append("- ").append(key).append(": ").append(map1.get(key)).append("\n");
                    diffString.append("  + ").append(key).append(": ").append(map2.get(key));
                }
            } else if (map1.containsKey(key)) {
                diffString.append("- ").append(key).append(": ").append(map1.get(key));
            } else {
                diffString.append("+ ").append(key).append(": ").append(map2.get(key));
            }

            diffString.append("\n");
        }

        return diffString.append("}").toString();
    }
}
