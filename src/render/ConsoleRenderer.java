package render;
import core.GameState;
import util.Constants;

public class ConsoleRenderer implements Renderer {

    @Override
    public void render(GameState state) {
        System.out.print("\033[2J\033[H");
        System.out.flush();

        for (int i = 0; i < Constants.WIDTH; i++) System.out.print("#");
        System.out.println();

        for (int y = 0; y < Constants.HEIGHT - 2; y++) {
            System.out.print("#");
            for (int x = 0; x < Constants.WIDTH - 2; x++) {
                char ch = ' ';
                if (x == Constants.DEFAULT_PADDEL1_X - 1 && y >= state.getPaddle1Y() - 1 && y <= state.getPaddle1Y() + 1)
                    ch = '|';
                else if (x == Constants.DEFAULT_PADDEL2_X - 1 && y >= state.getPaddle2Y() - 1 && y <= state.getPaddle2Y() + 1)
                    ch = '|';
                else if (x == state.getBallX() - 1 && y == state.getBallY() - 1)
                    ch = 'o';
                System.out.print(ch);
            }
            System.out.println("#");
        }

        for (int i = 0; i < Constants.WIDTH; i++) System.out.print("#");
        System.out.println();

        System.out.println("Player1: " + state.getScore1() + "   Player2: " + state.getScore2());
        System.out.println("Controls: A/Z (Player 1), K/M (Player 2), Q - Exit");
        System.out.println("ENTER required");
    }


}
