import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-management',
  templateUrl: './employee-management.component.html',
  styleUrls: ['./employee-management.component.css']
})
export class EmployeeManagementComponent implements OnInit {
  employees: any[] = [];
  selectedEmployee: any = null;
  newEmployee: any = { name: '', position: '' };
  errorMessage: string | null = null;
  filteredEmployees: any[] = [];
  filters: { userName?: string; userLogin?: string } = {};
  page: number = 1; 
  itemsPerPage: number = 10;
  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.loadEmployees();
  }

  /*loadEmployees(): void {
    this.authService.getEmployees().subscribe({
      next: (data) => {
        this.employees = data;
        this.applyFilters();
      },
      error: (err) => {
        this.errorMessage = 'An error occurred while fetching employee data.';
        console.error('Error fetching employees:', err);
      }
    });
  }*/
    loadEmployees(): void {
      this.authService.getEmployees().subscribe({
        next: (data) => {
          this.employees = data;
          
          this.applyFilters();
        },
        error: (err) => {
          this.errorMessage = 'An error occurred while fetching employee data.';
          console.error('Error fetching employees:', err);
        }
      });
    }

  selectEmployee(employee: any): void {
    this.selectedEmployee = { ...employee };
  }

  addEmployee(): void {
    this.authService.addEmployee(this.newEmployee).subscribe({
      next: (data) => {
        this.employees.push(data);
        this.newEmployee = { name: '', position: '' };
        this.applyFilters();
      },
      error: (err) => {
        this.errorMessage = 'An error occurred while adding the employee.';
        console.error('Error adding employee:', err);
      }
    });
  }

  editEmployee(employee: any): void {
    this.selectedEmployee = { ...employee };
  }
  onKeydown(event: KeyboardEvent): void {
    if (event.key === 'Enter') {
      this.saveEmployee();
    }
  }
  saveEmployee(): void {
    if (this.selectedEmployee) {
      this.authService.editEmployee(this.selectedEmployee.id, this.selectedEmployee).subscribe({
        next: () => {
          this.loadEmployees();
          this.selectedEmployee = null;
        },
        error: (err) => {
          this.errorMessage = 'An error occurred while updating the employee.';
          console.error('Error updating employee:', err);
        }
      });
    }
  }

  cancelEdit(): void {
    this.selectedEmployee = null;
  }
  deleteEmployee(id: number): void {
    if (confirm('Are you sure you want to delete this employee?')) {
      this.authService.deleteEmployee(id).subscribe({
        next: () => {
          this.employees = this.employees.filter(emp => emp.id !== id);
          this.applyFilters();
          console.log('Employee deleted successfully');
        },
        error: (err) => {
          console.error('Error deleting employee:', err);
        }
      });
    }
  }
  navigateToEditEmployee(employeeId: number): void {
    this.router.navigate(['/employees/edit', employeeId]);
  }
  
  navigateToAddEmployee(): void {
    this.router.navigate(['/employees/add']);
  }
  navigateToViewEmployee(employeeId: number): void {
    this.router.navigate(['/employees/view', employeeId]);
  }
  applyFilters(): void {
    this.filteredEmployees = this.employees.filter(employee => {
      const matchesUserName = !this.filters.userName || employee.userName.toLowerCase().includes(this.filters.userName.toLowerCase());
      const matchesUserLogin = !this.filters.userLogin || employee.userLogin.toLowerCase().includes(this.filters.userLogin.toLowerCase());
      return matchesUserName && matchesUserLogin;
    });
  }
    /*applyFilters(): void {
      this.filteredEmployees = this.employees.filter(employee => {
        const matchesUserName = !this.filters.userName || employee.userName.toLowerCase().includes(this.filters.userName.toLowerCase());
        const matchesUserLogin = !this.filters.userLogin || employee.userLogin.toLowerCase().includes(this.filters.userLogin.toLowerCase());
        return matchesUserName && matchesUserLogin;
      }).slice(this.first, this.first + this.rows); // Apply pagination
    }*/
  

  resetFilters(): void {
    this.filters = {};
    //this.applyFilters();
  }
  /*onPageChange(event: any): void {
    this.first = event.first;
    this.rows = event.rows;
    this.applyFilters(); 
  }*/
}
