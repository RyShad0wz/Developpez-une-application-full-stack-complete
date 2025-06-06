import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Article } from '../interfaces/article.interface';

@Injectable({ providedIn: 'root' })
export class ArticleService {
  private API = '/api/articles';
  constructor(private http: HttpClient) {}

  getAllArticles(): Observable<Article[]> {
    return this.http.get<Article[]>(this.API);
  }

  getArticleById(id: number): Observable<Article> {
    return this.http.get<Article>(`${this.API}/${id}`);
  }

  createArticle(data: Partial<Article>): Observable<Article> {
    return this.http.post<Article>(this.API, data);
  }
}