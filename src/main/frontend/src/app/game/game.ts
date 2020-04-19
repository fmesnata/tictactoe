import {Player} from "../authentication/player";

export interface Game {
  id: string;
  grid: string[];
  playerOne: Player;
  playerTwo: Player;
}
