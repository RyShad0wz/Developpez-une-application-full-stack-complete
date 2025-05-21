import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router }     from '@angular/router';
import { Observable, tap } from 'rxjs';

interface LoginResponse {
  token: string;
}

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  private readonly TOKEN_KEY = 'mdd-token';
  private readonly API = '/api/auth';

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  /** Sauvegarde du token JWT */
  setToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  /** Récupération du token JWT */
  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  /** Suppression du token */
  clearToken(): void {
    localStorage.removeItem(this.TOKEN_KEY);
  }

  /** Indique si l’utilisateur est authentifié */
  isLogged(): boolean {
    return !!this.getToken();
  }

  /** Appel au back-end pour se connecter */
  login(credentials: { identifier: string; password: string }): Observable<LoginResponse> {
    return this.http
      .post<LoginResponse>(`${this.API}/login`, credentials)
      .pipe(
        tap(res => this.setToken(res.token))
      );
  }

  /** Appel au back-end pour s’inscrire */
  register(data: { username: string; email: string; password: string }): Observable<any> {
    return this.http.post(`${this.API}/register`, data);
  }

  /** Déconnexion et redirection */
  logout(): void {
    this.clearToken();
    this.router.navigate(['/']);
  }
}
