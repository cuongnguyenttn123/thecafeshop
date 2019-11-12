package thecafeshop.DAOImp;

import thecafeshop.entity.Salary;

import java.util.List;



public interface SalaryDAOImp extends CommonDAOImp{

	public Boolean addSalary(Salary salary);

	public Salary getInfoById(int dinnertableid);

	public int getSalaryByEmployeeid(String employeeid);
	
	public Boolean deleteSalary(Salary Salary);

	public Boolean editSalary(Salary Salary);
	
}
