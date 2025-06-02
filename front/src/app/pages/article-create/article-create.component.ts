import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TopicService } from '../../core/services/topic.service';
import { ArticleService } from '../../core/services/article.service';
import { Topic } from '../../core/interfaces/topic.interface';
import { Router } from '@angular/router';
import { Location } from '@angular/common';  // <-- importer Location

@Component({
  selector: 'app-article-create',
  templateUrl: './article-create.component.html',
  styleUrls: ['./article-create.component.scss']
})
export class ArticleCreateComponent implements OnInit {
  form!: FormGroup;
  topics: Topic[] = [];
  error: string | null = null;

  constructor(
    private fb: FormBuilder,
    private topicService: TopicService,
    private articleService: ArticleService,
    private router: Router,
    private location: Location     // <-- injecter Location
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      title:   ['', Validators.required],
      content: ['', Validators.required],
      topicId: ['', Validators.required]
    });

    this.topicService.getAllTopics().subscribe(
      data => this.topics = data,
      err => this.error = err.message
    );
  }

  // Méthode appelée par la flèche “back”
  goBack(): void {
    this.location.back();
  }

  submit(): void {
    if (this.form.invalid) return;
    this.articleService.createArticle(this.form.value).subscribe({
      next: () => this.router.navigate(['/articles']),
      error: err => this.error = err.message || 'Erreur lors de la création'
    });
  }
}
