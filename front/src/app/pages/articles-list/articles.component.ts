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

  constructor(
    private articleService: ArticleService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadArticles();
  }

  private loadArticles() {
    this.articleService.getAllArticles()
      .subscribe({
        next: (data: Article[]) => this.articles = data,
        error: (err: any) => console.error(err)
      });
  }

  /** Aller à la création d’article */
  create() {
    this.router.navigate(['/articles/create']);
  }

  /** Aller au détail */
  view(id: number) {
    this.router.navigate(['/articles', id]);
  }
}
