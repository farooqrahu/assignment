import {Routes} from '@angular/router';

import {DashboardComponent} from '../../pages/dashboard/dashboard.component';
import {UserProfileComponent} from '../../pages/user-profile/user-profile.component';
import {EmployeesComponent} from '../../pages/employees/employees.component';
import {UsersComponent} from 'src/app/pages/users/users.component';
import {RegisterComponent} from "../../pages/register/register.component";

export const AdminLayoutRoutes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'user-profile', component: UserProfileComponent },
  { path: 'employees', component: EmployeesComponent },
  { path: 'users', component: UsersComponent },
  { path: 'register', component: RegisterComponent }
];
