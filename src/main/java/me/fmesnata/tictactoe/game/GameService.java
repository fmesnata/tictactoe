package me.fmesnata.tictactoe.game;

import java.util.List;

public interface GameService {
    void createGames();

    List<Game> listGames();

    Game findGame(String id);

    Game save(Game game);
}
