package me.fmesnata.tictactoe.game;

import me.fmesnata.tictactoe.lobby.LobbyService;
import me.fmesnata.tictactoe.lobby.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;

@SpringBootTest
class GameServiceImplTest {

    @Autowired
    private GameService gameService;

    @MockBean
    private LobbyService lobbyService;

    @MockBean
    private SimpMessagingTemplate messagingTemplate;

    @Test
    void should_create_two_games_for_four_players() {
        List<Player> players = createPlayers();
        Mockito.when(lobbyService.listPlayers()).thenReturn(players);

        gameService.createGames();

        List<Game> currentGames = gameService.listGames();
        Assertions.assertThat(currentGames.size()).isEqualTo(2);
        Mockito.verify(messagingTemplate, Mockito.times(4)).convertAndSendToUser(Mockito.anyString(), Mockito.eq("/topic/game"), Mockito.any(Game.class));
    }

    private List<Player> createPlayers() {
        Player one = new Player();
        one.setId("one");
        one.setState(Player.State.WAITING_FOR_GAME);
        Player two = new Player();
        two.setId("two");
        two.setState(Player.State.WAITING_FOR_GAME);
        Player three = new Player();
        three.setId("three");
        three.setState(Player.State.WAITING_FOR_GAME);
        Player four = new Player();
        four.setId("four");
        four.setState(Player.State.WAITING_FOR_GAME);

        return List.of(one, two, three, four);
    }

}