import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder pb;

        if (os.contains("win")) {
            pb = new ProcessBuilder(
                    "cmd", "/c", "start", "cmd", "/k",
                    "javac src/*.java && java -cp src Pong"
            );
        } else {
            throw new UnsupportedOperationException("Неизвестная ОС");
        }

        pb.inheritIO();
        pb.start();
    }
}