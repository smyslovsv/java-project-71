package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Differ {

    public static String generate(String format, String filepath1, String filepath2) throws Exception {
        // Чтение файлов:

        // Формируем абсолютный путь, если filePath будет содержать относительный путь,
        // то мы всегда будет работать с абсолютным
        Path path1 = getPath(filepath1);
        Path path2 = getPath(filepath2);

        // Читаем файлы
        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);

        // парсим JSON файлы
        Map<String, Object> map1 = getData(content1);
        Map<String, Object> map2 = getData(content2);
        // получаем список ключей
        Set<String> set = new HashSet<>(map1.keySet());
        set.addAll(map2.keySet());

        List<String> sortedList = new ArrayList<>(set);
        Collections.sort(sortedList);
        System.out.println(sortedList);

        StringBuilder diffString = new StringBuilder("{\n");
        for (String key : sortedList) {
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
    private static Path getPath(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return path;
    }
    private static Map<String, Object> getData(String content) throws Exception {
        Map<String,Object> result = new ObjectMapper().readValue(content, Map.class);
        return result;
    }
}
