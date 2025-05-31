import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../core/services/user.service';
import { SubscriptionService } from '../../core/services/subscription.service';
import { User } from '../../core/interfaces/user.interface';
import { Topic } from '../../core/interfaces/topic.interface'; // à créer si besoin

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  user!: User;
  subscriptions: Topic[] = []; // Affiche les topics, pas les subscriptions brutes
  profileForm!: FormGroup;
  error: string | null = null;

  constructor(
    private userService: UserService,
    private subService: SubscriptionService,
    private fb: FormBuilder
  ) {}

ngOnInit(): void {
  this.profileForm = this.fb.group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    password: ['']
  });
  this.loadProfile();
}

loadProfile(): void {
  this.userService.getCurrentUser().subscribe({
    next: u => {
      this.user = u;
      this.profileForm.patchValue({ name: u.name, email: u.email });
      // Charger les abonnements ici, maintenant qu’on a this.user.id !
this.subService.getMySubscriptions().subscribe({
  next: data => this.subscriptions = data,
  error: err => this.error = err.message
});

    },
    error: err => this.error = err.message
  });
}



updateProfile(): void {
  if (this.profileForm.invalid) return;
  this.userService.updateCurrentUser(this.profileForm.value).subscribe({
    next: user => {
      this.user = user;
      this.error = null;
    },
    error: err => this.error = err.message
  });
}


unsubscribe(topicId: number): void {
this.subService.unsubscribe(topicId).subscribe({
  next: () => {
    this.subscriptions = this.subscriptions.filter(t => t.id !== topicId);
  },
  error: err => this.error = err.message
});

}

}
