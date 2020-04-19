import {InjectableRxStompConfig} from "@stomp/ng2-stompjs";
import SockJS from 'sockjs-client';


export const rxStompConfig: InjectableRxStompConfig = {
  webSocketFactory: () => new SockJS('/ws'),
  // connectHeaders: {
  //   login: '',
  //   passcode: ''
  // },
  heartbeatIncoming: 0,
  heartbeatOutgoing: 20000,
  reconnectDelay: 1000,
  // debug: (msg: string): void => {
  //   console.log(new Date(), msg);
  // }
};
