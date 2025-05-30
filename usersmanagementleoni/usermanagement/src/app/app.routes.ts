import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AuthGuard } from './auth.guard';
import { UploadComponent } from './upload/upload.component';
import { EmployeeManagementComponent } from './employee-management/employee-management.component';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { EditEmployeeComponent } from './edit-employee/edit-employee.component';
import { ViewEmployeeComponent } from './view-employee/view-employee.component';
export const appRoutes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'employees', component: EmployeeManagementComponent , canActivate: [AuthGuard]},
  { path: 'upload', component: UploadComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'employees/add', component: AddEmployeeComponent , canActivate: [AuthGuard]},
  { path: 'employees/edit/:id', component: EditEmployeeComponent, canActivate: [AuthGuard] },
  { path: 'employees/view/:id', component: ViewEmployeeComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: '/login' }
];
