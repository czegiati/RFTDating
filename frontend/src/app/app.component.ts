import { Component } from '@angular/core';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
  constructor(userservice: UserService){
    userservice.findAll().then((result) => {
      console.log(result);
    });
  }
}
