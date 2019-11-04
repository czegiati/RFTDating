import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { ROOT_URL } from './service.constants';
import { HttpClient } from '@angular/common/http';
import { Sex } from '../models/sex';
import { Orientation } from '../models/orientation';
import { RegisterCredentials } from '../models/register-credentials.model';
import { exhaustMap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  public login(username: string, password: string) {
    return this.http.post<User>(ROOT_URL + '/users', {username, password}).toPromise();
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
