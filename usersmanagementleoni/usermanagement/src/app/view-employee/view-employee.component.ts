import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-employee',
  templateUrl: './view-employee.component.html',
  styleUrls: ['./view-employee.component.css']
})
export class ViewEmployeeComponent implements OnInit {
  employee: any;

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    const idString = this.route.snapshot.paramMap.get('id');
    const id = idString ? +idString : null;
  
    if (id !== null) {
      this.authService.getEmployee(id).subscribe({
        next: (data) => this.employee = data,
        error: (err) => console.error('Error fetching employee details:', err)
      });
    } else {
      console.error('Invalid employee ID');
      // Optionally, redirect back to the list or show an error message
    }
  }
  
  backToList(): void {
    this.router.navigate(['/employees']);
  }
}
