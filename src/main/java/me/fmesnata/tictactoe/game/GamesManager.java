package me.fmesnata.tictactoe.game;

import java.util.List;

public interface GamesManager {

    void addGame(Game game);

    List<Game> listGames();

    Game findGame(String id);

    Game save(Game game);
}
