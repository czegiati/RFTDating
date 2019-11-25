import { Component } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from '../models/user';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';

  public isLoggedIn = false;
  public currentUserId: string = null;
  public users: User[];

  constructor(userservice: UserService,
              authService: AuthService,
              private router: Router) {

    // This is how we can get every change on the loggedIn
    authService.isLoggedIn$.subscribe(loggedInResponse => {
      this.isLoggedIn = loggedInResponse;
    });

    authService.loggedInUserId$.subscribe(userId => {
      this.currentUserId = userId;
    });

    userservice.findAll().then((result: User[]) => {
      console.log(result);
      this.users = result;
    });
  }

  onHomeClicked(): void {
    this.router.navigateByUrl('/');
  }

}
