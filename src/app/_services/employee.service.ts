import {TokenStorageService} from 'src/app/_services/token-storage.service';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {Observable} from 'rxjs';
import {Employee} from '../models/employee.model';
import {EmployeeRequest} from '../models/employeerequest.model';

const employee_API = 'http://localhost:8080/api/employee/';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
const HttpUploadOptions = {
  headers: new HttpHeaders({})
}

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  constructor(private http: HttpClient, private token: TokenStorageService) {
  }
  employeeRequest: EmployeeRequest = new EmployeeRequest(0, '', '', "", 0, null, 0, 0, null, null);
  addUpdate(employee: Employee): Observable<any> {
    this.employeeRequest.convert(employee);
    const employeeId = this.employeeRequest.employeeId;
    const firstName = this.employeeRequest.firstName;
    const lastName = this.employeeRequest.lastName;
    const salary = this.employeeRequest.salary;
    const phoneNumber = this.employeeRequest.phoneNumber;
    const email = this.employeeRequest.email;
    const hireDat = this.employeeRequest.hireDat;

    return this.http.post(employee_API + 'add-update', {
      employeeId, firstName, lastName, salary, phoneNumber, email, hireDat
    }, httpOptions);
  }

  find(employeeRequest: EmployeeRequest): Observable<any> {
    const employeeId = employeeRequest.employeeId;
    const firstName = employeeRequest.firstName;
    const lastName = employeeRequest.lastName;
    const email = employeeRequest.email;
    return this.http.post(employee_API + 'find', {
      employeeId, firstName, lastName, email
    }, httpOptions);
  }

  delete(employee: Employee): Observable<any> {
    console.log(employee);
    const employeeId = employee.employeeId;
    return this.http.post(employee_API + 'delete', {employeeId}, httpOptions);
  }

  getAll(): Observable<any> {
    return this.http.post(employee_API + 'find', {}, httpOptions);
  }
}
