// src/app/app.component.ts
import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { BreakpointObserver } from '@angular/cdk/layout';
import { filter } from 'rxjs/operators';
import { AuthService } from './core/services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  // Indique si la fenêtre est « mobile » (≤ 768px)
  isMobile = false;
  // Indique si l’URL commence par /login ou /register
  isAuthPage = false;
  // Indique si on se trouve sur la home page exacte ("/")
  isHomePage = false;

  // On affichera la barre (drawer + app-navbar) seulement si showNavbar === true
  showNavbar = true;

  constructor(
    public auth: AuthService,
    private router: Router,
    private breakpointObserver: BreakpointObserver
  ) {}

  ngOnInit(): void {
    // 1) Observer la taille d’écran (mobile vs desktop)
    this.breakpointObserver
      .observe('(max-width: 768px)')
      .subscribe((state) => {
        this.isMobile = state.matches;
        this.updateNavbarVisibility();
      });

    // 2) Observer les changements de route pour mettre à jour isAuthPage et isHomePage
    this.router.events
      .pipe(filter((e) => e instanceof NavigationEnd))
      .subscribe(() => {
        const url = this.router.url.split('?')[0]; // ignore les query params
        this.isAuthPage = url.startsWith('/login') || url.startsWith('/register');
        this.isHomePage = url === '/';
        this.updateNavbarVisibility();
      });
  }

  // Met à jour showNavbar en fonction des trois flags ci-dessus
  private updateNavbarVisibility() {
    // 1) Si on est sur la home page, on masque toujours la barre
    if (this.isHomePage) {
      this.showNavbar = false;
      return;
    }
    // 2) Sinon, si on est sur /login ou /register ET qu’on est en mobile, on masque aussi
    if (this.isAuthPage && this.isMobile) {
      this.showNavbar = false;
      return;
    }
    // 3) Dans tous les autres cas (desktop sur pages auth, ou n’importe quelle autre route), on affiche
    this.showNavbar = true;
  }

  // Vous pouvez laisser votre méthode isHomePage() si vous l’utilisez ailleurs
  isHomePageRoute() {
    return this.router.url === '/';
  }
}
