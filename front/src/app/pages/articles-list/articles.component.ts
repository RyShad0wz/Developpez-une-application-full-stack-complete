import { Component, OnInit } from '@angular/core';
import { ArticleService }    from '../../core/services/article.service';
import { Article }        from '../../core/interfaces/article.interface';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {
  articles: Article[] = [];
  error: string | null = null;

  constructor(private articleService: ArticleService) {}

  ngOnInit(): void {
    this.articleService.getAllArticles().subscribe({
      next: data => this.articles = data,
      error: err => this.error = err.message || 'Impossible de charger les articles'
    });
  }
}
