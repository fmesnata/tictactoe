import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {GameComponent} from './game/game.component';
import {RxStompService} from "@stomp/ng2-stompjs";
import {AuthenticationComponent} from './authentication/authentication.component';

@NgModule({
  declarations: [
    AppComponent,
    GameComponent,
    AuthenticationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    RxStompService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
