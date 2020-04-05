package me.fmesnata.tictactoe.game.lobby;

import org.springframework.messaging.Message;
import org.springframework.messaging.core.MessagePostProcessor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lobby")
public class LobbyApi {

    private final LobbyService lobbyService;

    private final SimpMessagingTemplate messagingTemplate;

    public LobbyApi(LobbyService lobbyService, SimpMessagingTemplate messagingTemplate) {
        this.lobbyService = lobbyService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/lobby.players")
    @SendTo("/topic/lobby.players")
    public List<Player> listPlayers() {
        return this.lobbyService.listPlayers();
    }

    @MessageMapping("/lobby.players.me")
    public void me(Principal principal, SimpMessageHeaderAccessor acc) {
        Player currentPlayer = this.lobbyService.findPlayerById(principal.getName());
        this.messagingTemplate.convertAndSendToUser(currentPlayer.getId(), "/topic/lobby.players.me", currentPlayer);
    }

    @MessageMapping("/lobby.players.state")
    @SendTo("/topic/lobby.players.state")
    public Player changePlayerState(Player player) {
        return this.lobbyService.changePlayerState(player);
    }
}
