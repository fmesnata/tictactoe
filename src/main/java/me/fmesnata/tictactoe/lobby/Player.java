package me.fmesnata.tictactoe.lobby;

import lombok.Data;

@Data
public class Player {
    public enum State {
        ONLINE, WAITING_FOR_GAME, IN_GAME
    }

    private String id;
    private String username;
    private State state;
}
