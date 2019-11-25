import {Injectable} from '@angular/core';
import {User} from '../models/user';
import {ROOT_URL} from './service.constants';
import {HttpClient} from '@angular/common/http';
import {RegisterCredentials} from '../models/register-credentials.model';
import { Observable, Subject } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  // Creates a new Observable we can put messages on.
  public isLoggedIn$: Subject<boolean> = new Subject<boolean>();
  public loggedInUserId$: Subject<string> = new Subject<string>();

  public login(username: string, password: string): Promise<boolean> {
    return this.http.post<string>(ROOT_URL + '/login',
      `username=${username}&password=${password}`)
      .toPromise()
      .then((res: string) => {
        console.log('Successful Login userId: ', res);
        this.isLoggedIn$.next(true);
        this.loggedInUserId$.next(res);
        return true;
      })
      .catch(() => {
        console.log('Unsuccessful login');
        this.isLoggedIn$.next(false);
        this.loggedInUserId$.next(null);
        return false;
      });
  }

  public logout(): void {
    // @todo implement logout
  }

  public register(credentials: RegisterCredentials): Promise<User> {
    return this.http.post<User>(ROOT_URL + '/users', {
      username: credentials.username,
      full_name: credentials.fullname,
      sex: credentials.sex,
      sexualOrientation: credentials.orientation,
      email: credentials.email,
      birthdate: credentials.birthdate,
      password: credentials.password}).toPromise();
  }
}
