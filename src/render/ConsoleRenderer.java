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
                if (x == state.getPaddle1_x() - 1 && y >= state.getPaddle1_y() - 1 && y <= state.getPaddle1_y() + 1)
                    ch = '|';
                else if (x == state.getPaddle2_x() - 1 && y >= state.getPaddle2_y() - 1 && y <= state.getPaddle2_y() + 1)
                    ch = '|';
                else if (x == state.getBall_x() - 1 && y == state.getBall_y() - 1)
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
