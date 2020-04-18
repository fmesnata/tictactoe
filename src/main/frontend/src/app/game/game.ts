import {Player} from "../authentication/player";

export interface Game {
  id: string;
  playerOne: Player;
  playerTwo: Player;
}
