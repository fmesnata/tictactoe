package me.fmesnata.tictactoe.lobby;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LobbyServiceImpl implements LobbyService {

    private final Lobby lobby;

    public LobbyServiceImpl(Lobby lobby) {
        this.lobby = lobby;
    }

    @Override
    public List<Player> listPlayers() {
        return this.lobby.listPlayers();
    }

    @Override
    public void addPlayer(String id) {
        String username = id.split("#")[0];
        Player player = new Player();
        player.setId(id);
        player.setUsername(username);
        player.setState(Player.State.ONLINE);
        this.lobby.addPlayer(player);
    }

    @Override
    public void removePlayer(String id) {
        this.lobby.removePlayer(id);
    }

    @Override
    public Player changePlayerState(Player player) {
        return this.lobby.changePlayerState(player);
    }

    @Override
    public Player findPlayerById(String id) {
        return this.lobby.findPlayerById(id);
    }
}
