package input;

import java.util.Scanner;

public class InputWithConsole implements Input{
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public int handleInput() {
        String line = scanner.nextLine();
        if (line.isEmpty()) {
            return 0;   // пустая строка – ничего не делаем
        }

        char ch = line.charAt(0);
        if (ch >= 'A' && ch <= 'Z') ch += 32;  // to lower case

        if (ch == 'q') return -1;

        if (ch == 'a' || ch == 'z' || ch == 'k' || ch == 'm' || ch == ' ')
            return ch;
        else
            return 0;
    }
}
