import { Employee } from './employee.model';

describe('employee', () => {
  it('should create an instance', () => {
    // @ts-ignore
    expect(new Employee()).toBeTruthy();
  });
});
