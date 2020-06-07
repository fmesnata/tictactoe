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
    void winner_p1_game_won_winning_line_set_when_horizontal_line_is_O() {
        Game game = new Game();
        List<Symbol> grid = Arrays.asList(O, O, O, null, X, null, X, null, null);
        game.setGrid(grid);
        Player p1 = createPlayer("p1");
        game.setPlayerOne(p1);
        Player p2 = createPlayer("p2");
        game.setPlayerTwo(p2);

        Game updatedGame = gameValidator.isThereWinner(game);

        Assertions.assertThat(updatedGame.getWinner()).isEqualTo(p1);
        Assertions.assertThat(updatedGame.getWinningLine()).contains(0, 1, 2);
        Assertions.assertThat(updatedGame.getState()).isEqualTo(Game.State.WON);
    }

    @Test
    void winner_p2_game_won_winning_line_set_when_horizontal_line_is_X() {
        Game game = new Game();
        List<Symbol> grid = Arrays.asList(X, O, null, X, X, X, null, null, O);
        game.setGrid(grid);
        Player p1 = createPlayer("p1");
        game.setPlayerOne(p1);
        Player p2 = createPlayer("p2");
        game.setPlayerTwo(p2);

        Game updatedGame = gameValidator.isThereWinner(game);

        Assertions.assertThat(updatedGame.getWinner()).isEqualTo(p2);
        Assertions.assertThat(updatedGame.getWinningLine()).contains(3, 4, 5);
        Assertions.assertThat(updatedGame.getState()).isEqualTo(Game.State.WON);
    }

    @Test
    void winner_null_game_ongoing_winning_line_empty_when_grid_not_full_and_no_winning_line() {
        Game game = new Game();
        List<Symbol> grid = Arrays.asList(X, O, null, X, O, X, null, null, O);
        game.setGrid(grid);
        Player p1 = createPlayer("p1");
        game.setPlayerOne(p1);
        Player p2 = createPlayer("p2");
        game.setPlayerTwo(p2);

        Game updatedGame = gameValidator.isThereWinner(game);

        Assertions.assertThat(updatedGame.getWinner()).isNull();
        Assertions.assertThat(updatedGame.getWinningLine()).isEmpty();
        Assertions.assertThat(updatedGame.getState()).isEqualTo(Game.State.ONGOING);
    }

    @Test
    void winner_p2_game_won_winning_line_set_when_vertical_line_is_O() {
        Game game = new Game();
        List<Symbol> grid = Arrays.asList(X, O, null, X, O, O, X, null, O);
        game.setGrid(grid);
        Player p1 = createPlayer("p1");
        game.setPlayerOne(p1);
        Player p2 = createPlayer("p2");
        game.setPlayerTwo(p2);

        Game updatedGame = gameValidator.isThereWinner(game);

        Assertions.assertThat(updatedGame.getWinner()).isEqualTo(p2);
        Assertions.assertThat(updatedGame.getWinningLine()).contains(0, 3, 6);
        Assertions.assertThat(updatedGame.getState()).isEqualTo(Game.State.WON);
    }

    @Test
    void winner_p1_game_won_winning_line_set_when_diagonal_line_is_O() {
        Game game = new Game();
        List<Symbol> grid = Arrays.asList(O, X, null, X, O, null, X, null, O);
        game.setGrid(grid);
        Player p1 = createPlayer("p1");
        game.setPlayerOne(p1);
        Player p2 = createPlayer("p2");
        game.setPlayerTwo(p2);

        Game updatedGame = gameValidator.isThereWinner(game);

        Assertions.assertThat(updatedGame.getWinner()).isEqualTo(p1);
        Assertions.assertThat(updatedGame.getWinningLine()).contains(0, 4, 8);
        Assertions.assertThat(updatedGame.getState()).isEqualTo(Game.State.WON);
    }

    @Test
    void winner_p2_game_won_winning_line_set_when_diagonal_line_is_O() {
        Game game = new Game();
        List<Symbol> grid = Arrays.asList(O, null, X, O, X, null, X, null, O);
        game.setGrid(grid);
        Player p1 = createPlayer("p1");
        game.setPlayerOne(p1);
        Player p2 = createPlayer("p2");
        game.setPlayerTwo(p2);

        Game updatedGame = gameValidator.isThereWinner(game);

        Assertions.assertThat(updatedGame.getWinner()).isEqualTo(p2);
        Assertions.assertThat(updatedGame.getWinningLine()).contains(2, 4, 6);
        Assertions.assertThat(updatedGame.getState()).isEqualTo(Game.State.WON);
    }

    @Test
    void winner_null_game_draw_winning_line_empty_when_grid_full_and_no_winning_line() {
        Game game = new Game();
        List<Symbol> grid = Arrays.asList(X, O, X, O, X, O, O, X, O);
        game.setGrid(grid);
        Player p1 = createPlayer("p1");
        game.setPlayerOne(p1);
        Player p2 = createPlayer("p2");
        game.setPlayerTwo(p2);

        Game updatedGame = gameValidator.isThereWinner(game);

        Assertions.assertThat(updatedGame.getWinner()).isNull();
        Assertions.assertThat(updatedGame.getWinningLine()).isEmpty();
        Assertions.assertThat(updatedGame.getState()).isEqualTo(Game.State.DRAW);
    }

    private Player createPlayer(String username) {
        Player p = new Player();
        p.setUsername(username);
        return p;
    }
}
