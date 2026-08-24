package core;

import util.Constants;

public class Paddle {
    private int Y;
    private int X;

    public Paddle(int x) {
        Y = Constants.DEFAULT_PADDEL_Y;
        X = x;
    }

    public int getX() { return X; }
    public int getY() { return Y; }

    public void moveUp() {
        if (Y > 1) Y--;
    }
    public void moveDown() {
        if (Y < Constants.HEIGHT - 4) Y++;
    }
}
