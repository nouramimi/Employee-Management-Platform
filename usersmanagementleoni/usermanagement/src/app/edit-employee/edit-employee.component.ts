import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
  styleUrls: ['./edit-employee.component.css']
})
export class EditEmployeeComponent implements OnInit {
  employee: any = {};

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.authService.getEmployee(id).subscribe((data) => {
      this.employee = data;
    });
  }

  saveEmployee(): void {
    this.authService.editEmployee(this.employee.id, this.employee).subscribe({
      next: () => {
        console.log('Employee updated successfully');
        // Optionally show a success message here.
      },
      error: (err) => {
        console.error('Error updating employee:', err);
        // Optionally show an error message here.
      }
    });
  }

  backToList(): void {
    this.router.navigate(['/employees']);
  }
}

