import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable, of} from 'rxjs';
import { User } from '../models/user';
import { ROOT_URL } from './service.constants';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {}
  public  list: User[] = [];

  public findAll(): Promise<User[]> {
    // @todo cache this call
    return this.http.get<User[]>(ROOT_URL + '/users').toPromise().then(users => users.map(this.getUser));
  }

  public findAllObservable(): Observable<User[]> {
    return this.http.get<User[]>(ROOT_URL + '/users');
  }

  public getUserById(userId: string): Promise<User> {
    return this.findAll().then(users => {
      return users.find((user: User) => user.id === userId);
    });
  }

  private getUser( serverUserObject: any) {
    return {
      id: serverUserObject.user_id + '',
      username: serverUserObject.username,
      email: serverUserObject.email,
      fullname: serverUserObject.full_name,
      birthdate: serverUserObject.birthdate,
      sex: serverUserObject.sex,
      orientation: serverUserObject.sexualOrientation,
      bio: serverUserObject.bio
    };
  }


}
