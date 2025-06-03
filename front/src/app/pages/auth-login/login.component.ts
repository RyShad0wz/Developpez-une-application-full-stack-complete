import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';
import { LoginRequest } from '../../core/interfaces/auth-request.interface';
import { Location } from '@angular/common';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  error?: string;

  loginForm = this.fb.group({
    identifier: ['', Validators.required],
    password: ['', [Validators.required, Validators.minLength(8)]]
  });

  constructor(
    private fb: FormBuilder,
    private auth: AuthService,
    private router: Router,
    private location: Location
  ) {}

  submit() {
    if (this.loginForm.invalid) { return; }

    // On transforme 'identifier' en email pour l'API
    const { identifier, password } = this.loginForm.value;
    const payload: LoginRequest = {
      email: identifier!,
      password: password!
    };

    this.auth.login(payload).subscribe({
      next: () => this.router.navigate(['/articles']),
      error: err => {
        this.error = err.error?.message || 'Échec de la connexion';
      }
    });
  }

  goBack(): void {
  this.location.back();
  }
}
