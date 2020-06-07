package me.fmesnata.tictactoe.game;

import me.fmesnata.tictactoe.lobby.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameValidatorImpl implements GameValidator {

    @Override
    public Game isThereWinner(Game game) {
        Game checkedGame;
        checkedGame = checkHorizontalLines(game);

        if (checkedGame.getWinner() == null) {
            checkedGame = checkVerticalLines(game);
        }

        if (checkedGame.getWinner() == null) {
            checkedGame = checkDiagonalLines(game);
        }

        if (checkedGame.getState() == Game.State.ONGOING) {
            checkedGame = checkDraw(game);
        }

        return checkedGame;
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
                game.setState(Game.State.WON);
            }
        }

        return game;
    }

    private Game checkVerticalLines(Game game) {
        List<Game.Symbol> grid = game.getGrid();

        for (int i = 0; i < 3; i ++) {
            Game.Symbol firstCell = grid.get(i);
            if (firstCell == null) {
                continue;
            }

            Game.Symbol secondCell = grid.get(i + 3);
            Game.Symbol thirdCell = grid.get(i + 6);
            if (firstCell == secondCell && firstCell == thirdCell) {
                Player winner = firstCell == Game.Symbol.O ? game.getPlayerOne() : game.getPlayerTwo();
                game.setWinner(winner);
                game.setWinningLine(List.of(i, i+3, i+6));
                game.setState(Game.State.WON);
            }
        }

        return game;
    }

    private Game checkDiagonalLines(Game game) {
        List<Game.Symbol> grid = game.getGrid();

        if (grid.get(0) != null && grid.get(0) == grid.get(4) && grid.get(0) == grid.get(8)) {
            Player winner = grid.get(0) == Game.Symbol.O ? game.getPlayerOne() : game.getPlayerTwo();
            game.setWinner(winner);
            game.setWinningLine(List.of(0, 4, 8));
            game.setState(Game.State.WON);
        } else if (grid.get(2) != null && grid.get(2) == grid.get(4) && grid.get(2) == grid.get(6)) {
            Player winner = grid.get(2) == Game.Symbol.O ? game.getPlayerOne() : game.getPlayerTwo();
            game.setWinner(winner);
            game.setWinningLine(List.of(2, 4, 6));
            game.setState(Game.State.WON);
        }

        return game;
    }


    private Game checkDraw(Game game) {
        if (!game.getGrid().contains(null)) {
            game.setState(Game.State.DRAW);
        }
        return game;
    }
}
