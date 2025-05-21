import { Component, OnInit }      from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService }            from '../../core/services/user.service';
import { SubscriptionService }    from '../../core/services/subscription.service';
import { User }                from '../../core/interfaces/user.interface';
import { Subscription }        from '../../core/interfaces/subscription.interface';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  user!: User;
  subscriptions: Subscription[] = [];
  profileForm!: FormGroup;
  error: string | null = null;

  constructor(
    private userService: UserService,
    private subService: SubscriptionService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.loadProfile();
    this.profileForm = this.fb.group({
      username: ['', Validators.required],
      email:    ['', [Validators.required, Validators.email]],
      password: ['']
    });
  }

  loadProfile(): void {
    this.userService.getCurrentUser().subscribe({
      next: u => {
        this.user = u;
        this.profileForm.patchValue({ username: u.username, email: u.email });
      },
      error: err => this.error = err.message
    });
    this.subService.getSubscriptionsByUser(this.user.id).subscribe(
      data => this.subscriptions = data,
      err => this.error = err.message
    );
  }

  updateProfile(): void {
    if (this.profileForm.invalid) return;
    this.userService.updateUser(this.user.id, this.profileForm.value).subscribe({
      next: user => {
        this.user = user;
      },
      error: err => this.error = err.message
    });
  }

  unsubscribe(topicId: number): void {
    this.subService.unsubscribe(this.user.id, topicId);
    this.subscriptions = this.subscriptions.filter(s => s.topicId !== topicId);
  }
}
