import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ArticleService } from '../../core/services/article.service';
import { CommentService } from '../../core/services/comment.service';
import { Article } from '../../core/interfaces/article.interface';
import { Comment } from '../../core/interfaces/comment.interface';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/core/services/auth.service';

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
    private articleService: ArticleService,
    private commentService: CommentService,
    private fb: FormBuilder,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    const id = +this.route.snapshot.params['id'];
    this.articleService.getArticleById(id).subscribe({
      next: art => this.article = art,
      error: err => this.error = err.message
    });

    this.loadComments(id);

    this.commentForm = this.fb.group({
      content: ['', Validators.required]
    });
  }

  loadComments(articleId: number): void {
    this.commentService.getCommentsByArticle(articleId).subscribe({
      next: data => this.comments = data,
      error: err => this.error = err.message
    });
  }

  submitComment(): void {
    if (this.commentForm.invalid) return;
    const articleId = this.article.id;

    const payload = {
      content: this.commentForm.value.content,
      articleId
    };

    this.commentService.createComment(payload as any).subscribe({
      next: () => {
        this.commentForm.reset();
        this.loadComments(articleId);
      },
      error: err => this.error = err.message
    });
  }
}
