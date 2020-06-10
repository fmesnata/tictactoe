package me.fmesnata.tictactoe.game;

import me.fmesnata.tictactoe.lobby.LobbyService;
import me.fmesnata.tictactoe.lobby.Player;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final LobbyService lobbyService;

    private final GamesManager  gamesManager;

    private final SimpMessagingTemplate messagingTemplate;

    private final GameValidator gameValidator;

    public GameServiceImpl(LobbyService lobbyService,
                           GamesManager gamesManager,
                           SimpMessagingTemplate messagingTemplate,
                           GameValidator gameValidator) {
        this.lobbyService = lobbyService;
        this.gamesManager = gamesManager;
        this.messagingTemplate = messagingTemplate;
        this.gameValidator = gameValidator;
    }

    @Scheduled(fixedRate = 5000)
    public void createGames() {
        List<Player> playersReady = lobbyService.listPlayers().stream()
                .filter(p -> p.getState() == Player.State.WAITING_FOR_GAME)
                .collect(Collectors.toList());

        removeLastPlayerIfNumbersOfPlayerIsOdd(playersReady);

        for (int i = 0; i < playersReady.size() - 1; i += 2) {
            Player playerOne = playersReady.get(i);
            Player playerTwo = playersReady.get(++i);
            Game game = createGame(playerOne, playerTwo);
            gamesManager.addGame(game);
            sendGameToPlayers(playerOne, playerTwo, game);
        }
    }

    private Game createGame(Player playerOne, Player playerTwo) {
        Game game = new Game();
        List<Game.Symbol> grid = initGrid();
        game.setGrid(grid);
        game.setId(UUID.randomUUID().toString());
        game.setPlayerOne(playerOne);
        game.setPlayerTwo(playerTwo);
        return game;
    }

    private List<Game.Symbol> initGrid() {
        return Arrays.asList(null, null, null, null, null, null, null, null, null);
    }

    private void removeLastPlayerIfNumbersOfPlayerIsOdd(List<Player> playersReady) {
        int numberOfPlayersReady = playersReady.size();

        if (numberOfPlayersReady % 2 != 0) {
            playersReady.remove(numberOfPlayersReady - 1);
        }
    }

    private void sendGameToPlayers(Player playerOne, Player playerTwo, Game game) {
        messagingTemplate.convertAndSendToUser(playerOne.getId(), "/topic/game", game);
        messagingTemplate.convertAndSendToUser(playerTwo.getId(), "/topic/game", game);
    }

    @Override
    public List<Game> listGames() {
        return gamesManager.listGames();
    }

    @Override
    public Game findGame(String id) {
        return gamesManager.findGame(id);
    }

    @Override
    public void save(Game game) {
        gamesManager.save(game);
    }

    @Override
    public Game checkGrid(Game game) {
        return gameValidator.isThereWinner(game);
    }
}
