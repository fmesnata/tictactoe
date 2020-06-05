package me.fmesnata.tictactoe.game;

import me.fmesnata.tictactoe.game.Game.Symbol;
import me.fmesnata.tictactoe.lobby.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static me.fmesnata.tictactoe.game.Game.Symbol.O;
import static me.fmesnata.tictactoe.game.Game.Symbol.X;

public class GameValidatorTest {

    private GameValidator gameValidator;

    @BeforeEach
    void setup() {
        gameValidator = new GameValidatorImpl();
    }

    @Test
    void should_return_p1_when_horizontal_line_is_O() {
        Game game = new Game();
        List<Symbol> grid = Arrays.asList(O, O, O, null, X, null, X, null, null);
        game.setGrid(grid);
        Player p1 = new Player();
        p1.setUsername("p1");
        game.setPlayerOne(p1);
        Player p2 = new Player();
        p2.setUsername("p2");
        game.setPlayerTwo(p2);

        Game updatedGame = gameValidator.isThereWinner(game);

        Assertions.assertThat(updatedGame.getWinner()).isEqualTo(p1);
    }

    @Test
    void should_return_p2_when_horizontal_line_is_X() {
        Game game = new Game();
        List<Symbol> grid = Arrays.asList(X, O, null, X, X, X, null, null, O);
        game.setGrid(grid);
        Player p1 = new Player();
        p1.setUsername("p1");
        game.setPlayerOne(p1);
        Player p2 = new Player();
        p2.setUsername("p2");
        game.setPlayerTwo(p2);

        Game updatedGame = gameValidator.isThereWinner(game);

        Assertions.assertThat(updatedGame.getWinner()).isEqualTo(p2);
    }

    @Test
    void should_return_empty_when_no_horizontal_line() {
        Game game = new Game();
        List<Symbol> grid = Arrays.asList(X, O, null, X, O, X, null, null, O);
        game.setGrid(grid);
        Player p1 = new Player();
        p1.setUsername("p1");
        game.setPlayerOne(p1);
        Player p2 = new Player();
        p2.setUsername("p2");
        game.setPlayerTwo(p2);

        Game updatedGame = gameValidator.isThereWinner(game);

        Assertions.assertThat(updatedGame.getWinner()).isNull();
    }
}
