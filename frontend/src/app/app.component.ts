import { Component } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from '../models/user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';

  public users: User[];
  constructor(userservice: UserService) {
    userservice.findAll().then((result: User[]) => {
      console.log(result);
      this.users = result;
    });
  }

}
