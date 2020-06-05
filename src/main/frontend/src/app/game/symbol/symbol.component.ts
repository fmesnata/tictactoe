import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-symbol',
  templateUrl: './symbol.component.html',
  styleUrls: ['./symbol.component.scss']
})
export class SymbolComponent implements OnInit {

  @Input() symbol: string;
  @Input() winning: boolean;

  constructor() { }

  ngOnInit(): void {
  }

}
