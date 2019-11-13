package thecafeshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAO.SalaryDAO;
import thecafeshop.DAOImp.SalaryDAOImp;
import thecafeshop.entity.Salary;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class SalaryService implements SalaryDAOImp {

	@Autowired
	SalaryDAO salaryDAO;

	@Override
	public Boolean addSalary(Salary salary) {
		// TODO Auto-generated method stub
		return salaryDAO.addSalary(salary);
	}

	@Override
	public Salary getInfoById(int dinnertableid) {
		// TODO Auto-generated method stub
		return salaryDAO.getInfoById(dinnertableid);
	}

	@Override
	public Boolean deleteSalary(Salary Salary) {
		// TODO Auto-generated method stub
		return salaryDAO.deleteSalary(Salary);
	}

	@Override
	public Boolean editSalary(Salary salary) {
		// TODO Auto-generated method stub
		return salaryDAO.editSalary(salary);
	}

	@Override
	public int getSalaryByEmployeeid(String employeeid) {
		// TODO Auto-generated method stub
		return salaryDAO.getSalaryByEmployeeid(employeeid);
	}
	
}