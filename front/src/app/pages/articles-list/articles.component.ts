import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import { ArticleService } from '../../core/services/article.service';
import { SubscriptionService } from '../../core/services/subscription.service';
import { Article } from '../../core/interfaces/article.interface';
import { Topic } from 'src/app/core/interfaces/topic.interface';
import { forkJoin, of } from 'rxjs';
import { catchError, map, switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {
  articles: Article[] = [];
  filteredArticles: Article[] = [];
  subscribedTopicIds: number[] = [];
  sortOldestFirst = true;

  constructor(
    private articleService: ArticleService,
    private subscriptionService: SubscriptionService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadAndFilterArticles();
  }

  private loadAndFilterArticles(): void {
    this.subscriptionService.getMySubscriptions().pipe(
      catchError(err => {
        console.error('Erreur lors de la récupération des abonnements :', err);
        return of([] as Topic[]);
      }),
      switchMap((topics: Topic[]) => {
        this.subscribedTopicIds = topics.map(t => t.id);

        if (this.subscribedTopicIds.length === 0) {
          this.articles = [];
          this.filteredArticles = [];
          return of(null);
        }

        return this.articleService.getAllArticles().pipe(
          catchError(err => {
            console.error('Erreur lors du chargement des articles :', err);
            return of([] as Article[]);
          })
        );
      })
    ).subscribe((allArticles: Article[] | null) => {
      if (!allArticles) {
        return;
      }

      this.articles = allArticles;

    
      this.filteredArticles = this.articles.filter(a =>
        this.subscribedTopicIds.includes(a.topicId)
      );

      this.sortByDate(); 
    });
  }

  create(): void {
    this.router.navigate(['/articles/new']);
  }

  view(id: number): void {
    this.router.navigate(['/articles', id]);
  }

  sortByDate(): void {
  
    this.filteredArticles = [...this.filteredArticles].sort((a, b) => {
      const dateA = new Date(a.createdAt).getTime();
      const dateB = new Date(b.createdAt).getTime();
      return this.sortOldestFirst ? dateA - dateB : dateB - dateA;
    });
    
    this.sortOldestFirst = !this.sortOldestFirst;
  }
}
