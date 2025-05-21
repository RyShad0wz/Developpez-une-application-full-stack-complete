import { Component, OnInit } from '@angular/core';
import { TopicService }      from '../../core/services/topic.service';
import { Topic }          from '../../core/interfaces/topic.interface';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.scss']
})
export class TopicsComponent implements OnInit {
  topics: Topic[] = [];
  error: string | null = null;

  constructor(private topicService: TopicService) {}

  ngOnInit(): void {
    this.topicService.getAllTopics().subscribe({
      next: data => this.topics = data,
      error: err => this.error = err.message || 'Impossible de charger les thèmes'
    });
  }

  subscribe(topicId: number): void {
    // appeler SubscriptionService.subscribe(...)
  }
}
