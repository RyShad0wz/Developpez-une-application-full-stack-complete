import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Subscription } from '../interfaces/subscription.interface';

@Injectable({ providedIn: 'root' })
export class SubscriptionService {
  private API = '/api/subscriptions';
  constructor(private http: HttpClient) {}

  getSubscriptionsByUser(userId: number): Observable<Subscription[]> {
    return this.http.get<Subscription[]>(`${this.API}/user/${userId}`);
  }

  subscribe(userId: number, topicId: number): Observable<Subscription> {
    return this.http.post<Subscription>(this.API, null, {
      params: { userId: userId.toString(), topicId: topicId.toString() }
    });
  }

  unsubscribe(userId: number, topicId: number): Observable<void> {
    return this.http.delete<void>(this.API, {
      params: { userId: userId.toString(), topicId: topicId.toString() }
    });
  }
}