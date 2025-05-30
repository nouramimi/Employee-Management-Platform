import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']  // Correct styleUrls
})
/*export class RegisterComponent {
  username: string = '';
  password: string = '';
  email: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    this.authService.register(this.username, this.password, this.email).subscribe({
      next: () => this.router.navigate(['/login']),
      error: err => console.error('Registration failed', err)
    });
  }
}*/
export class RegisterComponent {
  user = {
    username: '',
    password: '',
    email: ''
  };

  showPassword = false;

  constructor(private authService: AuthService, private router: Router) { }

  togglePasswordVisibility() {
    this.showPassword = !this.showPassword;
  }
  
  register() {
    const form = document.forms[0];  // or use a ViewChild to get the form
    if (!form.checkValidity()) {
        alert('Please enter a valid password.');
        return;
    }
    this.authService.register(this.user).subscribe(
      response => {
        console.log('User registered successfully', response);
        this.router.navigate(['/home']);
      },
      error => {
        console.error('Error registering user', error);
      }
    );
  }
}
