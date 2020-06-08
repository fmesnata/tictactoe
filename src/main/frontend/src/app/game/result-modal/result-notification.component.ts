import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-result-notification',
  templateUrl: './result-notification.component.html',
  styleUrls: ['./result-notification.component.scss']
})
export class ResultNotificationComponent implements OnInit {

  @Input() public gameState: string;
  @Input() public isWinner: boolean;

  constructor() { }

  ngOnInit(): void {
  }

}
