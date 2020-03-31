import {Component, OnInit} from '@angular/core';
import {RxStompService} from "@stomp/ng2-stompjs";
import {Router} from "@angular/router";
import {AuthenticationService} from "../services/authentication.service";

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {

  constructor(private rxStompService: RxStompService,
              private authenticationService: AuthenticationService,
              private router: Router) { }

  ngOnInit(): void {
  }

  public disconnect(): void {
    this.authenticationService.logout().subscribe(() => this.router.navigate(['/auth']));
  }
}
