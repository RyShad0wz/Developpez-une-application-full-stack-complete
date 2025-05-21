import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Topic } from '../interfaces/topic.interface';

@Injectable({ providedIn: 'root' })
export class TopicService {
  private API = '/api/topics';
  constructor(private http: HttpClient) {}

  getAllTopics(): Observable<Topic[]> {
    return this.http.get<Topic[]>(this.API);
  }

  getTopicById(id: number): Observable<Topic> {
    return this.http.get<Topic>(`${this.API}/${id}`);
  }
}
