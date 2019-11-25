import { Component, OnInit } from '@angular/core';
import {User} from '../../models/user';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title = 'frontend';

  public users: User[];
  constructor(userservice: UserService) {
    userservice.findAll().then((result: User[]) => {
      console.log(result);
      this.users = result;
    });
  }

  ngOnInit() {
  }

}
