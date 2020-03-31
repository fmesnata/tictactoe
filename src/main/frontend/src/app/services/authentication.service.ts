import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClient: HttpClient) { }

  public checkAuthentication(): Observable<HttpResponse<Object>> {
    return this.httpClient.get<HttpResponse<Object>>("/api/authentication/me", {observe: 'response'});
  }

  public login(username): Observable<void> {
    let body = new URLSearchParams();
    body.set('username', username);
    body.set('password', username);
    const options = {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
    };

    return this.httpClient.post<void>("/login", body.toString(), options);
  }

  public logout(): Observable<void> {
    return this.httpClient.post<void>("/logout", {});
  }
}
