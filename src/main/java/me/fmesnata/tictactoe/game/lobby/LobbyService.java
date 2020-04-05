package me.fmesnata.tictactoe.game.lobby;

import java.util.List;

public interface LobbyService {

    List<Player> listPlayers();

    void addPlayer(String id);

    void removePlayer(String id);

    Player changePlayerState(Player player);

    Player findPlayerById(String id);
}
