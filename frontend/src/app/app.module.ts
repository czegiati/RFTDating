import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserService } from '../services/user.service';
import { RouterModule } from '@angular/router';
import { LoginComponent } from '../auth/login/login.component';
import { AuthModule } from '../auth/auth.module';
import { RegisterComponent } from '../auth/register/register.component';
import { AuthService } from '../services/auth.service';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { ViewProfileComponent } from './view-profile/view-profile.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import {MatCardModule} from '@angular/material/card';
import { HomeComponent } from './home/home.component';
import {MaterialModule} from './material.module';
import {FlexLayoutModule} from '@angular/flex-layout';

@NgModule({
  declarations: [
    AppComponent,
    ViewProfileComponent,
    EditProfileComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    FlexLayoutModule,
    HttpClientModule,
    AuthModule,
    MatToolbarModule,
    MatIconModule,
    RouterModule.forRoot([
      {path: '', component: LoginComponent},
      {path: 'register', component: RegisterComponent},
      {path: 'view-profile/:id', component: ViewProfileComponent },
      {path: 'edit-profile', component: EditProfileComponent},
      {path: 'home', component: HomeComponent}
    ]),
    MatCardModule,
  ],
  providers: [UserService, AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
