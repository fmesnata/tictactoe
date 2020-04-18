package me.fmesnata.tictactoe.game;

import lombok.Data;
import me.fmesnata.tictactoe.lobby.Player;

@Data
public class Game {
    private String id;
    private Player playerOne;
    private Player playerTwo;
}
