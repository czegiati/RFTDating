import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public shownUsers: User[];

  constructor(userService: UserService,
              authService: AuthService,
              private router: Router) {

    // If there is a current user filter all the users to show only users beside the logged in user.
    authService.currentUser$.subscribe(currentUser => {
      if (currentUser && currentUser.id) {
        userService.findAll().then((allUsers: User[]) => {
          this.shownUsers = allUsers.filter(user => user.id !== currentUser.id);
        });
      }
    });
  }

  ngOnInit() {
  }

  chatWithUser(userId: string): void {
    console.log('Chatting with user!', userId);
    this.router.navigate(['chat', userId]);
  }
}
