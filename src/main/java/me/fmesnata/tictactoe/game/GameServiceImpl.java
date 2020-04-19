package me.fmesnata.tictactoe.game;

import me.fmesnata.tictactoe.lobby.LobbyService;
import me.fmesnata.tictactoe.lobby.Player;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final LobbyService lobbyService;

    private final GamesManager  gamesManager;

    private final SimpMessagingTemplate messagingTemplate;

    public GameServiceImpl(LobbyService lobbyService, GamesManager gamesManager, SimpMessagingTemplate messagingTemplate) {
        this.lobbyService = lobbyService;
        this.gamesManager = gamesManager;
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public void createGames() {
        List<Player> playersReady = lobbyService.listPlayers().stream()
                .filter(p -> p.getState() == Player.State.WAITING_FOR_GAME)
                .collect(Collectors.toList());

        int numberOfPlayersReady = playersReady.size();

        if (numberOfPlayersReady % 2 != 0) {
            playersReady.remove(numberOfPlayersReady - 1);
        }

        for (int i = 0; i < playersReady.size() - 1; i += 2) {
            Game game = new Game();
            game.setId(UUID.randomUUID().toString());
            Player playerOne = playersReady.get(i);
            Player playerTwo = playersReady.get(i + 1);
            game.setPlayerOne(playerOne);
            game.setPlayerTwo(playerTwo);
            gamesManager.addGame(game);
            messagingTemplate.convertAndSendToUser(playerOne.getId(), "/topic/game", game);
            messagingTemplate.convertAndSendToUser(playerTwo.getId(), "/topic/game", game);
        }
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
    public Game save(Game game) {
        return gamesManager.save(game);
    }
}
