import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
  public user: User;

  constructor(public userService: UserService,
              public route: ActivatedRoute) {

    const userId = route.snapshot.paramMap.get('id');
    userService.getUserById(userId).then(userById => this.user = userById);
  }

  ngOnInit() {
  }

}
