import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { User } from '../models/user';
import { IS_LOGGING_ENABLED } from '../app.constants';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
  authService: AuthService;

  get isLoggedIn(): boolean {
    return this.currentUser !== null;
  }

  get currentUserId(): string {
    return this.currentUser ? this.currentUser.id : '';
  }
  public currentUser: User = null;

  constructor(authService: AuthService,
              private router: Router) {
    this.authService = authService;

    authService.currentUser$.subscribe((user: User) => {
      console.assert(IS_LOGGING_ENABLED, `Current user is ${user}.`);
      if (user) {
        this.router.navigateByUrl('/home');
        this.currentUser = user;
      }
    });
  }

  onHomeClicked(): void {
    this.router.navigateByUrl('/');
  }

  public onLogOut() {
    this.authService.logout();
  }
}
