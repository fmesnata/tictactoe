package me.fmesnata.tictactoe.game.lobby;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LobbyInMemory implements Lobby {

    private Map<String, Player> players = new ConcurrentHashMap<>();

    @Override
    public List<Player> listPlayers() {
        return List.copyOf(players.values());
    }

    @Override
    public void addPlayer(Player player) {
        players.put(player.getId(), player);
    }

    @Override
    public void removePlayer(String id) {
        players.remove(id);
    }

    @Override
    public Player changePlayerState(Player player) {
        Player p = players.get(player.getId());
        p.setState(player.getState());
        return p;
    }

    @Override
    public Player findPlayerById(String id) {
        return this.players.get(id);
    }
}
