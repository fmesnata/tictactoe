package me.fmesnata.tictactoe.game;

import lombok.Data;
import me.fmesnata.tictactoe.lobby.Player;

import java.util.ArrayList;
import java.util.List;

@Data
public class Game {
    private String id;
    private List<Symbol> grid = new ArrayList<>(9);
    private Player playerOne;
    private Player playerTwo;

    private enum Symbol {
        X,O
    }
}
