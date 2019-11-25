import {Injectable} from '@angular/core';
import {User} from '../models/user';
import {ROOT_URL} from './service.constants';
import {HttpClient} from '@angular/common/http';
import {RegisterCredentials} from '../models/register-credentials.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  public login(username: string, password: string): Promise<boolean> {
    return this.http.post<boolean>(ROOT_URL + '/login',
      `username=${username}&password=${password}`)
      .toPromise()
      .then(() => {
        console.log('Successful Login');
        return true;
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
