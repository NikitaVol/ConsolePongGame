package core;

public class GameState {
    private final int ballX;
    private final int ballY;
    private final int paddle1Y;
    private final int paddle2Y;
    private final int score1;
    private final int score2;
    private final boolean gameOver;
    private final int winner; // 0 - нет, 1 или 2

    public GameState(int ballX, int ballY, int paddle1Y, int paddle2Y,
                     int score1, int score2, boolean gameOver, int winner) {
        this.ballX = ballX;
        this.ballY = ballY;
        this.paddle1Y = paddle1Y;
        this.paddle2Y = paddle2Y;
        this.score1 = score1;
        this.score2 = score2;
        this.gameOver = gameOver;
        this.winner = winner;
    }

    public int getBallX() { return ballX; }
    public int getBallY() { return ballY; }
    public int getPaddle1Y() { return paddle1Y; }
    public int getPaddle2Y() { return paddle2Y; }
    public int getScore1() { return score1; }
    public int getScore2() { return score2; }
    public boolean isGameOver() { return gameOver; }
    public int getWinner() { return winner; }
}