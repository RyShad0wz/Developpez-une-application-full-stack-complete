import { Component, Input } from '@angular/core';
import { MatDrawer } from '@angular/material/sidenav';
import { Router } from '@angular/router';
import { AuthService } from './../../../core/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  @Input() drawer?: MatDrawer;
  @Input() isVisitor = false;

  constructor(
    public auth: AuthService,
    private router: Router
  ) {}

  goHome() {
    if (this.auth.isLogged()) {
      this.router.navigate(['/articles']);
    } else {
      this.router.navigate(['/']);
    }
  }

  logout() {
    this.auth.logout();
    this.router.navigate(['/']);
    this.drawer?.close();
  }
}
