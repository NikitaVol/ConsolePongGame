import java.util.Scanner;
import java.io.IOException;
public class Pong {
    public static void main(String[] args) {
        try {
            // Открывает новое окно командной строки и запускает в нем Java-класс
            new ProcessBuilder("cmd.exe", "/c", "start", "java", "MyProgram")
                    .inheritIO() // Опционально
                    .start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}