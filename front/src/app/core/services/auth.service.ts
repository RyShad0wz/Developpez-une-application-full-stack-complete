import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { Router } from '@angular/router';

import { LoginRequest, RegisterRequest } from '../interfaces/auth-request.interface';
import { AuthenticationResponse } from '../interfaces/auth-response.interface';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly API = '/api/auth';

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  register(request: RegisterRequest): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(`${this.API}/register`, request)
      .pipe(tap(res => this.saveToken(res.token)));
  }

  login(request: LoginRequest): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(`${this.API}/login`, request)
      .pipe(tap(res => this.saveToken(res.token)));
  }

  logout(): void {
    localStorage.removeItem('jwt');
    this.router.navigate(['/login']);
  }

  isLogged(): boolean {
    return !!localStorage.getItem('jwt');
  }

  getToken(): string | null {
    return localStorage.getItem('jwt');
  }

  private saveToken(token: string) {
    localStorage.setItem('jwt', token);
  }

   /** Décode le payload du JWT et renvoie le champ `sub` (ici l’email) */
  getCurrentUsername(): string | null {
    const token = this.getToken();
    if (!token) return null;
    try {
      const [, payload] = token.split('.');
      const decoded = JSON.parse(atob(payload));
      return decoded.sub as string;
    } catch {
      return null;
    }
  }

getCurrentUserId(): number | null {
  const token = this.getToken();
  if (!token) return null;
  try {
    const [, payload] = token.split('.');
    const decoded = JSON.parse(atob(payload));
    return Number(decoded.userId); // conversion explicite !
  } catch {
    return null;
  }
}

}
