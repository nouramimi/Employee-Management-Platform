import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent {
  employee: any = {};

  constructor(private authService: AuthService, private router: Router) {}

  addEmployee(): void {
    this.authService.addEmployee(this.employee).subscribe(() => {
      this.router.navigate(['/employees']);
    });
  }

  backToList(): void {
    this.router.navigate(['/employees']);
  }
}
