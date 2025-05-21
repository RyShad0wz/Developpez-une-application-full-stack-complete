import { Component } from '@angular/core';
import { Router }    from '@angular/router';
import { AuthService } from './../../../core/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  constructor(
    public auth: AuthService,
    private router: Router
  ) {}

  goHome() {
    this.router.navigate(['/']);
  }

  logout() {
    this.auth.logout();
    this.router.navigate(['/']);
  }
}
