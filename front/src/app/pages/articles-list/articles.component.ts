import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ArticleService } from '../../core/services/article.service';
import { Article } from '../../core/interfaces/article.interface';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {
  articles: Article[] = [];
  sortOldestFirst = true; // toggle pour tri chronologique

  constructor(
    private articleService: ArticleService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadArticles();
  }

  private loadArticles(): void {
    this.articleService.getAllArticles().subscribe({
      next: (data: Article[]) => this.articles = data,
      error: (err: any) => console.error('Error loading articles:', err)
    });
  }

  create(): void {
    this.router.navigate(['/articles/new']);
  }

  view(id: number): void {
    this.router.navigate(['/articles', id]);
  }

  sortByDate(): void {
    this.articles = [...this.articles].sort((a, b) => {
      const dateA = new Date(a.createdAt).getTime();
      const dateB = new Date(b.createdAt).getTime();
      return this.sortOldestFirst ? dateA - dateB : dateB - dateA;
    });
    this.sortOldestFirst = !this.sortOldestFirst;
  }
}
