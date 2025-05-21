import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User }       from '../interfaces/user.interface';

@Injectable({ providedIn: 'root' })
export class UserService {
  private API = '/api/users';

  constructor(private http: HttpClient) {}

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.API}/${id}`);
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.API);
  }

  updateUser(id: number, data: Partial<User & { password?: string }>): Observable<User> {
    return this.http.put<User>(`${this.API}/${id}`, data);
  }

  /** Récupère les infos de l’utilisateur courant */
  getCurrentUser(): Observable<User> {
    return this.http.get<User>(`${this.API}/me`);
  }
}
