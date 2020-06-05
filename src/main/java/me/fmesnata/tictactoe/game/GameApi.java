package me.fmesnata.tictactoe.game;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameApi {

    private final GameService gameService;
    private final SimpMessagingTemplate messagingTemplate;

    public GameApi(GameService gameService, SimpMessagingTemplate messagingTemplate) {
        this.gameService = gameService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/game.play")
    public void play(@Payload Game game) {
        Game existingGame = gameService.findGame(game.getId());
        existingGame.setGrid(game.getGrid());
        Game updated = gameService.checkGrid(game);
        gameService.save(updated);
        messagingTemplate.convertAndSendToUser(game.getPlayerOne().getId(), "/topic/game.state", updated);
        messagingTemplate.convertAndSendToUser(game.getPlayerTwo().getId(), "/topic/game.state", updated);
    }
}
