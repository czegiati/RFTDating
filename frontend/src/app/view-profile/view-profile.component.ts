import {Component, OnInit} from '@angular/core';
import {User} from '../../models/user';
import {UserService} from '../../services/user.service';
import {Orientation} from "../../models/orientation";
import {Observable} from "rxjs";

@Component({
  selector: 'app-view-profile',
  templateUrl: './view-profile.component.html',
  styleUrls: ['./view-profile.component.css']
})
export class ViewProfileComponent implements OnInit {
  public user: User;

  public user$: Observable<User[]>;

  constructor(public userService: UserService) {
    userService.findAll().then((users: User[]) => {
      this.user = users[2];  // @todo get by user Id from URL
      console.log(this.user.orientation === Orientation.Homosexual);
    });
    this.user$ = this.userService.findAllObservable();
  }

  ngOnInit() {
  }

}
