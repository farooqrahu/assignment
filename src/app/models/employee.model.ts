export class Employee {
  constructor(
    public employeeId: number,
    public firstName: string,
    public lastName: string,
    public email: string,
    public phoneNumber: number,
    public hireDat: Date,
    public salary: number,
    public managerId: number,
    public departmentId: number
  ) {
  }

}
