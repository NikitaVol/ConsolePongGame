package core;

import render.ConsoleRenderer;
import util.Constants;
import input.InputWithConsole;

public class Game {
    private final Ball ball;
    private final Paddle paddle_1, paddle_2;
    private final Player player_1, player_2;
    private boolean gameOver;
    private int winner;

    public Game() {
        ball = new Ball(
                Constants.DEFAULT_BALL_X,
                Constants.DEFAULT_BALL_Y,
                Constants.BALL_INIT_DX,
                Constants.BALL_INIT_DY
        );
        paddle_1 = new Paddle(Constants.DEFAULT_PADDEL1_X);
        paddle_2 = new Paddle(Constants.DEFAULT_PADDEL2_X);

        player_1 = new Player();
        player_2 = new Player();

        gameOver = false;
        winner = 0;
        getCurrentState();
    }

    public void run() {
        ConsoleRenderer renderer = new ConsoleRenderer();
        InputWithConsole input = new InputWithConsole();

        GameState state = getCurrentState();
        while (!state.isGameOver()) {
            System.out.print("\033[?25l");
            System.out.flush();
            renderer.render(state);
            System.out.print("\033[?25h");
            System.out.flush();

            int action = input.handleInput();
            if (action == -1) {
                System.out.println("\nThe game ended early.");
                return;
            }
            commandMove((char)action);

            update();
            state = getCurrentState();

        }
        System.out.print("\033[2J\033[H");
        System.out.flush();
        renderer.render(state);
        System.out.println("\nWinner: Player " + state.getWinner() + "! Congratulations!");
    }

    private GameState getCurrentState() {
        return new GameState(ball.getX(), ball.getY(), paddle_1.getY(), paddle_2.getY(),
                player_1.getScore(), player_2.getScore(),
                gameOver, winner);
    }

    private void update() {
        ball.move();
        ball.bounceOffMoveY(0, Constants.HEIGHT);
        ball.bounceOffPaddleMove(paddle_1, paddle_2);

        if (ball.getX() - 1 < 0) {
            player_2.addScore();
            ball.resetBall();
        }
        if (ball.getX() - 1 > Constants.WIDTH - 3) {
            player_1.addScore();
            ball.resetBall();
        }

        if (player_1.getScore() >= Constants.MAX_SCORE) {
            gameOver = true;
            winner = 1;
        }
        if (player_2.getScore() >= Constants.MAX_SCORE) {
            gameOver = true;
            winner = 2;
        }
    }

    private void commandMove(char action){
        switch (action) {
            case 'a':
                paddle_1.moveUp();
                break;
            case 'z':
                paddle_1.moveDown();
                break;
            case 'k':
                paddle_2.moveUp();
                break;
            case 'm':
                paddle_2.moveDown();
                break;
            default:
                break;
        }
    }
}
