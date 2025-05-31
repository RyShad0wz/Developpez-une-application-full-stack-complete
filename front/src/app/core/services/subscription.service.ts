import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Topic } from '../interfaces/topic.interface';

@Injectable({ providedIn: 'root' })
export class SubscriptionService {
  private API = '/api/subscriptions';
  constructor(private http: HttpClient) {}

  // Utilisé pour afficher les topics auxquels l’utilisateur est abonné
getMySubscriptions(): Observable<Topic[]> {
  return this.http.get<Topic[]>(this.API);
}
  // Abonnement
  subscribe(topicId: number): Observable<any> {
    return this.http.post(`${this.API}/${topicId}`, {}); // --> POST /api/subscriptions/{topicId}
  }

  // Désabonnement
unsubscribe(topicId: number): Observable<void> {
  return this.http.delete<void>(`${this.API}/${topicId}`);
}
}
