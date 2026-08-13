package core;


import static util.Constants.DEFAULT_BALL_X;
import static util.Constants.DEFAULT_BALL_Y;

public class Ball {
    private int x, y;
    private int dx, dy;

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Ball(int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public void bounceX() { dx = -dx; }
    public void bounceY() { dy = -dy; }

    public void bounceOffMoveY(int minY, int maxY) {
        if (y - 1 < minY) {
            y = 1;
            dy = 1;
        }
        if (y - 1 > maxY - 3) {
            y = maxY - 3;
            dy = -1;
        }
    }

    public void bounceOffPaddleMove(Paddle paddle_1, Paddle paddle_2) {
        if (x - 1 == paddle_1.getX() - 1) {
            if (y - 1 >= paddle_1.getY() - 1 && y - 1 <= paddle_1.getY() + 1) {
                bounceX();
                int hit = (y - 1) - (paddle_1.getY() - 1);
                if (hit == 0)
                    dy = -1;
                else if (hit == 1)
                    dy = 0;
                else if (hit == 2)
                    dy = 1;
                x = paddle_1.getX() + 1;
            }
        }
        if (x - 1 == paddle_2.getX() - 1) {
            if (y - 1 >= paddle_2.getY() - 1 && y - 1 <= paddle_2.getY() + 1) {
                bounceX();
                int hit = (y - 1) - (paddle_2.getY() - 1);
                if (hit == 0)
                    dy = -1;
                else if (hit == 1)
                    dy = 0;
                else if (hit == 2)
                    dy = 1;
                x = paddle_2.getX() - 1;
            }
        }
    }

    public void resetBall(){
        x = DEFAULT_BALL_X;
        y = DEFAULT_BALL_Y;
        bounceX();
        dy = 0;
    }
}
