import {AfterContentInit, Component, OnDestroy, OnInit} from '@angular/core';
import {RxStompService} from '@stomp/ng2-stompjs';
import {Router} from '@angular/router';
import {AuthenticationService} from '../services/authentication.service';
import {rxStompConfig} from '../rx-stomp.config';
import {Player} from '../authentication/player';
import {PlayerState} from "./playerState";
import {Game} from "./game";

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit, OnDestroy, AfterContentInit {

  public players: Player[] = [];
  public currentPlayer: Player;
  public PlayerState: object = PlayerState;
  public game: Game = {grid: [], winningLine: []} as Game;
  public symbol: string;

  constructor(private rxStompService: RxStompService,
              private authenticationService: AuthenticationService,
              private router: Router) { }

  ngOnInit(): void {
    this.connectToWebSocketBroker();

    this.rxStompService.watch('/topic/lobby.players')
      .subscribe(message => this.players = JSON.parse(message.body));

    this.rxStompService.watch('/user/topic/lobby.players.me')
      .subscribe(message => this.currentPlayer = JSON.parse(message.body));

    this.rxStompService.watch("/topic/lobby.players.state")
      .subscribe(message => {
        let player: Player = JSON.parse(message.body);
        let index = this.players.findIndex(e => e.id === player.id);
        this.players.splice(index, 1, player);
      });

    this.rxStompService.watch("/user/topic/game")
      .subscribe(message => {
        const game: Game = JSON.parse(message.body);
        this.changeState(PlayerState.IN_GAME);
        this.game = game;
        this.symbol = game.playerOne.id === this.currentPlayer.id ? 'O' : 'X';
      });

    this.rxStompService.watch("/user/topic/game.state")
      .subscribe(message => {
        this.game = JSON.parse(message.body);
      });
  }

  ngAfterContentInit(): void {
    this.rxStompService.publish({
      destination: '/app/lobby.players',
      body: ''
    });

    this.rxStompService.publish({
      destination: '/app/lobby.players.me',
      body: ''
    });
  }

  public disconnect(): void {
    this.authenticationService.logout().subscribe(() => this.router.navigate(['/auth']));
  }

  private connectToWebSocketBroker() {
    this.rxStompService.configure(rxStompConfig);
    this.rxStompService.activate();
  }

  public changeState(playerState: PlayerState): void {
    this.currentPlayer.state = playerState;
    this.rxStompService.publish({
      destination: '/app/lobby.players.state',
      body: JSON.stringify(this.currentPlayer)
    });
  }

  public addSymbol(index: number): void {
    if (!this.game.winner && this.isMyTurn(this.game.grid) && !this.game.grid[index]) {
      this.game.grid[index] = this.symbol;

      this.rxStompService.publish({
        destination: '/app/game.play',
        body: JSON.stringify(this.game)
      });
    }
  }

  public isCellWinning(index: number) {
    return this.game.winningLine.indexOf(index) > -1;
  }

  public playerCanPlay(): boolean {
    return this.currentPlayer.state === PlayerState.ONLINE
      || (this.game.winner && this.currentPlayer.state === PlayerState.IN_GAME);
  }

  public playerCanCancelSearch(): boolean {
    return this.currentPlayer.state === PlayerState['WAITING_FOR_GAME'];
  }

  ngOnDestroy(): void {
    this.rxStompService.deactivate();
  }

  private isMyTurn(grid: string[]): boolean {
    const length = grid.filter(p => p !== null).length;
    return this.symbol === 'O' && length % 2 === 0 || this.symbol === 'X' && length % 2 !== 0;
  }
}
