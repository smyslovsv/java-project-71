package hexlet.code;

import picocli.CommandLine.Option;

public class Differ {

    @Option(names = {"-h", "--help"}, description = "Show this help message and exit.")
    boolean help;
    @Option(names = {"-V", "--version"}, description = "Print version information and exit.")
    boolean version;
    public static String generate() {
        return "Hello, World!";
    }
}
