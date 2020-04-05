package me.fmesnata.tictactoe.game.lobby;

import java.util.List;

public interface Lobby {

    List<Player> listPlayers();

    void addPlayer(Player player);

    void removePlayer(String id);

    Player changePlayerState(Player player);

    Player findPlayerById(String id);
}
