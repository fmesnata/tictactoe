package me.fmesnata.tictactoe.game;

import me.fmesnata.tictactoe.lobby.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameValidatorImpl implements GameValidator {

    @Override
    public Game isThereWinner(Game game) {
        return checkHorizontalLines(game);
    }

    private Game checkHorizontalLines(Game game) {
        List<Game.Symbol> grid = game.getGrid();

        for (int i = 0; i < 9; i += 3) {
            Game.Symbol firstCell = grid.get(i);
            if (firstCell == null) {
                continue;
            }

            Game.Symbol secondCell = grid.get(i + 1);
            Game.Symbol thirdCell = grid.get(i + 2);
            if (firstCell == secondCell && firstCell == thirdCell) {
                Player winner = firstCell == Game.Symbol.O ? game.getPlayerOne() : game.getPlayerTwo();
                game.setWinner(winner);
                game.setWinningLine(List.of(i, i+1, i+2));
            }
        }

        return game;
    }
}
