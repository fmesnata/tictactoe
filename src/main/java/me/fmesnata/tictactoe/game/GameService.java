package me.fmesnata.tictactoe.game;

import java.util.List;

public interface GameService {
    void createGames();

    List<Game> listGames();

    Game findGame(String id);

    void save(Game game);

    Game checkGrid(Game game);
}
