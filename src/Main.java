import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder pb;

        // Путь к вашей jdk папке и испол
        String javaPath = "C:\\Program Files\\jdk-21\\bin\\java.exe";
        if (os.contains("win")) {
            // Windows: открываем cmd с /k (оставить окно открытым)

            pb = new ProcessBuilder(
                    "cmd", "/c", "start", "cmd", "/k",
                    "\"" + javaPath + "\"", "-cp", "src", "Pong"
            );
        } else {
            throw new UnsupportedOperationException("Неизвестная ОС");
        }

        pb.inheritIO(); // чтобы вывод шел в новое окно
        pb.start();


    }
}
