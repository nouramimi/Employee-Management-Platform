
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user = {
    username: '',
    password: ''
  };
  showPassword = false;
  errorMessage: string | null = null;

  constructor(private authService: AuthService, private router: Router) {}
  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }
  /*onSubmit() {
    if (this.user.username && this.user.password) {
      this.authService.login(this.user.username, this.user.password).subscribe({
        next: (response) => {
          localStorage.setItem('authToken', response.token);
          this.router.navigate(['/home']);
        },
        error: (err) => {
          console.error('Login error:', err);
        }
      });
    }
  }*/
    onSubmit() {
      if (this.user.username && this.user.password) {
        this.authService.login(this.user.username, this.user.password).subscribe({
          next: (response) => {
            if (response.accessToken && response.tokenType) {
              // Combine tokenType and accessToken if needed
              const token = `${response.tokenType.trim()} ${response.accessToken}`;

              localStorage.setItem('authToken', token);
              this.router.navigate(['/home']);
            } else {
              console.error('Invalid response format:', response);
            }
          },
          error: (err) => {
            if (err.status === 401) {
              this.errorMessage = 'The password you entered is incorrect. Please try again.';
            } else {
              this.errorMessage = 'An error occurred during login. Please try again later.';
            }
            console.error('Login error:', err);
          }
        });
      }
    } 
}



