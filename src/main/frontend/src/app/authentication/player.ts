import {PlayerState} from "../game/playerState";

export interface Player {
  id: string;
  username: string;
  state: PlayerState;
}
