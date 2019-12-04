import { NgModule } from '@angular/core';
import {
    MatSidenavModule,
    MatToolbarModule,
    MatIconModule,
    MatListModule,
  MatInputModule,
  MatInput,
} from '@angular/material';
import { CookieService } from 'ngx-cookie-service';

@NgModule({
    imports: [MatSidenavModule,
        MatToolbarModule,
        MatIconModule,
        MatListModule,
        MatInputModule
    ],
    exports: [
        MatInput
    ]
})
export class MaterialModule {
}
