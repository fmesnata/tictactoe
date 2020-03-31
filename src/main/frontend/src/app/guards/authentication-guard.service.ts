import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {Observable} from "rxjs";
import {catchError, map} from "rxjs/operators";
import {AuthenticationService} from "../services/authentication.service";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuardService implements CanActivate {

  constructor(private authenticationService: AuthenticationService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean|UrlTree> {
    if (state.url === '/auth') {
      return this.authenticationService.checkAuthentication()
        .pipe(
          map(response => this.router.createUrlTree(['/game'])),
          catchError(err => {
            return new Observable<boolean>(o => {
              o.next(true);
              o.complete();
            });
          })
        );
    } else {
      return this.authenticationService.checkAuthentication()
        .pipe(
          map(response => true),
          catchError(err => {
            return new Observable<UrlTree>(o => {
              o.next(this.router.createUrlTree(['/auth']));
              o.complete();
            });
          })
        );
    }
  }
}
