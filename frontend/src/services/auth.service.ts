import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { ROOT_URL } from './service.constants';
import { HttpClient } from '@angular/common/http';
import { Sex } from '../models/sex';
import { Orientation } from '../models/orientation';
import { RegisterCredentials } from '../models/register-credentials.model';
import { exhaustMap } from 'rxjs/operators';
import { of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  public login(username: string, password: string): Promise<boolean> {
    //return this.http.post<boolean>(ROOT_URL + '/login', {username, password})
    return of(true)
    .toPromise()
      .then((isLoggedIn: boolean) => {
        console.log('Successful Login');
        return isLoggedIn;
      })
      .catch(() => {
        console.log('Unsuccessful login');
        return false;
      });
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
