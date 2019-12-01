import { Component } from '@angular/core';
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

  constructor(authService: AuthService,
              private router: Router) {

    // This is how we can get every change on the loggedIn
    authService.isLoggedIn$.subscribe(loggedInResponse => {
      this.isLoggedIn = loggedInResponse;
    });

    authService.loggedInUserId$.subscribe(userId => {
      this.currentUserId = userId;
    });
  }

  onHomeClicked(): void {
    this.router.navigateByUrl('/');
  }

}
