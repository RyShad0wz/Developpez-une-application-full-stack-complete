import { NgModule }             from '@angular/core';
import { CommonModule }         from '@angular/common';
import { RouterModule } from '@angular/router'; 
import { MatButtonModule }      from '@angular/material/button';
import { MatInputModule }       from '@angular/material/input';
import { MatToolbarModule }     from '@angular/material/toolbar';
import { MatIconModule }        from '@angular/material/icon';
import { MatCardModule }        from '@angular/material/card';
import { MatMenuModule }        from '@angular/material/menu';
import { MatSidenavModule }     from '@angular/material/sidenav';
import { MatListModule }        from '@angular/material/list';
import { MatSelectModule }      from '@angular/material/select';
import { MatFormFieldModule }   from '@angular/material/form-field';
import { MatSnackBarModule }    from '@angular/material/snack-bar';

// Shared components
import { NavbarComponent }      from './components/navbar/navbar.component';

@NgModule({
  declarations: [
    NavbarComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    MatButtonModule,
    MatInputModule,
    MatToolbarModule,
    MatIconModule,
    MatCardModule,
    MatMenuModule,
    MatSidenavModule,
    MatListModule,
    MatSelectModule,
    MatFormFieldModule,
    MatSnackBarModule
  ],
  exports: [
    // On ré-exporte tout ce qui doit être disponible partout :
    CommonModule,
    RouterModule,
    MatButtonModule,
    MatInputModule,
    MatToolbarModule,
    MatIconModule,
    MatCardModule,
    MatMenuModule,
    MatSidenavModule,
    MatListModule,
    MatSelectModule,
    MatFormFieldModule,
    MatSnackBarModule,
    // Composants partagés
    NavbarComponent
  ]
})
export class SharedModule { }
