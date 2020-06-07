import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.scss']
})
export class GridComponent implements OnInit {

  @Input() grid: string[];
  @Input() winningLine: number[];
  @Output() addSymbolEvent: EventEmitter<number> = new EventEmitter<number>();

  constructor() { }

  ngOnInit(): void {
  }

  public addSymbol(index: number): void {
    this.addSymbolEvent.emit(index);
  }

  public isCellWinning(index: number) {
    return this.winningLine.indexOf(index) > -1;
  }
}
