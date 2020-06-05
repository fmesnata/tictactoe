package me.fmesnata.tictactoe.game;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GamesManagerImpl implements GamesManager {

    private final Map<String, Game> games = new HashMap<>();

    @Override
    public void addGame(Game game) {
        games.put(game.getId(), game);
    }

    @Override
    public List<Game> listGames() {
        return new ArrayList<>(games.values());
    }

    @Override
    public Game findGame(String id) {
        return games.get(id);
    }

    @Override
    public void save(Game game) {
        games.put(game.getId(), game);
    }
}
