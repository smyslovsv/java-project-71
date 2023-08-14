import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import hexlet.code.Differ;

import java.nio.file.Files;
import java.nio.file.Path;

class AppTest {
    @Test
    public void testJson() throws Exception {
        Path path = Differ.getPath("./src/test/resources/result2.txt");
        String expected = Files.readString(path);
        String actual = Differ.generate("./src/test/resources/file3.json",
                "./src/test/resources/file4.json",
                "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testYaml() throws Exception {
        Path path = Differ.getPath("./src/test/resources/result1.txt");
        String expected = Files.readString(path);
        String actual = Differ.generate("./src/test/resources/file1.yml",
                "./src/test/resources/file2.yml",
                "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void testPlainFormat() throws Exception {
        Path path = Differ.getPath("./src/test/resources/resultPlain.txt");
        String expected = Files.readString(path);
        String actual = Differ.generate("./src/test/resources/file3.yml",
                "./src/test/resources/file4.yml",
                "plain");
        assertEquals(expected, actual);
    }
}
