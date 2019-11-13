package thecafeshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thecafeshop.DAO.EmloyeeDAO;
import thecafeshop.DAOImp.EmployeeDAOImp;
import thecafeshop.entity.Employee;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class EmployeeService implements EmployeeDAOImp {

	@Autowired
	private EmloyeeDAO emloyeeDAO;

	@Override
	public String logIn(String username, String password) {

		return emloyeeDAO.logIn(username, password);

	}

	@Override
	public Employee getInfoById(String emId) {

		return emloyeeDAO.getInfoById(emId);
	}

	@Override
	public List<Employee> findAll() {
		
		return emloyeeDAO.findAll();
	}

	@Override
	public Boolean addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return emloyeeDAO.addEmployee(employee);
	}

	@Override
	public Boolean deleteEmployee(String employeeid) {
		// TODO Auto-generated method stub
		return emloyeeDAO.deleteEmployee(employeeid);
	}

	@Override
	public Boolean editEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return emloyeeDAO.editEmployee(employee);
	}

	@Override
	public Boolean checkExistUseName(String usename) {
		// TODO Auto-generated method stub
		return emloyeeDAO.checkExistUseName(usename);
	}

	@Override
	public List<Employee> findLimit(int startPosition) {
		// TODO Auto-generated method stub
		return emloyeeDAO.findLimit(startPosition);
	}

//	public Customer findById(final int id) {
//		return customerDAO.findById(id);
//	}
//
//	public void save(final Customer customer) {
//		customerDAO.save(customer);
//	}
//
//	public void update(final Customer customer) {
//		customerDAO.update(customer);
//	}
//
//	public void delete(final int id) {
//		Customer customer = customerDAO.findById(id);
//		if (customer != null) {
//			customerDAO.delete(customer);
//		}
//	}
}
