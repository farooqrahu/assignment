import {Employee} from "./employee.model";

export class EmployeeRequest {
  constructor(
    public employeeId: number,
    public firstName: string,
    public lastName: string,
    public email: string,
    public phoneNumber: number,
    public hireDat: Date,
    public salary: number,
    public managerId: number,
    public departmentId: number,
    private sort: string = "id",
    private sortdirection: string = "asc",
    private pagesize: number = 10,
    private pagenumber: number = 0
  ) {
  }

  convert(
    employee: Employee,
  ) {
    this.employeeId = employee.employeeId
    this.firstName = employee.firstName
    this.lastName = employee.lastName
    this.email = employee.email
    this.hireDat = employee.hireDat
    this.salary = employee.salary
  }
}
