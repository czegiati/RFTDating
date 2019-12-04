import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { UserService } from '../../services/user.service';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
  public user: User;
  public editProfileForm: FormGroup;

  constructor(public userService: UserService,
              public route: ActivatedRoute,
              private authService: AuthService,
              private formBuilder: FormBuilder) {

    const userId = route.snapshot.paramMap.get('id');
    userService.getUserById(userId).then(userById => this.user = userById);
  }

  ngOnInit() {
    this.editProfileForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(6)] ],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      fullname: ['', Validators.required],
      birthdate: ['', Validators.required],
      sex: ['', Validators.required],
      orientation: ['', Validators.required],
      bio: ['', Validators.required],
    });
  }
  public onSave() {
    if (this.editProfileForm.invalid) {
      console.warn('Invalid change');
      return;
    }
    console.log(this.editProfileForm.value);
    this.authService.register(this.editProfileForm.value);
  }
}
