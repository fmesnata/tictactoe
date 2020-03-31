import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {GameComponent} from "./game/game.component";
import {AuthenticationComponent} from "./authentication/authentication.component";
import {AuthenticationGuardService} from "./guards/authentication-guard.service";


const routes: Routes = [
  {path: 'auth', component: AuthenticationComponent, canActivate: [AuthenticationGuardService]},
  {path: 'game', component: GameComponent, canActivate: [AuthenticationGuardService]},
  {path: '', redirectTo: '/game', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
