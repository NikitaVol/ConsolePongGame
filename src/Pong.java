import core.GameState;
import render.ConsoleRenderer;
import util.Constants;
import javax.swing.*;
import java.util.Scanner;

public class Pong {
    private static final Scanner scanner = new Scanner(System.in);
    private static ConsoleRenderer renderer;
    private static GameState state;

    public static void main(String[] args) {

        renderer = new ConsoleRenderer();

        while (!state.isGameOver()) {
            System.out.print("\033[2J\033[H");
            System.out.print("\033[?25l");
            System.out.flush();
            renderer.render(state);
            System.out.print("\033[?25h");
            System.out.flush();

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
                    if (paddle1_y < Constants.HEIGHT - 4) paddle1_y++;
                    break;
                case 'k':
                    if (paddle2_y > 1) paddle2_y--;
                    break;
                case 'm':
                    if (paddle2_y < Constants.HEIGHT - 4) paddle2_y++;
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
            if (ball_y - 1 > Constants.HEIGHT - 3) {
                ball_y = Constants.HEIGHT - 3;
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
            if (ball_x - 1 > Constants.WIDTH - 3) {
                score1++;
                ball_x = 40;
                ball_y = 12;
                dx = -1;
                dy = 0;
            }

            if (score1 >= Constants.MAX_SCORE) {
                gameOver = true;
                winner = 1;
            }
            if (score2 >= Constants.MAX_SCORE) {
                gameOver = true;
                winner = 2;
            }
        }
        System.out.print("\033[2J\033[H");
        System.out.flush();
        renderer.render(state);
        System.out.println("\nWinner: Player " + winner + "! Congratulations!");
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