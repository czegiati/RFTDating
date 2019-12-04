import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Sex } from 'src/models/sex';
import { Orientation } from '../../models/orientation';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  public registerForm: FormGroup;
  public Sex = Sex;
  public Orientation = Orientation;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) {
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
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
  public onSubmit() {
    if (this.registerForm.invalid) {
      console.warn('Invalid registration');
      return;
    }
    console.log(this.registerForm.value);
    this.authService.register(this.registerForm.value);
  }
  get username() { return this.registerForm.get('username'); }
  get email() { return this.registerForm.get('email'); }
  get password() { return this.registerForm.get('password'); }
  get fullname() { return this.registerForm.get('fullname'); }
  get birthdate() { return this.registerForm.get('birthdate'); }
  get sex() { return this.registerForm.get('sex'); }
  get orientation() { return this.registerForm.get('orientation'); }
  get bio() { return this.registerForm.get('bio'); }
}
