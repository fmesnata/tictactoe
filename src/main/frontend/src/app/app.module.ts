import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {GameComponent} from './game/game.component';
import {RxStompService} from "@stomp/ng2-stompjs";
import {AuthenticationComponent} from './authentication/authentication.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatInputModule} from '@angular/material/input'
import {MatButtonModule} from "@angular/material/button";
import {MatCardModule} from "@angular/material/card";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatMenuModule} from "@angular/material/menu";
import { SymbolComponent } from './game/symbol/symbol.component';
import { GridComponent } from './game/grid/grid.component';
import { ResultNotificationComponent } from './game/result-modal/result-notification.component';

@NgModule({
  declarations: [
    AppComponent,
    GameComponent,
    AuthenticationComponent,
    SymbolComponent,
    GridComponent,
    ResultNotificationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    MatMenuModule
  ],
  providers: [
    RxStompService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
