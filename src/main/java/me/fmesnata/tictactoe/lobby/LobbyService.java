package me.fmesnata.tictactoe.lobby;

import java.util.List;

public interface LobbyService {

    List<Player> listPlayers();

    void addPlayer(String id);

    void removePlayer(String id);

    Player changePlayerState(Player player);

    Player findPlayerById(String id);
}
