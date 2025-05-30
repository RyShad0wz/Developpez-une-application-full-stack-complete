import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TopicService } from '../../core/services/topic.service';
import { Topic } from '../../core/interfaces/topic.interface';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.scss']
})
export class TopicsComponent implements OnInit {
  topics: Topic[] = [];

  constructor(
    private topicService: TopicService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.topicService.getAllTopics()
      .subscribe({
        next: (data: Topic[]) => this.topics = data,
        error: (err: any) => console.error(err)
      });
  }

  /** Navigation vers le détail du thème */
  viewTopic(id: number) {
    this.router.navigate(['/topics', id]);
  }
}
