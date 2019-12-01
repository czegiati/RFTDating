import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { ROOT_URL } from './service.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { RegisterCredentials } from '../models/register-credentials.model';
import { ReplaySubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  // Creates a new Observable we can put messages on.
  public isLoggedIn$: Subject<boolean> = new Subject<boolean>();
  public loggedInUserId$: ReplaySubject<string> = new ReplaySubject<string>(1);

  public login(username: string, password: string): Promise<boolean> {
    return this.http.post<string>(ROOT_URL + '/login',
      `username=${username}&password=${password}`,
      {headers: {loggedInUser: username},  observe: 'response'})
      .toPromise()
      .then((res: HttpResponse<string>) => {
        console.log('Headers are ', res.headers)
        console.log('Successful Login userId: ', res);
        this.isLoggedIn$.next(true);
        this.loggedInUserId$.next(res.body);
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
