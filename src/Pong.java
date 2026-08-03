import java.util.Scanner;

public class Pong {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 25;
    private static final int MAX_SCORE = 21;

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int paddle1_x = 2, paddle1_y = 11;
        int paddle2_x = 77, paddle2_y = 11;
        int ball_x = 40, ball_y = 12;
        int dx = -1, dy = 0;
        int score1 = 0, score2 = 0;
        boolean gameOver = false;
        int winner = 0;

        while (!gameOver) {
            clearConsole();
            render(paddle1_x, paddle1_y, paddle2_x, paddle2_y, ball_x, ball_y, score1, score2);

            int action = handleInput();
            if (action == -1) {
                System.out.println("\nThe game ended early.");
                return;
            } else if (action == 0) {
                continue;
            }

            switch (action) {
                case 'a':
                    if (paddle1_y > 1) paddle1_y--;
                    break;
                case 'z':
                    if (paddle1_y < HEIGHT - 4) paddle1_y++;
                    break;
                case 'k':
                    if (paddle2_y > 1) paddle2_y--;
                    break;
                case 'm':
                    if (paddle2_y < HEIGHT - 4) paddle2_y++;
                    break;
                default:
                    break;
            }

            ball_x += dx;
            ball_y += dy;

            if (ball_y - 1 < 0) {
                ball_y = 1;
                dy = 1;
            }
            if (ball_y - 1 > HEIGHT - 2) {
                ball_y = HEIGHT - 2;
                dy = -1;
            }

            if (ball_x - 1 == paddle1_x - 1) {
                if (ball_y - 1 >= paddle1_y - 1 && ball_y - 1 <= paddle1_y + 1) {
                    dx = -dx;
                    int hit = (ball_y - 1) - (paddle1_y - 1);
                    if (hit == 0)
                        dy = -1;
                    else if (hit == 1)
                        dy = 0;
                    else if (hit == 2)
                        dy = 1;
                    ball_x = paddle1_x + 1;
                }
            }
            if (ball_x - 1 == paddle2_x - 1) {
                if (ball_y - 1 >= paddle2_y - 1 && ball_y - 1 <= paddle2_y + 1) {
                    dx = -dx;
                    int hit = (ball_y - 1) - (paddle2_y - 1);
                    if (hit == 0)
                        dy = -1;
                    else if (hit == 1)
                        dy = 0;
                    else if (hit == 2)
                        dy = 1;
                    ball_x = paddle2_x - 1;
                }
            }

            if (ball_x - 1 < 0) {
                score2++;
                ball_x = 40;
                ball_y = 12;
                dx = 1;
                dy = 0;
            }
            if (ball_x - 1 > WIDTH - 3) {
                score1++;
                ball_x = 40;
                ball_y = 12;
                dx = -1;
                dy = 0;
            }

            if (score1 >= MAX_SCORE) {
                gameOver = true;
                winner = 1;
            }
            if (score2 >= MAX_SCORE) {
                gameOver = true;
                winner = 2;
            }
        }
        clearConsole();
        render(paddle1_x, paddle1_y, paddle2_x, paddle2_y, ball_x, ball_y, score1, score2);
        System.out.println("\nWinner: Player " + winner + "! Congratulations!");
    }

    private static void clearConsole() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;
            if (os.contains("win")) {
                pb = new ProcessBuilder("cmd", "/c", "cls");
            } else {
                pb = new ProcessBuilder("clear");
            }
            pb.inheritIO().start().waitFor();
        } catch (Exception e) {
            // Если команда не работает – используем ANSI (может не работать) или прокрутку
            System.out.print("\033[2J\033[H");
            System.out.flush();
            // альтернатива – просто напечатать много пустых строк:
            // for (int i = 0; i < 50; i++) System.out.println();
            // System.out.print("\033[H");
        }
    }

    private static void render(int paddle1_x, int paddle1_y, int paddle2_x, int paddle2_y,
                               int ball_x, int ball_y, int score1, int score2) {
        System.out.print("\033[2J\033[H");

        for (int i = 0; i < WIDTH; i++) System.out.print("#");
        System.out.println();

        for (int y = 0; y < HEIGHT - 2; y++) {
            System.out.print("#");
            for (int x = 0; x < WIDTH - 2; x++) {
                char ch = ' ';
                if (x == paddle1_x - 1 && y >= paddle1_y - 1 && y <= paddle1_y + 1)
                    ch = '|';
                else if (x == paddle2_x - 1 && y >= paddle2_y - 1 && y <= paddle2_y + 1)
                    ch = '|';
                else if (x == ball_x - 1 && y == ball_y - 1)
                    ch = 'o';
                System.out.print(ch);
            }
            System.out.println("#");
        }

        for (int i = 0; i < WIDTH; i++) System.out.print("#");
        System.out.println();

        System.out.println("Player1: " + score1 + "   Player2: " + score2);
        System.out.println("Controls: A/Z (Player 1), K/M (Player 2), Q - Exit");
        System.out.println("ENTER required");
    }

    private static int handleInput() {
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