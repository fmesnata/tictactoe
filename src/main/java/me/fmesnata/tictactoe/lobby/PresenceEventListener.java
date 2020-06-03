package me.fmesnata.tictactoe.lobby;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class PresenceEventListener {

    private final LobbyService lobbyService;

    private final SimpMessagingTemplate messagingTemplate;

    public PresenceEventListener(LobbyService lobbyService, SimpMessagingTemplate messagingTemplate) {
        this.lobbyService = lobbyService;
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void onConnect(SessionConnectedEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        this.lobbyService.addPlayer(headers.getUser().getName());
        this.messagingTemplate.convertAndSend("/topic/lobby.players", this.lobbyService.listPlayers());
    }

    @EventListener
    public void onDisconnect(SessionDisconnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        SecurityContextHolder.getContext().setAuthentication(null);
        this.lobbyService.removePlayer(headers.getUser().getName());
        this.messagingTemplate.convertAndSend("/topic/lobby.players", this.lobbyService.listPlayers());
    }
}
