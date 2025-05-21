// src/app/core/services/auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router }     from '@angular/router';
import { Observable, tap } from 'rxjs';

interface LoginResponse { token: string; }

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly TOKEN_KEY = 'mdd-token';
  private readonly API       = '/api/auth';

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  setToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  clearToken(): void {
    localStorage.removeItem(this.TOKEN_KEY);
  }

  isLogged(): boolean {
    return !!this.getToken();
  }

  login(credentials: { identifier: string; password: string }): Observable<LoginResponse> {
    return this.http
      .post<LoginResponse>(`${this.API}/login`, credentials)
      .pipe(tap(res => this.setToken(res.token)));
  }

  register(data: { username: string; email: string; password: string }): Observable<any> {
    return this.http.post(`${this.API}/register`, data);
  }

  logout(): void {
    this.clearToken();
    this.router.navigate(['/']);
  }

  /** 
   * Décode le JWT et renvoie l'ID de l'utilisateur.
   * Adaptation possible selon la clé utilisée ('sub' ou 'userId').
   */
  getCurrentUserId(): number | null {
    const token = this.getToken();
    if (!token) return null;
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      // si l'ID est sous 'sub' ou 'userId', adapte la ligne suivante :
      return payload.userId ?? payload.sub ?? null;
    } catch {
      return null;
    }
  }
}
