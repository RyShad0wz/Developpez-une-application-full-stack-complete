import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ArticleService } from '../../core/services/article.service';
import { CommentService } from '../../core/services/comment.service';
import { Article } from '../../core/interfaces/article.interface';
import { Comment } from '../../core/interfaces/comment.interface';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.scss']
})
export class ArticleDetailComponent implements OnInit {
  article!: Article;
  comments: Comment[] = [];
  commentForm!: FormGroup;
  error: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private articleService: ArticleService,
    private commentService: CommentService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    const id = +this.route.snapshot.params['id'];
    this.loadArticle(id);
    this.loadComments(id);

    this.commentForm = this.fb.group({
      content: ['', Validators.required]
    });
  }

  goBack(): void {
    this.router.navigate(['/articles']);
  }

  private loadArticle(articleId: number): void {
    this.articleService.getArticleById(articleId).subscribe({
      next: art => this.article = art,
      error: err => this.error = err.message || 'Article non trouvé'
    });
  }

  private loadComments(articleId: number): void {
    this.commentService.getCommentsByArticle(articleId).subscribe({
      next: data => this.comments = data,
      error: err => this.error = err.message || 'Impossible de charger les commentaires'
    });
  }

  submitComment(): void {
    if (this.commentForm.invalid) {
      return;
    }
    const articleId = this.article.id;
    this.commentService.createComment({ 
      content: this.commentForm.value.content, 
      articleId 
    } as any).subscribe({
      next: () => {
        this.commentForm.reset();
        this.loadComments(articleId);
      },
      error: err => this.error = err.message || 'Échec de l’envoi'
    });
  }
}
