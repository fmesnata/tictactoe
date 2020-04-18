package me.fmesnata.tictactoe.game;

import me.fmesnata.tictactoe.lobby.LobbyService;
import me.fmesnata.tictactoe.lobby.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
class GameServiceImplTest {

    @Autowired
    private GameService gameService;

    @MockBean
    private LobbyService lobbyService;

    @Test
    void should_create_two_games_for_four_players() {
        List<Player> players = createPlayers();
        Mockito.when(lobbyService.listPlayers()).thenReturn(players);

        gameService.createGames();

        List<Game> currentGames = gameService.listGames();
        Assertions.assertThat(currentGames.size()).isEqualTo(2);
    }

    private List<Player> createPlayers() {
        Player one = new Player();
        one.setState(Player.State.WAITING_FOR_GAME);
        Player two = new Player();
        two.setState(Player.State.WAITING_FOR_GAME);
        Player three = new Player();
        three.setState(Player.State.WAITING_FOR_GAME);
        Player four = new Player();
        four.setState(Player.State.WAITING_FOR_GAME);

        return List.of(one, two, three, four);
    }

}