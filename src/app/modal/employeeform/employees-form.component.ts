import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Employee} from 'src/app/models/employee.model';

@Component({
  selector: 'app-employeesform',
  templateUrl: './employees-form.component.html',
  styleUrls: ['./employees-form.component.css']
})
export class EmployeesFormComponent implements OnInit {
  errors: String;
  constructor(
    public dialogRef: MatDialogRef<EmployeesFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Employee) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  validate(data: Employee): boolean {
    this.errors = "Form validation!";
    if (data.firstName == null || data.firstName == ""){
      this.errors += "First Name\n"
    }else{
      if (data.firstName.length<2)
        this.errors += "\n"
    }
    if (data.lastName == null || data.lastName == ""){
      this.errors += "Last Name\n"

    }else{
      if (data.lastName.length<2)
        this.errors += "\n"
    }
    if (data.salary <= 0)
      this.errors += "Salary\n"
   if(!this.validateEmail(data.email)){
     this.errors += "Email.\n";
   }
    return (this.errors == "")
  }
  validateEmail(email) {
    const regularExpression = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return regularExpression.test(String(email).toLowerCase());
  }
  ngOnInit() {
  }

}
