import { Component, OnInit } from '@angular/core';
import { TopicService } from '../../core/services/topic.service';
import { SubscriptionService } from '../../core/services/subscription.service';
import { AuthService } from '../../core/services/auth.service';
import { Topic } from '../../core/interfaces/topic.interface';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.scss']
})
export class TopicsComponent implements OnInit {
  topics: Topic[] = [];
  subscribedTopicIds: number[] = [];
  userId!: number | null;
  error: string | null = null;

  constructor(
    private topicService: TopicService,
    private subscriptionService: SubscriptionService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.userId = this.authService.getCurrentUserId();
    this.loadTopics();
    if (this.userId) {
this.subscriptionService.getMySubscriptions().subscribe({
  next: subs => { this.subscribedTopicIds = subs.map(t => t.id); },
  error: err => this.error = err.message
});
    }
  }

  loadTopics(): void {
    this.topicService.getAllTopics().subscribe({
      next: topics => this.topics = topics,
      error: err => this.error = err.message
    });
  }

  isSubscribed(topicId: number): boolean {
    return this.subscribedTopicIds.includes(topicId);
  }

subscribe(topicId: number): void {
  if (!this.userId) {
    console.error("userId manquant");
    return;
  }
  console.log('Subscribe click', this.userId, topicId); // <--- test
  this.subscriptionService.subscribe(topicId).subscribe({
    next: () => {
      this.subscribedTopicIds.push(topicId); // Mise à jour instantanée !
    },
    error: err => this.error = err.message
  });
}
}
