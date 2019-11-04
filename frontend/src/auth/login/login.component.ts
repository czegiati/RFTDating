import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) {
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required, Validators.minLength(6) ],
      password: ['', Validators.required]
    });
  }

  public onSubmit() {
    if (this.loginForm.invalid) {
      console.warn('Invalid login');
      return;
    }
    console.log(this.loginForm.value);
    this.authService.login(this.loginForm.value.username, this.loginForm.value.password);

  }
}
