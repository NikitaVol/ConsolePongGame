package core;

public class GameState {
    private int paddle1_x = 2, paddle1_y = 11;
    private int paddle2_x = 77, paddle2_y = 11;
    private int ball_x = 40, ball_y = 12;
    private int dx = -1, dy = 0;
    private int score1 = 0, score2 = 0;
    private boolean gameOver = false;
    private int winner = 0;

    public GameState(int paddle1_x, int paddle1_y, int paddle2_x, int paddle2_y, int ball_x, int ball_y, int dx, int dy, int score1, int score2, boolean gameOver, int winner) {
        this.paddle1_x = paddle1_x;
        this.paddle1_y = paddle1_y;
        this.paddle2_x = paddle2_x;
        this.paddle2_y = paddle2_y;
        this.ball_x = ball_x;
        this.ball_y = ball_y;
        this.dx = dx;
        this.dy = dy;
        this.score1 = score1;
        this.score2 = score2;
        this.gameOver = gameOver;
        this.winner = winner;
    }

    //Getters
    public int getPaddle1_x() {
        return paddle1_x;
    }

    public int getPaddle1_y() {
        return paddle1_y;
    }

    public int getPaddle2_x() {
        return paddle2_x;
    }

    public int getPaddle2_y() {
        return paddle2_y;
    }

    public int getBall_x() {
        return ball_x;
    }

    public int getBall_y() {
        return ball_y;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public int getScore1() {
        return score1;
    }

    public int getScore2() {
        return score2;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getWinner() {
        return winner;
    }
}
