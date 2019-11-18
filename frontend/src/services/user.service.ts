import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable, of} from 'rxjs';
import { User } from '../models/user';
import { ROOT_URL } from './service.constants';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {};
  public  list: User[] = [];

  public findAll(): Promise<User[]> {
    return this.http.get<User[]>(ROOT_URL + '/users').toPromise();
    return of(this.list).toPromise();
  }
}
