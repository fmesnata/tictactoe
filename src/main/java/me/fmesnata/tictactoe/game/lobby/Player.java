package me.fmesnata.tictactoe.game.lobby;

import lombok.Data;

@Data
public class Player {
    enum State {
        ONLINE, WAITING_FOR_GAME, IN_GAME
    }

    private String id;
    private String username;
    private State state;
}
