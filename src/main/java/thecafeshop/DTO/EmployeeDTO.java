package thecafeshop.DTO;


import thecafeshop.entity.Employee;

public class EmployeeDTO {

	private Employee employee;
	
	private int salary;

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
