import {EmployeeRequest} from '../../models/employeerequest.model';
import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {MessageboxComponent} from 'src/app/modal/messagebox/messagebox.component';
import {EmployeesFormComponent} from 'src/app/modal/employeeform/employees-form.component';
import {EmployeeService} from 'src/app/_services/employee.service';
import {TokenStorageService} from 'src/app/_services/token-storage.service';
import {Employee} from '../../models/employee.model';
import Swal from 'sweetalert2'
import {SelectionModel} from "@angular/cdk/collections";

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.scss']
})
export class EmployeesComponent implements OnInit, AfterViewInit {
  columnsToDisplay = ["employeeId", 'firstName', "lastName", "email", "salary", "action"];
  dataSource: MatTableDataSource<Employee> = null;
  selection = new SelectionModel<Employee>(true, []);

  employees: Employee[] = [];
  employeeslength = 0;

  constructor(public employeeservice: EmployeeService, private token: TokenStorageService
    , public dialog: MatDialog
              // ,@Inject(DOCUMENT) document:Document
  ) {
  }

  ngAfterViewInit(): void {
  }

  @ViewChild(MatSort) sort: MatSort | any;
  @ViewChild(MatPaginator) paginator: MatPaginator | any;
  @ViewChild('employeesearch') employeesearch: ElementRef | any;

  counter(i: number) {
    return new Array(i);
  }

  loademployeeresults(): void {
    this.paginator.page.subscribe(() => {
        const employeerequest = new EmployeeRequest(0, this.employeesearch.nativeElement.value,
          this.employeesearch.nativeElement.value, "", 0, null, 0, 0, null, 'firstName', 'asc', this.paginator.pageSize, this.paginator.getNumberOfPages())
        this.employeeservice.find(employeerequest).subscribe(
          data => {
            this.employees = data.list;
            this.employeeslength = data.totalitems;
            setTimeout(() => {
              this.dataSource = new MatTableDataSource(this.employees);
              this.dataSource.sort = this.sort;
              this.dataSource.paginator = this.paginator;
            });
          },
          err => {(err);}
        );


      }
    )
  }

  ngOnInit() {
    this.refreshemployee();
  }

  refreshemployee() {
    this.employeeservice.getAll().subscribe(
      data => {
        this.employees = data.list;
        (this.employees.length);
        this.employeeslength = data.totalitems;
        this.dataSource = new MatTableDataSource(this.employees);
        setTimeout(() => {
          this.dataSource.sort = this.sort;
          this.dataSource.paginator = this.paginator;
        });
      },
      err => {
        (err);
      }
    );
  }

  public doFilter = (value: string, type: String) => {
    switch (type) {
      case 'Employee':
        this.dataSource.filter = value.trim().toLocaleLowerCase();
        break;

    }
  }

  openDialog(employee?: Employee): void {
    if (employee === undefined)
      employee = new Employee(0, "", "", "", 0, null, 0, 0, 0)
    const dialogRef = this.dialog.open(EmployeesFormComponent, {
      width: '400px',
      data: {
        employeeId: employee.employeeId,
        firstName: employee.firstName,
        lastName: employee.lastName,
        email: employee.email,
        hireDat: employee.hireDat,
        departmentId: employee.departmentId,
        managerId: employee.managerId,
        salary: employee.salary,
        phoneNumber: employee.phoneNumber,
      }
    });
    dialogRef.afterClosed().subscribe(res => {
      if (JSON.stringify(employee) != JSON.stringify(res)) {
        if (employee.employeeId != res.employeeId)
          console.log("error")
        else {
          this.updateEmployee(res)
          this.refreshemployee()
        }
      }


    });
  }
  updateEmployee(employee: Employee): any {
    this.employeeservice.addUpdate(employee).subscribe(data => {
        var objIndex = this.employees.findIndex((obj => obj.employeeId == employee.employeeId));
        this.employees[objIndex] = employee
        this.dataSource = new MatTableDataSource(this.employees)
        this.refreshemployee()
        Swal.fire(
          'Success!',
          data.message,
          'success'
        )

      },
      err => {
        this.messagebox("error updating Employee");

      }
    );
  }

  delete(employee: Employee): any {
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.employeeservice.delete(employee).subscribe(value => {
          Swal.fire(
            'Deleted!',
            'Employee has been deleted.',
            'success'
          )
          this.refreshemployee()
        })
      }
    })
  }

  messagebox(body: string, title?: string) {
    if (title === undefined)
      title = "Notice"
    const dialogRef = this.dialog.open(MessageboxComponent, {
      width: '350px',
      data: {
        title: title, body: body
      }
    });
  }


}


