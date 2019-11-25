import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable, of} from 'rxjs';
import { User } from '../models/user';
import { ROOT_URL } from './service.constants';
import {Sex} from "../models/sex";
import {Orientation} from "../models/orientation";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {}
  public  list: User[] = [];

  public findAll(): Promise<User[]> {
    return this.http.get<User[]>(ROOT_URL + '/users').toPromise().then(users => users.map(this.getUser));
  }

  public getUser( serverUserObject: any) {
    return {
      username: serverUserObject.username,
      email: serverUserObject.email,
      fullname: serverUserObject.full_name,
      birthdate: serverUserObject.birthdate,
      sex: serverUserObject.sex,
      orientation: serverUserObject.sexualOrientation,
      bio: serverUserObject.bio
    };
  }

  public findAllObservable(): Observable<User[]>{
    return this.http.get<User[]>(ROOT_URL + '/users');
  }


}
