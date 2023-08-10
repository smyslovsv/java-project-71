import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import hexlet.code.Differ;

import java.nio.file.Files;
import java.nio.file.Path;

class AppTest {
    @Test
    public void testJson() throws Exception {
        Path path = Differ.getPath("./src/test/resources/result1.txt");
        String expected = Files.readString(path);
        String actual = Differ.generate("stylish",
                "./src/test/resources/file1.json",
                "./src/test/resources/file2.json");
        assertEquals(expected, actual);
    }

    @Test
    public void testYaml() throws Exception {
        Path path = Differ.getPath("./src/test/resources/result1.txt");
        String expected = Files.readString(path);
        String actual = Differ.generate("stylish",
                "./src/test/resources/file1.yml",
                "./src/test/resources/file2.yml");
        assertEquals(expected, actual);
    }
}
