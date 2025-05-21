import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Comment } from '../interfaces/comment.interface';

@Injectable({ providedIn: 'root' })
export class CommentService {
  private API = '/api/comments';
  constructor(private http: HttpClient) {}

  getCommentsByArticle(articleId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(`${this.API}/article/${articleId}`);
  }

  createComment(data: Partial<Comment>): Observable<Comment> {
    return this.http.post<Comment>(this.API, data);
  }
}