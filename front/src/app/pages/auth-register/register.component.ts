import { Component } from '@angular/core';
import { FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';
import { RegisterRequest } from '../../core/interfaces/auth-request.interface';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  error?: string;

  registerForm = this.fb.group({
    username: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(8)]],
    confirm: ['', Validators.required]
  }, { validators: this.passwordsMatch });

  constructor(
    private fb: FormBuilder,
    private auth: AuthService,
    private router: Router
  ) {}

  private passwordsMatch(group: AbstractControl) {
    const pw = group.get('password')?.value;
    const co = group.get('confirm')?.value;
    return pw === co ? null : { notMatching: true };
  }

  submit() {
    if (this.registerForm.invalid) { return; }

    const { username, email, password } = this.registerForm.value;
    const payload: RegisterRequest = {
      name: username!,
      email: email!,
      password: password!
    };

    this.auth.register(payload).subscribe({
      next: () => this.router.navigate(['/']),
      error: err => {
        this.error = err.error?.message || 'Échec de l’inscription';
      }
    });
  }
}
