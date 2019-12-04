import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { ROOT_URL } from './service.constants';
import { getUserFromServerObject } from './user-utilities';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {}
  public list: User[] = [];

  public findAll(): Promise<User[]> {
    // @todo cache this call
    return this.http.get<User[]>(ROOT_URL + '/users').toPromise()
        .then((users: any[]) => users.map(getUserFromServerObject));
  }

  public findAllObservable(): Observable<User[]> {
    return this.http.get<User[]>(ROOT_URL + '/users');
  }

  public getUserById(userId: string): Promise<User> {
    return this.findAll().then(users => {
      return users.find((user: User) => user.id === userId);
    });
  }
}
