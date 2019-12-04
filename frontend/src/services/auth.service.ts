import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { ROOT_URL } from './service.constants';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { RegisterCredentials } from '../models/register-credentials.model';
import { ReplaySubject, Subject } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
import { getUserFromServerObject } from './user-utilities';
import { IS_LOGGING_ENABLED } from '../app.constants';

const USER_COOKIE_NAME = 'user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,
              private cookieService: CookieService) {
      // Check if there was a logged in user stored in the cookies.
      const cookieText = cookieService.get(USER_COOKIE_NAME);
      if (!cookieText) { return; }

      let user: User;
      try {
          user = JSON.parse(cookieService.get(USER_COOKIE_NAME));
      } catch (e) {
          console.assert(IS_LOGGING_ENABLED, 'No logged in user found!');
      }
      if (user) {
        this.setCurrentUser(user);
      }
  }

  // Creates a new Observable we can put messages on.
  public currentUser$: Subject<User> = new ReplaySubject<User>(1);

  public login(username: string, password: string): Promise<boolean> {
    return this.http.post<any>(ROOT_URL + '/login', `username=${username}&password=${password}`,
        { observe: 'response'})
        .toPromise()
        .then((res: HttpResponse<string>) => {
          console.log('Successful Login userId: ', res);

          const user = getUserFromServerObject(res.body);
          this.setCurrentUser(user);

          // Storing the logged in user in a cookie.
          this.cookieService.set('user', JSON.stringify(user));
          return true;
        })
        .catch(() => {
          console.log('Unsuccessful login, removing user information.');
          this.logout();
          return false;
        });
  }

  // @todo wire up button with this function
  public logout(): void {
    this.currentUser$.next( null);
    this.cookieService.delete(USER_COOKIE_NAME);
  }

  public register(credentials: RegisterCredentials): Promise<User> {
    return this.http.post<User>(ROOT_URL + '/users', {
      username: credentials.username,
      full_name: credentials.fullname,
      sex: credentials.sex,
      sexualOrientation: credentials.orientation,
      email: credentials.email,
      birthdate: credentials.birthdate,
      password: credentials.password
    }).toPromise();
  }

    /**
     * Sets the current user. Also sends isLoggedIn events.
     * @param user the logged in User object.
     */
    private setCurrentUser(user: User) {
        this.currentUser$.next(user);
    }
}
