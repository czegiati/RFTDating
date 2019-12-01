import { Component } from '@angular/core';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-view-profile',
  templateUrl: './view-profile.component.html',
  styleUrls: ['./view-profile.component.css']
})
export class ViewProfileComponent {
  public user: User;

  constructor(public userService: UserService,
              public route: ActivatedRoute) {

    const userId = route.snapshot.paramMap.get('id');
    userService.getUserById(userId).then(userById => this.user = userById);
  }
}
