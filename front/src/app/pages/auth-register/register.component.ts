import { Component, OnInit }       from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router }                  from '@angular/router';
import { AuthService }             from '../../core/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;
  error: string | null = null;

  constructor(
    private fb: FormBuilder,
    private auth: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      username: ['', Validators.required],
      email:    ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      confirm:  ['', Validators.required]
    }, { validators: this.matchPasswords });
  }

  private matchPasswords(group: FormGroup) {
    const pass = group.get('password')?.value;
    const confirm = group.get('confirm')?.value;
    return pass === confirm ? null : { notMatching: true };
  }

  submit(): void {
    if (this.registerForm.invalid) return;
    const { username, email, password } = this.registerForm.value;
    this.auth.register({ username, email, password }).subscribe({
      next: () => this.router.navigate(['/login']),
      error: err => this.error = err.error?.message || 'Échec de l’inscription'
    });
  }
}
